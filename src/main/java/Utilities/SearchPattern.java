package Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchPattern {
    public String patternRegex;

    public SearchPattern(String patternRegex) {
        this.patternRegex = patternRegex;
    }

    public boolean matches(String sentence){
        Pattern pattern = Pattern.compile(patternRegex);
        Matcher matcher = pattern.matcher(sentence.toLowerCase());
        return matcher.find();
    }
}
