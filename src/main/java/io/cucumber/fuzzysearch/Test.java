package io.cucumber.fuzzysearch;

import info.debatty.java.stringsimilarity.RatcliffObershelp;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.apache.commons.text.similarity.CosineDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //case 1
        /*String request = "3, 90-98 South Street";
        String dempgraphic = "Unit 3, 90-98 South Street";

        String requestModified = request.toUpperCase().replace(",", "").replace("UNIT","")
                .replace("STREET","ST");
        String dempgraphicModified = dempgraphic.toUpperCase().replace(",", "").replace("UNIT ","")
                .replace("STREET","ST");*/
        //case 2
        /*String request = "3/90-98 South street";
        String dempgraphic = "Unit 3, 90-98 South Street";

        String requestModified = request.toUpperCase().replace(",", "").replace("UNIT","")
                .replace("STREET","ST").replace("/"," ");
        String dempgraphicModified = dempgraphic.toUpperCase().replace(",", "").replace("UNIT ","")
                .replace("STREET","ST");*/
        //case 3
        /*String request = "Unit 3, 90-98 South St";
        String dempgraphic = "Unit 3, 90-98 South Street";

        String requestModified = request.toUpperCase().replace(",", "").replace("UNIT","")
                .replace("STREET","ST").replace("/"," ").trim();
        String dempgraphicModified = dempgraphic.toUpperCase().replace(",", "").replace("UNIT ","")
                .replace("STREET","ST").trim();*/

        //case 4
        /*String request = "3, 90-98 South St";
        String dempgraphic = "Unit 3, 90-98 South Street";

        String requestModified = request.toUpperCase().replace(",", "").replace("UNIT","")
                .replace("STREET","ST").replace("/"," ").trim();
        String dempgraphicModified = dempgraphic.toUpperCase().replace(",", "").replace("UNIT ","")
                .replace("STREET","ST").trim();*/

        //case 5
        /*String request = "3/90-98 South st";
        String dempgraphic = "Unit 3, 90-98 South Street";

        String requestModified = request.toUpperCase().replace(",", "").replace("UNIT","")
                .replace("STREET","ST").replace("/"," ").trim();
        String dempgraphicModified = dempgraphic.toUpperCase().replace(",", "").replace("UNIT ","")
                .replace("STREET","ST").trim();*/

        //case 6
       /* String request = "Unit 3, 90 South Street";
        String dempgraphic = "Unit 3, 90-98 South Street";

        String requestModified = request.toUpperCase().replace(",", "").replace("UNIT","")
                .replace("STREET","ST").replace("/"," ").trim();
        String dempgraphicModified = dempgraphic.toUpperCase().replace(",", "").replace("UNIT ","")
                .replace("STREET","ST").trim();*/
        //case 7
        /*String request = "3, 90 South Street";
        String dempgraphic = "Unit 3, 90-98 South Street";

        String requestModified = request.toUpperCase().replace(",", "").replace("UNIT","")
                .replace("STREET","ST").replace("/"," ").trim();
        String dempgraphicModified = dempgraphic.toUpperCase().replace(",", "").replace("UNIT ","")
                .replace("STREET","ST").trim();*/

        //case 8
        String request = "3/90 South street";
        String dempgraphic = "Unit 3, 90-98 South Street";

        String requestModified = request.toUpperCase().replace(",", "").replace("UNIT","")
                .replace("STREET","ST").replace("/"," ").trim();
        String dempgraphicModified = dempgraphic.toUpperCase().replace(",", "").replace("UNIT ","")
                .replace("STREET","ST").trim();

        System.out.println("requestModified:: :"+requestModified);
        System.out.println("dempgraphicModified:: :"+dempgraphicModified);
        System.out.println("String equals:: "+ requestModified.equalsIgnoreCase(dempgraphicModified));
        extractBestMatch(requestModified,dempgraphicModified);
        ratcliffObershelpJSS(requestModified,dempgraphicModified);
        cosineDistanceApacheCommons(requestModified,dempgraphicModified);
        //System.out.println(print(1));;
        String[] array = {"Zebra","Tiger","Monkey"};
        Arrays.asList(array);

       /* int a = 123456789012345678;
        System.out.println(a);*/

        List temp = new LinkedList();
        temp.add(1);
        temp.add(2);
        temp.add(0);
        System.out.println(temp);
        System.out.println(temp.get(0) instanceof Object);
        System.out.println(temp.get(1) instanceof Integer);
    }

    /**
     * approach: fuzzywuzzy
     */
    public static void extractBestMatch(String req, String demo) {
        ExtractedResult res = FuzzySearch.extractOne(req, Collections.singleton(demo));
        System.out.println("fuzzywuzzy score ::"+res.getScore());
    }

    /**
     * approach: Java string similarity
     */
    public static void ratcliffObershelpJSS(String req, String demo){
        RatcliffObershelp ro = new RatcliffObershelp();

        System.out.println("RatcliffObershelp::"+Math.round(ro.similarity(req,demo))*100);
    }

    public static void cosineDistanceApacheCommons(String req, String demo){
        // How dis-similar words are between both strings.
        double cosineDistance = new CosineDistance().apply(req, demo);
        double cosineDistancePercentage = Math.round(cosineDistance * 100);
        double cosineSimilarityPercentage = Math.round((1 - cosineDistance) * 100);
        System.out.println((cosineSimilarityPercentage));
    }

    static Exception print(int i){
        if(i>1)
            return new Exception();
        else
            throw new RuntimeException();

    }
}
