package Model;

import Utilities.RegexpCreator;
import Utilities.SearchPattern;
import Utilities.Validator;

public class Query {
    private String url;
    private SearchPattern sentencePattern;
    private SearchPattern forbiddenPattern;
    private String sentencePatternString;
    private String forbiddenPatternString;
    private Integer deep;
    private boolean subdomains;

    private Validator validator;

    public Query(String url,
                 String sentencePattern,
                 String forbiddenPattern,
                 int deep,
                 boolean subdomains) {
        if (isEmpty(sentencePattern) && isEmpty(forbiddenPattern)) {
            System.out.println("bad query");
            throw new IllegalArgumentException();
        }

        this.url = url;
        this.deep = deep;
        this.subdomains = subdomains;
        this.sentencePatternString = sentencePattern;
        this.forbiddenPatternString = forbiddenPattern;
        this.sentencePattern = new SearchPattern(RegexpCreator.getSearchExpr(sentencePattern));
        this.forbiddenPattern = new SearchPattern(RegexpCreator.getSearchExpr(forbiddenPattern));

        this.validator = new Validator(url, subdomains);
    }

    private Query(String url,
                  String sentencePatternString,
                  String forbiddenPatternString,
                  int deep,
                  boolean subdomains,
                  SearchPattern sentencePattern,
                  SearchPattern forbiddenPattern,
                  Validator validator) {
        this.url = url;
        this.sentencePatternString = sentencePatternString;
        this.forbiddenPatternString = forbiddenPatternString;
        this.sentencePattern = sentencePattern;
        this.forbiddenPattern = forbiddenPattern;
        this.subdomains = subdomains;
        this.deep = deep;
        this.validator = validator;
    }

    public Query clone() {
        return new Query(
                this.url,
                this.sentencePatternString,
                this.forbiddenPatternString,
                this.deep,
                this.subdomains,
                this.sentencePattern,
                this.forbiddenPattern,
                this.validator);
    }

    public String getUrl() {
        return url;
    }

    public SearchPattern getSentencePattern() {
        return sentencePattern;
    }

    public SearchPattern getForbiddenPattern() {
        return forbiddenPattern;
    }

    public int getDeep() {
        return deep;
    }

    public boolean getSubdomains() {
        return subdomains;
    }

    public String getSentencePatternString() {
        return sentencePatternString;
    }

    public String getForbiddenPatternString() {
        return forbiddenPatternString;
    }

    public void setSentencePattern(SearchPattern sentencePattern) {
        this.sentencePattern = sentencePattern;
    }

    public void setForbiddenPattern(SearchPattern forbiddenPattern) {
        this.forbiddenPattern = forbiddenPattern;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean matches(String fullSentence) {
        return sentencePattern.matches(fullSentence) && !forbiddenPattern.matches(fullSentence);
    }

    private boolean isEmpty(String sentence) {
        return sentence.matches("^\\s*$");
    }

    public boolean validateSublink(String sub) {
        return this.validator.validateSublink(sub);
    }

}

