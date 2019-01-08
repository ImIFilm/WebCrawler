package Utilities;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        String elementString;
        ArrayList<String> newest = new ArrayList<>();
        for (Element element : whatIsInWebsite) {
            elementString = element.text();
            BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
            iterator.setText(elementString);
            List<String> result = new ArrayList<>();
            int start = iterator.first();
            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
                result.add(elementString.substring(start, end));
            }
            newest.addAll(result);
        }
        return newest;
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }
}
