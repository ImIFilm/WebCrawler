import java.util.*;
import java.util.regex.Pattern;

public class RegexpCreator {
    private String searchExpr = null;
    private boolean valid = true;

    public RegexpCreator(String sentence, String forbidden_words) throws Exception{
        if(!validateInput(sentence, forbidden_words)){
            valid = false;
            return;
        }
        ArrayList<String> forbiddenWords = new ArrayList<>(Arrays.asList(forbidden_words.split(" ")));
        ArrayList<String> words = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        System.out.println(forbiddenWords);

        StringBuilder strBuilder = new StringBuilder();

        //append forbidden words
        strBuilder.append("^(?!(");
        for(int i = 0; i < forbiddenWords.size() - 1; i++)
            strBuilder.append(String.format(".*%s|", forbiddenWords.get(i)));

        strBuilder.append(String.format(".*%s)).*(", forbiddenWords.get(forbiddenWords.size() - 1)));

        //append words we search for
        Iterator<String> iter = words.iterator();
        String word = iter.next();
        int counter = 0;
        while(iter.hasNext()){
            if(!word.equals("*"))
                strBuilder.append(word).append(" ");
            word = iter.next();
            while(iter.hasNext() && word.equals("*")){
                counter++;
                word = iter.next();
            }
            if(counter > 0)
                strBuilder.append(getAnyWordPattern(counter));
            counter = 0;
        }

        strBuilder.append(words.get(words.size() - 1)).append(")");
        this.searchExpr = strBuilder.toString();
        System.out.println(this.searchExpr);
    }

    public Pattern getSearchExpr(){
        return searchExpr != null ? Pattern.compile(searchExpr) : Pattern.compile("");
    }

    private boolean validateInput(String sentence, String forbidden){
        String inputValidator = "^[a-zA-Z]+( (\\* )*[a-zA-Z]+)*$";
        String forbidValidator = "^[a-zA-Z\\s\\p{L}]*$";
        return sentence.matches(inputValidator) && forbidden.matches(forbidValidator);
    }

    public boolean getValid(){
        return valid;
    }

    private String getAnyWordPattern(int count){
        return String.format("([a-zA-Z\\p{L}]+ ){%d}", count);
    }
}
