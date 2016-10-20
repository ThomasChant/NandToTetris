import java.util.HashMap;
import java.util.Map;

/**
 * Created by chentao on 2016/10/18 0018.
 */
public class PreSymbol {
    public static final Map<String,Integer> PREDEFINE_SYMBOL = new HashMap<>() ;

    static {
        PREDEFINE_SYMBOL.put("R0",0);
        PREDEFINE_SYMBOL.put("R1",1);
        PREDEFINE_SYMBOL.put("R2",2);
        PREDEFINE_SYMBOL.put("R3",3);
        PREDEFINE_SYMBOL.put("R4",4);
        PREDEFINE_SYMBOL.put("R5",5);
        PREDEFINE_SYMBOL.put("R6",6);
        PREDEFINE_SYMBOL.put("R7",7);
        PREDEFINE_SYMBOL.put("R8",8);
        PREDEFINE_SYMBOL.put("R9",9);
        PREDEFINE_SYMBOL.put("R10",10);
        PREDEFINE_SYMBOL.put("R11",11);
        PREDEFINE_SYMBOL.put("R12",12);
        PREDEFINE_SYMBOL.put("R13",13);
        PREDEFINE_SYMBOL.put("R14",14);
        PREDEFINE_SYMBOL.put("R15",15);

        PREDEFINE_SYMBOL.put("SCREEN",16384);
        PREDEFINE_SYMBOL.put("KBD",24576);
        PREDEFINE_SYMBOL.put("SP",0);
        PREDEFINE_SYMBOL.put("LCL",1);
        PREDEFINE_SYMBOL.put("ARG",2);
        PREDEFINE_SYMBOL.put("THIS",3);
        PREDEFINE_SYMBOL.put("THAT",4);
    }
    
}
