package Utilities;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpCreator {
    private String searchExpr = null;
    private boolean valid = true;

    public RegexpCreator(String sentence, String forbidden_words){
        if(!validateInput(sentence, forbidden_words)){
            valid = false;
            return;
        }
        ArrayList<String> forbiddenWords = new ArrayList<>(Arrays.asList(forbidden_words.split("[\\s\\,]+")));

        StringBuilder strBuilder = new StringBuilder();

        //append forbidden words
        strBuilder.append("^(?!(");
        for(int i = 0; i < forbiddenWords.size() - 1; i++)
            strBuilder.append(String.format(".*%s|", forbiddenWords.get(i)));

        strBuilder.append(String.format(".*%s)).*(", forbiddenWords.get(forbiddenWords.size() - 1)));

        String divider = "([a-zA-Z]+)([\\*\\,\\s]*)";
        Pattern pattern = Pattern.compile(divider);
        Matcher matcher = pattern.matcher(sentence);
        boolean first = true;
        while(matcher.find()){
            String word = matcher.group(1);
            String rest = matcher.group(2);
            int starCount = (int)rest.chars().filter(c -> c == '*').count();
            if(starCount > 0){
                if(first)
                    word = word.concat(" ");
                strBuilder.append(word).append(getAnyWordPattern(starCount));
            }
            else{
                strBuilder.append(word).append(" ");
            }
            first = false;
        }
        strBuilder.deleteCharAt(strBuilder.length() - 1).append(")");

        this.searchExpr = strBuilder.toString();
        System.out.println(this.searchExpr);
    }

    public String getSearchExpr(){
        return searchExpr;
    }

    public boolean getValid(){
        return valid;
    }
    private boolean validateInput(String sentence, String forbidden){
        String inputValidator = "^[a-zA-Z]+([\\*\\,\\s]+[a-zA-Z]+)*\\s*$";
        String forbidValidator = "^[a-zA-Z\\,\\s\\p{L}]*$";
        ArrayList<String> sentenceList = new ArrayList<>(Arrays.asList(sentence.split("[\\s\\,\\*]+")));
        ArrayList<String> forbidList = new ArrayList<>(Arrays.asList(forbidden.split("[\\s\\,]+")));
        sentenceList.retainAll(forbidList);
        return sentence.matches(inputValidator) && forbidden.matches(forbidValidator) && sentenceList.isEmpty();
    }

    private String getAnyWordPattern(int count){
        return String.format("([a-zA-Z\\p{L}]+ ){%d}", count);
    }
}