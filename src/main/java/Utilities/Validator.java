package Utilities;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean isSubdomain(String link, String sub){
        Pattern pattern = Pattern.compile("^(http[s]?:\\/{2})?(www\\.)?((\\w+\\.)+\\w+)(\\/\\S*)*$");
        Matcher matcher = pattern.matcher(link);
        matcher.find();
        String domain = Arrays.asList(matcher.group(3).split("(?<=\\.)|(?=\\.)"))
                .stream()
                .map(e -> e.equals(".") ? "\\." : e)
                .reduce("", String::concat);

        pattern = Pattern.compile(String.format("^(http[s]?:\\/{2})?(www\\.)?(\\w+\\.)+\\b%s\\b(\\/\\S*)*$", domain));
        matcher = pattern.matcher(sub);
        return matcher.find();
    }

    public boolean isContinuation(String link, String sub){
        Pattern pattern = Pattern.compile("^((http[s]?:\\/{2})?(www\\.)?((\\w+\\.)+\\w+))(\\/\\S*)*$");
        Matcher matcher = pattern.matcher(link);
        matcher.find();
        String fst_domain = matcher.group(4);
        matcher = pattern.matcher(sub);
        matcher.find();
        String snd_domain = matcher.group(4);

        return fst_domain.equals(snd_domain);
    }
}
