import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by chentao on 2016/10/19 0019.
 */
public class CodeReader {

    public BufferedReader readFile(String path) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
    }

    public List<String> readCodes(String path,Filter filter){

        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = readFile(path);
            String line = null;
            while ((line=reader.readLine())!=null){
                line = filter.removeWhiteSpace(line);
                if(!"".equals(line))
                    lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  lines;
    }

    public static void main(String[] args) {
        CodeReader codeReader = new CodeReader();
        List<String> line = codeReader.readCodes("E:\\nand2tetris\\assembler\\RectL.asm", new Filter());
        System.out.println(line.toString());
    }
}
