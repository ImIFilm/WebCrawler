package Controller;

public class Query {

    private String url;
    private String sentencePattern;
    private String forbiddenWords;
    private Integer deep;
    private boolean ifMine;

    public Query(String url,
                 String sentencePattern,
                 String forbiddenWords,
                 int deep,
                 boolean ifMine){
        this.url = url;
        this.sentencePattern = sentencePattern;
        this.forbiddenWords = forbiddenWords;
        this.deep = deep;
        this.ifMine = ifMine;
    }

    public String getUrl(){
        return url;
    }
    public String getSentencePattern() {
        return sentencePattern;
    }

    public String getForbiddenWords(){
        return forbiddenWords;
    }

    public int getDeep(){
        return deep;
    }

    public boolean getIfMine(){
        return ifMine;
    }
}

