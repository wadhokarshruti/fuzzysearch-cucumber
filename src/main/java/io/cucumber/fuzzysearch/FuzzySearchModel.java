package io.cucumber.fuzzysearch;

import java.util.List;

public class FuzzySearchModel {

    private List<String> demographicAddresses;
    private String addressFromRequest;
    private Integer givenCutOff;
    private Float fuzzyWuzzyMatchedScore;
    private String fuzzyWuzzyMatchedValue;
    private Long ratcliffObershelpSimilarity;
    private double cosineSimilarity;
    private boolean stringEquals;

    public void setDemographicAddresses(List<String> demographicAddresses) {
        this.demographicAddresses = demographicAddresses;
    }

    public List<String> getDemographicAddresses() {
        return demographicAddresses;
    }

    public void setAddressFromRequest(String addressFromRequest) {
        this.addressFromRequest = addressFromRequest;
    }

    public String getAddressFromRequest() {
        return addressFromRequest;
    }

    public void setGivenCutOff(Integer givenCutOff) {
        this.givenCutOff = givenCutOff;
    }

    public Integer getGivenCutOff() {
        return givenCutOff;
    }

    public Float getFuzzyWuzzyMatchedScore() {
        return fuzzyWuzzyMatchedScore;
    }

    public void setFuzzyWuzzyMatchedScore(Float fuzzyWuzzyMatchedScore) {
        this.fuzzyWuzzyMatchedScore = fuzzyWuzzyMatchedScore;
    }

    public void setFuzzyWuzzyMatchedValue(String fuzzyWuzzyMatchedValue) {
        this.fuzzyWuzzyMatchedValue = fuzzyWuzzyMatchedValue;
    }

    public String getFuzzyWuzzyMatchedValue() {
        return fuzzyWuzzyMatchedValue;
    }

    public Long getRatcliffObershelpSimilarity() {
        return ratcliffObershelpSimilarity;
    }

    public void setRatcliffObershelpSimilarity(Long ratcliffObershelpSimilarity) {
        this.ratcliffObershelpSimilarity = ratcliffObershelpSimilarity;
    }

    public void setCosineSimilarity(double cosineSimilarity) {
        this.cosineSimilarity = cosineSimilarity;
    }

    public double getCosineSimilarity() {
        return cosineSimilarity;
    }

    public boolean isStringEquals() {
        return stringEquals;
    }

    public void setStringEquals(boolean stringEquals) {
        this.stringEquals = stringEquals;
    }
}
