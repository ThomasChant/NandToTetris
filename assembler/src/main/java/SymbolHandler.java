import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by chentao on 2016/10/20 0020.
 */
public class SymbolHandler {

    public List<String> handlePassOne(SymbolTable symbolTable,List<String> lines){
        lines = new CopyOnWriteArrayList<>(lines);
        for(int i=0;i<lines.size();i++){
            String lable = "";

            while (isLable(lines.get(i))){
                int index1 = lines.get(i).indexOf(Mark.LEFT_PARENTHESE.value());
                int index2 = lines.get(i).indexOf(Mark.RIGHT_PARENTHESE.value());
                lable = lines.get(i).substring(index1+1,index2);
                lines.remove(i);
                symbolTable.put(lable, i);
            }
        }
        return lines;
    }

    public boolean isLable(String line){
        int index1 = line.indexOf(Mark.LEFT_PARENTHESE.value());
        int index2 = line.indexOf(Mark.RIGHT_PARENTHESE.value());
        if(index1!=-1 && index2!=-1 && index1<index2){
            return true;
        }
        return false;
    }


    public void handlePassTwo(SymbolTable symbolTable,List<String> lines){
        int n = 16;
        for(String line : lines){
            if(line.contains(Mark.AT.value())){
                int index = line.indexOf(Mark.AT.value());
                String symbol = line.substring(index+1);
                if(symbolTable.contains(symbol)){
                    continue;
                }else {
                    symbolTable.put(symbol,n);
                }
                n++;
            }
        }
    }



}
