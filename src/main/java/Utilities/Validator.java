package Utilities;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private String link;
    private Boolean subdomains;

    public Validator(String link, Boolean subdomains){
        this.link = link;
        this.subdomains = subdomains;
    }

    public boolean validateSublink(String sub){
        if(subdomains){
            return isSubdomain(sub) || isContinuation(sub);
        }
        else{
            return isContinuation(sub);
        }
    }

    private boolean isSubdomain(String sub){
        try{
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
        catch(IllegalStateException e){
            return false;
        }
    }

    private boolean isContinuation(String sub){
        try {
            Pattern pattern = Pattern.compile("^((http[s]?:\\/{2})?(www\\.)?((\\w+\\.)+\\w+))(\\/\\S*)*$");
            Matcher matcher = pattern.matcher(link);
            matcher.find();
            String fst_domain = matcher.group(4);
            matcher = pattern.matcher(sub);
            matcher.find();
            String snd_domain = matcher.group(4);
            return fst_domain.equals(snd_domain);
        }
        catch (IllegalStateException e){
            return false;
        }

    }
}
