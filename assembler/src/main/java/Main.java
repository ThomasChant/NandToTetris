import jdk.management.resource.internal.inst.FileInputStreamRMHooks;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        //1 去除空白行及注释行
        //2 无符号的汇编代码转化为机器指令，并输出
        //3 带符号的汇编代码转化为无符号的汇编代码

            testFilter();

//        String ab = "fasdfsf//fsdf";
//        int i = ab.indexOf("//");
//        System.out.println(ab.substring(0,i));
        String a = "@fdsf(aa)dd";
//        List<String> lines = new CopyOnWriteArrayList<>();
//        lines.add("fff");
//        lines.add("(ad)");
//        lines.add("cc");
//        lines.add("(ee)");
//        lines.add("tt");
//        lines.add("ss");
//        SymbolTable symbolTable = new SymbolTable();
//        for(int i=0;i<lines.size();i++){
//            if(lines.get(i).contains("(")) {
//                lines.remove(i);
//                symbolTable.addSysbol(lines.get(i),i);
//            }
//        }
        System.out.println(a.substring(1));
    }


    public static void testFilter() throws IOException {
        CodeReader codeReader = new CodeReader();
        List<String> lines = codeReader.readCodes("E:\\nand2tetris\\assembler\\Pong.asm", new Filter());

        System.out.println(lines.toString());
        SymbolTable symbolTable = new SymbolTable();

        SymbolHandler symbolHandler  = new SymbolHandler();
        lines = symbolHandler.handlePassOne(symbolTable,lines);

        symbolHandler.handlePassTwo(symbolTable,lines);


        Parser parser = new Parser(symbolTable);
        List<String> codes = parser.parseCode(lines);

        CodeWriter codeWriter = new CodeWriter("E:\\nand2tetris\\assembler\\Pong.hack",true);
        codeWriter.writeCode(codes);

    }
}
