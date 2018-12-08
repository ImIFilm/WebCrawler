package Utilities;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpCreator {
    public static String getSearchExpr(String sentence){
        sentence = sentence.toLowerCase();
        if(!validateInput(sentence)){
            throw new IllegalArgumentException();
        }

        StringBuilder strBuilder = new StringBuilder();

        String divider = "([a-zA-Z\\p{L}]+)([\\*\\,\\s]*)";
        Pattern pattern = Pattern.compile(divider);
        Matcher matcher = pattern.matcher(sentence);
        boolean first_match = true;
        while(matcher.find()){
            String word = matcher.group(1);
            String rest = matcher.group(2);
            int starCount = (int)rest.chars().filter(c -> c == '*').count();
            if(starCount > 0){
                if(first_match)
                    word = word.concat(" ");
                strBuilder.append(word).append(getAnyWordPattern(starCount));
            }
            else{
                strBuilder.append(word).append(" ");
            }
            first_match = false;
        }
        strBuilder.deleteCharAt(strBuilder.length() - 1);

        return strBuilder.toString();
    }

    private static boolean validateInput(String sentence){
        String inputValidator = "^[a-zA-Z\\p{L}]+([\\*\\,\\s]+[a-zA-Z\\p{L}]+)*\\s*$";
        return sentence.matches(inputValidator);
    }
    private static String getAnyWordPattern(int count){
        return String.format("([a-zA-Z\\p{L}]+ ){%d}", count);
    }
}