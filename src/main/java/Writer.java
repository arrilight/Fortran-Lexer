import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    private String filename;

    Writer(String filename) {
        this.filename = filename;
        try {
            FileWriter fw  = new FileWriter(filename, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Token token) {
        try (FileWriter fw = new FileWriter(this.filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(token.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeEmptyLine() {
        try (FileWriter fw = new FileWriter(this.filename, true)) {
            fw.write("-\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
