package Model;

import Utilities.RegexpCreator;
import Utilities.SearchPattern;

public class Query {
    private String url;
    private SearchPattern sentencePattern;
    private SearchPattern forbiddenPattern;
    private Integer deep;
    private boolean subdomains;

    public Query(String url,
                 String sentence,
                 String forbiddenWords,
                 int deep,
                 boolean subdomains){
        this.url = url;
        this.deep = deep;
        this.subdomains = subdomains;

        this.sentencePattern = new SearchPattern(RegexpCreator.getSearchExpr(sentence));
        this.forbiddenPattern = new SearchPattern(RegexpCreator.getSearchExpr(forbiddenWords));
    }

    public String getUrl(){
        return url;
    }
    public SearchPattern getSentencePattern() {
        return sentencePattern;
    }
    public SearchPattern getForbiddenWords(){
        return forbiddenPattern;
    }
    public int getDeep(){
        return deep;
    }
    public boolean getSubdomains(){
        return subdomains;
    }

    public boolean matches(String fullSentence){
        return sentencePattern.matches(fullSentence) && !sentencePattern.matches(fullSentence);
    }
}

