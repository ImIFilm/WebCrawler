package Utilities;
//import com.sun.deploy.util.StringUtils;
import org.jsoup.select.Elements;

import javax.xml.bind.Element;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TextParser {
    Elements whatIsInWebsite;

    public TextParser (Elements all){
        whatIsInWebsite=all;
    }

    public ArrayList<String> makeSentences(){
        String div;
        ArrayList<String> newest = new ArrayList<>();
        for(int j=0;j<whatIsInWebsite.size();j++){
            div=whatIsInWebsite.get(j).text();
            BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
            iterator.setText(div);
            List<String> result = new ArrayList<>();
            int start = iterator.first();
            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
                result.add(div.substring(start, end));
            }
            newest.addAll(result);
        }
        return newest;
    }
}
