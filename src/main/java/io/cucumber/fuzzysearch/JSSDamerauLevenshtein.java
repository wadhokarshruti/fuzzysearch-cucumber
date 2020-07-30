package io.cucumber.fuzzysearch;

import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.RatcliffObershelp;

/**
 * https://github.com/tdebatty/java-string-similarity#damerau-levenshtein
 */
public class JSSDamerauLevenshtein {
    public static void main(String[] args) {
        RatcliffObershelp ro = new RatcliffObershelp();

        // substitution of s and t
        System.out.println(ro.similarity("My string", "My tsring"));

        // substitution of s and n
        System.out.println(ro.similarity("My string", "My ntrisg"));
        System.out.println(Math.round(0.20913421238*100));
    }
}
