package Utilities;
//import com.sun.deploy.util.StringUtils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import javax.xml.bind.Element;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TextParser {

    private ArrayList<String> sentences;

    public TextParser(Elements whatIsInWebsite) {
        this.sentences = makeSentences(whatIsInWebsite);
    }

    private ArrayList<String> makeSentences(Elements whatIsInWebsite) {
        String div;
        ArrayList<String> newest = new ArrayList<>();
        for (Element element : whatIsInWebsite) {
            div = element.text();
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

    public ArrayList<String> getSentences() {
        return sentences;
    }
}
