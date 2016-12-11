import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class CodeWriter {

    private BufferedWriter bw;
    private String[] line;
    private VmCode vmCode;
    private String filename;
    private int arthmaticCounter=1;

    public CodeWriter(BufferedWriter bw, VmCode vmCode, String filename) {
        this.bw = bw;
        this.vmCode = vmCode;
        this.filename = filename;
    }

    public void convertToAssembly() throws IOException {
        while (vmCode.hasMoreCommand()){
             line = vmCode.nextCommand().split(" ");
             switch (line[0]){
                 case "push": pushHandle();break;
                 case "pop": popHandle();break;
                 default: athmaticHandle();
             }
            vmCode.advance();
        }
        bw.close();

    }
    public void pushHandle() throws IOException {
        int num = Integer.parseInt(line[2]);
        switch (line[1]){
            case "constant":
                bw.write("@"+num+"\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                break;
            case "local":
                bw.write("@"+num+"\nD=A\n@LCL\nA=M+D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                break;
            case "argument":
                bw.write("@"+num+"\nD=A\n@ARG\nA=M+D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                break;
            case "this":
                bw.write("@"+num+"\nD=A\n@THIS\nA=M+D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                break;
            case "that":
                bw.write("@"+num+"\nD=A\n@THAT\nA=M+D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                break;
            case "temp":
                if(num>=8)
                    System.out.println("push temp 参数超过了8");
                bw.write("@"+(num+5)+"\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                break;
            case "pointer":
                bw.write("@"+(num+3)+"\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                break;
            case "static":
                bw.write("@"+filename+"."+num+"\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                break;
            case "nothing":
                bw.write("@"+num+"\nM=A\n@SP\nM=M+D\n");
                break;
        }
    }
    public void popHandle() throws IOException {
        int num = Integer.parseInt(line[2]);
        switch (line[1]){
            case "local":
                bw.write("@"+num+"\nD=A\n@LCL\nA=M\nD=D+A\n@temptory\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@temptory\nA=M\nM=D\n");
                break;
            case "argument":
                bw.write("@"+num+"\nD=A\n@ARG\nA=M\nD=D+A\n@temptory\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@temptory\nA=M\nM=D\n");
                break;
            case "this":
                bw.write("@"+num+"\nD=A\n@THIS\nA=M\nD=D+A\n@temptroy\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@temptory\nA=M\nM=D\n");
                break;
            case "that":
                bw.write("@"+num+"\nD=A\n@THAT\nA=M\nD=D+A\n@temptory\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@temptory\nA=M\nM=D\n");
                break;
            case "temp":
                if(num>=8)
                    System.out.println("pop temp 参数超过了8");
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@"+(num+5)+"\nM=D\n");
                break;
            case "pointer":
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@"+(num+3)+"\nM=D\n");
                break;
            case "static":
                bw.write("@"+filename+"."+num+"\nD=A\n@temptory\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@temptory\nA=M\nM=D\n");
                break;
            case "nothing":
                bw.write("");
                break;
        }
    }
    public void athmaticHandle() throws IOException {
        switch (line[0]){
            case "add":
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@SP\nM=M-1\nA=M\nM=M+D\n@SP\nM=M+1\n");
                break;
            case "sub":
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@SP\nM=M-1\nA=M\nM=M-D\n@SP\nM=M+1\n");
                break;
            case "neg":
                bw.write("@SP\nM=M-1\nA=M\nM=-M\n@SP\nM=M+1\n");
                break;
            case "eq":
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@SP\nM=M-1\nA=M\nD=M-D\n@isTrue"+arthmaticCounter+"\nD;JEQ\n" +
                        "@isFalse"+arthmaticCounter+"\n0;JMP\n@continue"+arthmaticCounter+"\n0;JMP\n(isTrue"+arthmaticCounter+")\n@SP\nA=M\nM=-1\n" +
                        "(isFalse"+arthmaticCounter+")\n@SP\nA=M\nM=0\n(continue"+arthmaticCounter+")\n@SP\nA=M\nM=M+1\n");
                arthmaticCounter++;
                break;
            case "gt":
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@SP\nM=M-1\nA=M\nD=M-D\n@isTrue"+arthmaticCounter+"\nD;JGT\n" +
                        "@isFalse"+arthmaticCounter+"\n0;JMP\n@continue"+arthmaticCounter+"\n0;JMP\n(isTrue"+arthmaticCounter+")\n@SP\nA=M\nM=-1\n" +
                        "(isFalse"+arthmaticCounter+")\n@SP\nA=M\nM=0\n(continue"+arthmaticCounter+")\n@SP\nA=M\nM=M+1\n");
                arthmaticCounter++;
                break;
            case "lt":
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@SP\nM=M-1\nA=M\nD=M-D\n@isTrue"+arthmaticCounter+"\nD;JLT\n" +
                        "@isFalse"+arthmaticCounter+"\n0;JMP\n@continue"+arthmaticCounter+"\n0;JMP\n(isTrue"+arthmaticCounter+")\n@SP\nA=M\nM=-1\n" +
                        "(isFalse"+arthmaticCounter+")\n@SP\nA=M\nM=0\n(continue"+arthmaticCounter+")\n@SP\nA=M\nM=M+1\n");
                arthmaticCounter++;
                break;
            case "and":
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@SP\nM=M-1\nA=M\nM=M&D\n@SP\nM=M+1\n");
                break;
            case "or":
                bw.write("@SP\nM=M-1\nA=M\nD=M\n@SP\nM=M-1\nA=M\nM=M|D\n@SP\nM=M+1\n");
                break;
            case "not":
                bw.write("@SP\nM=M-1\nA=M\nM=!M\n@SP\nM=M+1\n");
                break;
        }

    }



}
