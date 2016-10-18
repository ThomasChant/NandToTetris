import java.util.HashMap;
import java.util.Map;

/**
 * Created by chentao on 2016/10/18 0018.
 */
public class SymbolTable {
    private final Map<String,Integer> symbolPairs;

    public SymbolTable(Map<String,Integer> symbolPairs){
        this.symbolPairs = symbolPairs;
        symbolPairs.putAll(PreSymbol.PREDEFINE_SYMBOL);
    }

    public boolean containsSymbol(String symbol){
        return symbolPairs.containsKey(symbol);
    }

    public int getValue(String symbol){//todo
        return 0;
    }



}
