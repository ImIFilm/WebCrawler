package Model;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Result {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String sentence;

    @ManyToOne(cascade=CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return sentence.equals(result.sentence) &&
                storedQuery.equals(result.storedQuery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentence, storedQuery);
    }
}