import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;

/**
 * Created by chentao on 2016/10/19 0019.
 */
public class Filter {

    public String removeWhiteSpace(String line){

        String line1 = line.replaceAll("\\s","");
        if("".equals(line1))
            return line1;

        if(line1.contains(Mark.SLASH.value())){
            String[] signs = line.split(Mark.SLASH.value());
            line1 = signs[0];
            line1 = line1.replaceAll("\\s","");
            return  line1;
        }

        return line1;
    }

}
