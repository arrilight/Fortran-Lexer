import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    private String filename;

    Writer(String filename) throws IOException {
        this.filename = filename;
        FileWriter fw  = new FileWriter(filename, false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.print("");
        out.close();
    }

    public void write(Token token) throws IOException {
        FileWriter fw = new FileWriter(this.filename, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(token.toString());
        out.close();
    }

    public void writeEmptyLine() throws IOException {
        FileWriter fw = new FileWriter(this.filename, true);
        fw.write("-\n");
        fw.close();
    }
}
