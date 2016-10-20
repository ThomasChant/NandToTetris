import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentao on 2016/10/18 0018.
 */
public class Parser {

    private SymbolTable symbolTable;

    public Parser(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public List<String> parseCode(List<String> assembleCode){
        List<String> machineCodes = new ArrayList<>();
        for(String code : assembleCode){
            if(code.contains(Mark.AT.value())){  //A-instruction
                code = getAddress(code);
            }else {//C-instruction
                String dest = getDest(code);
                String comp = getComp(code);
                String jump = getJump(code);

                String destCode = CInstructionMap.DEST.get(dest);
                String compCode =  CInstructionMap.COMP.get(comp);
                String jumpCode = CInstructionMap.JUMP.get(jump);
                code = CInstructionMap.PREFIX+compCode+destCode+jumpCode;
            }
            machineCodes.add(code);
        }
        return machineCodes;
    }


    public String getAddress(String line){
        line =  line.substring(1);
        int addr = 0;
        try {
            addr = Integer.valueOf(line);
        }catch (NumberFormatException e){
            addr = symbolTable.get(line);
        }
        String binaryStr = Integer.toBinaryString(addr);
        char[] binaryCode = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'};
        binaryStr.getChars(0, binaryStr.length(), binaryCode, binaryCode.length - binaryStr.length()); //replace last char sequense with the binaryStr's char
        return new String(binaryCode);
    }

    public String getComp(String line){
        int index1 = line.indexOf(Mark.EQUAL.value());
        int index2 = line.indexOf(Mark.SEMICOLON.value());
        String comp ;
        if(index1!=-1 ){
            if(index2 != -1)
               comp = line.substring(index1,index2-1);
            else
                comp = line.substring(index1+1);
        }else {
            if(index2 >1 )
                comp = line.substring(0,index2-1);
            else
                comp = line.substring(0,1);
        }
        return comp;
    }

    public String getDest(String line){
        int index = line.indexOf(Mark.EQUAL.value());
        String dest = "";
        if(index !=-1){
            dest = line.substring(0,index);
        }
        return dest;
    }

    public String getJump(String line){
        int index = line.indexOf(Mark.SEMICOLON.value());
        String jump = "";
        if(index !=-1){
            jump = line.substring(index+1);
        }
        return jump;
    }
}
