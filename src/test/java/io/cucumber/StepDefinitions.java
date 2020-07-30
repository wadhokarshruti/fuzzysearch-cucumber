package io.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.fuzzysearch.FuzzySearchModel;
import io.cucumber.fuzzysearch.FuzzyStringMatchSearch;
import io.cucumber.fuzzysearch.WriteDataToExcel;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Shruti Wadhokar
 */
public class StepDefinitions {
    private final FuzzySearchModel searchModel = new FuzzySearchModel();

    @Given("^multiple addresses (.*) from the demographic API for a customer$")
    public void i_have_address_from_demographic_api(String demographicAddressesInput) {
        String[] demographicAddressesArray = demographicAddressesInput.split(";");
        List<String> normalisedAddresses = Arrays.asList(demographicAddressesArray).stream().map(this::normaliseAddress).collect(Collectors.toList());
        searchModel.setDemographicAddresses(normalisedAddresses);
    }

    private String normaliseAddress(String address) {
        return address.toUpperCase().replace(",", "").replace("UNIT", "")
                .replace("STREET", "ST").replace("AVENUE","AVE")
                .replace("ROAD","RD").replace("/", " ").trim();
    }

    @When("^customer enters (.*) non-standard address in the request$")
    public void i_send_address_in_request(String addressFromRequest) {
        searchModel.setAddressFromRequest(normaliseAddress(addressFromRequest));
    }

    @When("^cutoff is (\\d+)$")
    public void i_have_cutoff(int givenCutOff) {
        searchModel.setGivenCutOff(givenCutOff);
    }

    @Then("^Know the match score$")
    public void my_matched_score_is() {
        if (!searchModel.getAddressFromRequest().contains("-") && searchModel.getDemographicAddresses().get(0).contains("-")){
            searchModel.setDemographicAddresses(Collections.singletonList(searchModel.getDemographicAddresses().get(0).replaceAll("-[0-9]{2}", "")));
        }
        if (searchModel.getAddressFromRequest().contains("-") && !searchModel.getDemographicAddresses().get(0).contains("-")){
            searchModel.setAddressFromRequest(searchModel.getAddressFromRequest().replaceAll("-[0-9]{2}", ""));
        }

        FuzzyStringMatchSearch search = new FuzzyStringMatchSearch();
        search.extractBestMatch(searchModel);
        System.out.println("matched score: " + searchModel.getFuzzyWuzzyMatchedScore());
        System.out.println("matched result: " + searchModel.getFuzzyWuzzyMatchedValue());
        search.ratcliffObershelpJSS(searchModel);
        search.cosineDistanceApacheCommons(searchModel);
        searchModel.setStringEquals(searchModel.getAddressFromRequest()
                .equalsIgnoreCase(searchModel.getDemographicAddresses().get(0)));
        WriteDataToExcel writeDataToExcel = new WriteDataToExcel();
        try {
            writeDataToExcel.writeOrUpdate(searchModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
