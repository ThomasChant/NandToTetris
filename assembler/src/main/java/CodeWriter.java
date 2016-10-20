import java.io.*;
import java.util.List;

/**
 * Created by chentao on 2016/10/19 0019.
 */
public class CodeWriter {


    private PrintWriter writer;

    public CodeWriter(String path,boolean autoflush) throws IOException{
        FileOutputStream fos = new FileOutputStream(path);
        this.writer = new PrintWriter(fos,true);

    }

    public void writeCode(List<String> lines) throws FileNotFoundException {
        for(String line : lines){
            writer.print(line);
            writer.println();
        }
    }
}
