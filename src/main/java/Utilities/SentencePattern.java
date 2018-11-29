package Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentencePattern {
    public String sentencePatternRegex;

    public SentencePattern(String sentencePatternRegex) {
        this.sentencePatternRegex = sentencePatternRegex;
    }

    public boolean ifMatch(String sentence){
        String lowerText = sentence.toLowerCase();
        Pattern pattern = Pattern.compile(sentencePatternRegex);
        Matcher matcher = pattern.matcher(lowerText);
        if (matcher.find()) return true;
        else return false;
    }
}
