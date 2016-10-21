import java.io.IOException;
import java.util.List;

/**
 * Created by chentao on 2016/10/21 0021.
 */
public class Assembler {

    public void translate(String path){
        try {
            //read assemble file and filter white space and comment line
            CodeReader reader = new CodeReader();
            List<String> lines = reader.readCodes(path);

            //add symbol to symbol table
            SymbolHandler handler = new SymbolHandler();
            //create and init sysmbol table
            SymbolTable symbolTable = new SymbolTable();

            //add label to symbol table and remove the label from lines
            lines = handler.handlePassOne(symbolTable,lines);

            //add var sysmbol to symbol table
            handler.handlePassTwo(symbolTable,lines);

            //parse hack assemble code to binary machine code
            Parser parser = new Parser(symbolTable);
            lines = parser.parseCode(lines);

            //white the machine code to the .hack file
            path = path.replace(".asm",".hack");
            CodeWriter codeWriter = new CodeWriter(path);
            codeWriter.writeCode(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Assembler assembler = new Assembler();
        assembler.translate("H:\\learn\\NandToTetris\\assembler\\Pong.asm");
    }
}
