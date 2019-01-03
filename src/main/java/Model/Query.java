package Model;

import Utilities.SearchPattern;

public class Query {
    protected String url;
    protected SearchPattern sentencePattern;
    protected SearchPattern forbiddenPattern;
    protected Integer depth;
    protected boolean subdomains;


    public String getUrl() {
        return url;
    }

    public SearchPattern getSentencePattern() {
        return sentencePattern;
    }

    public SearchPattern getForbiddenPattern() {
        return forbiddenPattern;
    }

    public int getDepth() {
        return depth;
    }

    public boolean getSubdomains() {
        return subdomains;
    }

    public void setSentencePattern(SearchPattern sentencePattern) {
        this.sentencePattern = sentencePattern;
    }

    public void setForbiddenPattern(SearchPattern forbiddenPattern) {
        this.forbiddenPattern = forbiddenPattern;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}

