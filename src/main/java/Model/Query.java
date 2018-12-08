package Model;

import Utilities.RegexpCreator;
import Utilities.SearchPattern;

public class Query {
    private String url;
    private SearchPattern sentencePattern;
    private SearchPattern forbiddenPattern;
    private String sentencePatternString;
    private String forbiddenPatternString;
    private Integer deep;
    private boolean subdomains;

    public Query(String url,
                 String sentence,
                 String forbiddenWords,
                 int deep,
                 boolean subdomains){
        if(isEmpty(sentence) && isEmpty(forbiddenWords))
            throw new IllegalArgumentException();

        this.url = url;
        this.deep = deep;
        this.subdomains = subdomains;
        this.sentencePatternString = sentence;
        this.forbiddenPatternString = forbiddenWords;
        this.sentencePattern = new SearchPattern(RegexpCreator.getSearchExpr(sentence));
        this.forbiddenPattern = new SearchPattern(RegexpCreator.getSearchExpr(forbiddenWords));
    }

    public String getUrl(){
        return url;
    }
    public SearchPattern getSentencePattern() {
        return sentencePattern;
    }

    public SearchPattern getForbiddenPattern(){
        return forbiddenPattern;
    }

    public int getDeep(){
        return deep;
    }
    public boolean getSubdomains(){
        return subdomains;
    }
    public String getSentencePatternString(){
        return sentencePatternString;
    }
    public String getForbiddenPatternString() {
        return forbiddenPatternString;
    }

    public void setSentencePattern(SearchPattern sentencePattern){
        this.sentencePattern = sentencePattern;
    }
    public void setForbiddenPattern(SearchPattern forbiddenPattern){
        this.forbiddenPattern = forbiddenPattern;
    }

    public void setDeep(int deep){
        this.deep = deep;
    }

    public boolean matches(String fullSentence){
        return sentencePattern.matches(fullSentence) && !forbiddenPattern.matches(fullSentence);
    }

    private boolean isEmpty(String sentence){
        return sentence.matches("\\s*");
    }
}

