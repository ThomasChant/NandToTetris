import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentao on 2016/10/19 0019.
 */
public class CodeReader {

    public BufferedReader readFile(String path) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
    }

    public List<String> readCodes(String path) throws IOException {
        Filter filter = new Filter();
        List<String> lines = new ArrayList<>();
        BufferedReader reader = readFile(path);
        String line = null;
        while ((line=reader.readLine())!=null){
            line = filter.removeWhiteSpace(line);
            if(!"".equals(line))
                lines.add(line);
        }

        return  lines;
    }

    public static void main(String[] args) throws IOException {
        CodeReader codeReader = new CodeReader();
        List<String> line = codeReader.readCodes("E:\\nand2tetris\\assembler\\RectL.asm");
        System.out.println(line.toString());
    }

    private class Filter{
        public String removeWhiteSpace(String line){

            String line1 = line.replaceAll("\\s","");
            if("".equals(line1))
                return line1;

            if(line1.contains(Mark.SLASH.value())){
                String[] signs = line.split(Mark.SLASH.value());
                line1 = signs[0];
                line1 = line1.replaceAll("\\s","");
                return  line1;
            }

            return line1;
        }
    }
}
