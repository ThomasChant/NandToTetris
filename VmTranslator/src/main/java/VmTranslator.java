import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.*;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class VmTranslator {
    private CodeWriter codeWriter;
    private VmCode vmCode;
    private static BufferedWriter bw;

    public VmTranslator(CodeWriter codeWriter, VmCode vmCode, BufferedWriter bw) {
        this.codeWriter = codeWriter;
        this.vmCode = vmCode;
        this.bw = bw;
    }

    public static void main(String[] args) throws IOException {
//          String path="H:\\sourceCode\\NandToTetris\\homework\\projects\\07\\StackArithmetic\\StackTest\\StackTest.vm";
          String path="H:\\sourceCode\\NandToTetris\\homework\\projects\\07\\MemoryAccess\\PointerTest\\PointerTest.vm";
//          String path="H:\\sourceCode\\NandToTetris\\homework\\projects\\07\\MemoryAccess\\BasicTest\\BasicTest.vm";
//          String path="H:\\sourceCode\\NandToTetris\\homework\\projects\\07\\MemoryAccess\\BasicTest\\BasicTest.vm";
//          String path="H:\\sourceCode\\NandToTetris\\homework\\projects\\07\\MemoryAccess\\BasicTest\\BasicTest.vm";
         File file = new File(path);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
          bw = new BufferedWriter(new FileWriter(new File(file.getPath().replace(".vm",".asm"))));
        bw.write("@256\nD=A\n@SP\nM=D\n");
        callHandler();
          translateFile(path);
//        File file = new File("H:\\sourceCode\\NandToTetris\\VmTranslator\\src\\main\\java\\VmTranslator.java");
//        System.out.println(file.getName().substring(0,file.getName().indexOf(".")));
//
//        System.out.println("fsadsa//er".indexOf("//"));
//        System.out.println("fsadsa//er".substring(0, 6));
    }

    private static void callHandler() throws IOException
    {
        // PUSH return-address
        bw.write("@return-address0\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        // Push LCL
        bw.write("@LCL\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        // Push ARG
        bw.write("@ARG\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        // Push THIS
        bw.write("@THIS\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        // Push THAT
        bw.write("@THAT\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        // ARG = SP-m-5
        bw.write("@5\nD=A\n@SP\nD=M-D\n@ARG\nM=D\n");
        // LCL = SP
        bw.write("@SP\nD=M\n@LCL\nM=D\n");
        // goto f
        bw.write("@Sys.init$Sys.init\n");
        bw.write("0;JMP\n");
        // (return-address)
        bw.write("(return-address0)\n");
    }

    public static void translateFile(String filename) {

        try {
            String name = filename.substring(0,filename.indexOf("."));
            VmCode vmCode = new VmCode(filename);
            CodeWriter codeWriter = new CodeWriter(bw,vmCode,name);
            codeWriter.convertToAssembly();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

class VmCode{
    String[] arr;
    int curr_index;

    VmCode(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line,str="";
        while ((line=br.readLine())!=null){
            int index = line.indexOf("//");
            if(index!=-1){
                line = line.substring(0,index);
            }
            if(!line.matches("^\\s*$")){
                str = str.concat(line+"\n");
            }
        }
        arr = str.split("\\n");
        curr_index=0;
    }

    public boolean hasMoreCommand(){
        return curr_index<arr.length;
    }

    public void advance(){
        curr_index++;
    }

    public String nextCommand(){

        return arr[curr_index];
    }
}

/*
  阶段1 目标
  实现逻辑运算和push/pop内存操作
  思路
  1 对vm文件进行处理，并转化为数组
  2 获取每一行代码，对其进行处理
    如果push则用pushHandle方法处理
    如果pop则用popHandle方法处理
    默认用athimaticHandle方法处理
 */
