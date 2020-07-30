package io.cucumber.fuzzysearch;

import info.debatty.java.stringsimilarity.RatcliffObershelp;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.apache.commons.text.similarity.CosineDistance;


public class FuzzyStringMatchSearch {

    /**
     * approach: fuzzywuzzy
     * @param searchModel
     */
    public void extractBestMatch(FuzzySearchModel searchModel) {
        ExtractedResult res = FuzzySearch.extractOne(searchModel.getAddressFromRequest(),
                searchModel.getDemographicAddresses());
        if (res.getScore() > searchModel.getGivenCutOff()) {
            searchModel.setFuzzyWuzzyMatchedScore((float) res.getScore());
            searchModel.setFuzzyWuzzyMatchedValue(res.getString());
        } else {
            searchModel.setFuzzyWuzzyMatchedScore(0.0f);
        }
    }

    /**
     * approach: Java string similarity
     * @param searchModel
     */
    public void ratcliffObershelpJSS(FuzzySearchModel searchModel){
        RatcliffObershelp ro = new RatcliffObershelp();

        searchModel.setRatcliffObershelpSimilarity(Math.round(ro.similarity(searchModel.getDemographicAddresses().get(0),
                searchModel.getAddressFromRequest()) * 100));
    }

    public void cosineDistanceApacheCommons(FuzzySearchModel searchModel){
        // How dis-similar words are between both strings.
        double cosineDistance = new CosineDistance().apply(searchModel.getDemographicAddresses().get(0).toLowerCase(),
                searchModel.getAddressFromRequest().toLowerCase());
        double cosineDistancePercentage = Math.round(cosineDistance * 100);
        double cosineSimilarityPercentage = Math.round((1 - cosineDistance) * 100);
        searchModel.setCosineSimilarity(cosineSimilarityPercentage);
    }

}
