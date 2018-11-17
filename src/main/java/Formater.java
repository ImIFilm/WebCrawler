
//import com.sun.deploy.util.StringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formater {
    String sentence;

    public Formater (String all){
        sentence=all;
    }

    // https://stackoverflow.com/questions/2687012/split-string-into-sentences
    public String showOnlySentenceWithWord (String what){
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        String source = sentence;
        iterator.setText(source);
        String now;
        List<String> tokens = new ArrayList<>();
        tokens.add(what);
        String patternString = ".*(" + StringUtils.join(tokens, "|") + ").*";
        Pattern pattern = Pattern.compile(patternString);

        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            now=source.substring(start,end).toLowerCase();
            Matcher matcher = pattern.matcher(now);
            if (matcher.find())
                return source.substring(start,end);
        }
        return " ";
    }
}
