import java.util.HashMap;
import java.util.Map;

/**
 * Created by chentao on 2016/10/18 0018.
 */
public class SymbolTable {
    private final Map<String,Integer> symbolPairs;

    public SymbolTable(){
        symbolPairs = new HashMap<>();
        symbolPairs.putAll(PreSymbol.PREDEFINE_SYMBOL);
    }

    public boolean contains(String symbol){
        return symbolPairs.containsKey(symbol);
    }

    public int get(String symbol){//todo
        return symbolPairs.get(symbol);
    }

    public void put(String sysbol,int value){
        if(!contains(sysbol)){
            symbolPairs.put(sysbol,value);
            Integer a = symbolPairs.get(sysbol);
        }

    }







}
