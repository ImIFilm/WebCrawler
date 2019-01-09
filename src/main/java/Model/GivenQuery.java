package Model;

import Utilities.RegexpCreator;
import Utilities.SearchPattern;
import Utilities.Validator;

public class GivenQuery extends Query {
    private String sentencePatternString;
    private String forbiddenPatternString;

    private Validator validator;

    public GivenQuery(String url,
                      String sentencePattern,
                      String forbiddenPattern,
                      int depth,
                      boolean subdomains) {
        if (isEmpty(sentencePattern) && isEmpty(forbiddenPattern)) {
            throw new IllegalArgumentException();
        }

        this.url = url;
        this.depth = depth;
        this.subdomains = subdomains;
        this.sentencePatternString = sentencePattern;
        this.forbiddenPatternString = forbiddenPattern;
        this.sentencePattern = new SearchPattern(RegexpCreator.getSearchExpr(sentencePattern));
        this.forbiddenPattern = new SearchPattern(RegexpCreator.getSearchExpr(forbiddenPattern));

        this.validator = new Validator(url, subdomains);
    }

    private GivenQuery(String url,
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
        this.depth = deep;
        this.validator = validator;
    }

    public GivenQuery clone() {
        return new GivenQuery(
                this.url,
                this.sentencePatternString,
                this.forbiddenPatternString,
                this.depth,
                this.subdomains,
                this.sentencePattern,
                this.forbiddenPattern,
                this.validator);
    }

    public String getSentencePatternString() {
        return sentencePatternString;
    }

    public String getForbiddenPatternString() {
        return forbiddenPatternString;
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

