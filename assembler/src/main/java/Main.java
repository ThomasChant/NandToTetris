import jdk.management.resource.internal.inst.FileInputStreamRMHooks;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        //1 去除空白行及注释行
        //2 无符号的汇编代码转化为机器指令，并输出
        //3 带符号的汇编代码转化为无符号的汇编代码
       testFilter();
        String ab = "fasdfsf//fsdf";
        int i = ab.indexOf("//");
        System.out.println(ab.substring(0,i));

    }


    public static void testFilter() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\nand2tetris\\assembler\\src\\main\\java\\Main.java"),"utf-8"));
        String line = null;
        Filter filter = new Filter();
        List<String> lines = new ArrayList<>();
        while ((line=reader.readLine())!=null){
             line = filter.removeWhiteSpace(line);
             if(!"".equals(line))
                lines.add(line);
        }

    }
}
