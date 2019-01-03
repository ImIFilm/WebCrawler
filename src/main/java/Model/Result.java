package Model;
import javax.persistence.*;

@Entity
@Table
public class Result {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String sentence;

    @ManyToOne
    private StoredQuery storedQuery;


    public Result() {
    }

    public Result(StoredQuery storedQuery, String sentence){
        this.storedQuery = storedQuery;
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }
    public void setStoredQuery(StoredQuery storedQuery){
        this.storedQuery = storedQuery;
    }
    public void setSentence(String sentence){
        this.sentence = sentence;
    }
    public StoredQuery getStoredQuery(){
        return this.storedQuery;
    }
}