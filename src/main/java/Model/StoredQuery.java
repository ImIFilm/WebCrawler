package Model;

import javax.persistence.*;

@Entity
@Table
public class StoredQuery extends Query {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String url;
    private String sentenceRegex;
    private String forbiddenRegex;
    private int depth;
    private boolean subdomains;

    public StoredQuery(){
    }

    public StoredQuery(String url){
        this.url = url;
    }

    public StoredQuery(Query query){
        this.url = query.getUrl();
        super.url = query.getUrl();
        this.sentenceRegex = query.getSentencePattern().getPatternRegex();
        this.forbiddenRegex = query.getForbiddenPattern().getPatternRegex();
        this.depth = query.getDepth();
        super.depth = query.getDepth();
        this.subdomains = query.getSubdomains();
        super.subdomains = query.getSubdomains();
        this.sentencePattern = query.getSentencePattern();
        this.forbiddenPattern = query.getForbiddenPattern();
    }

    public String getUrl(){
        return this.url;
    }

    public String getSentenceRegex(){
        return this.sentenceRegex;
    }

    public String getForbiddenRegex(){
        return this.forbiddenRegex;
    }

    public boolean getSubdomains() {
        return this.subdomains;
    }

    public int getDepth() {
        return this.depth;
    }
}
