import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    private Scanner scanner;
    private String prLine;

    public Reader(String filename) {
        File file = new File(filename);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            scanner = null;
            System.out.println("File not found!");
        }
    }

    public String nextLine() {
        String line;
        if (scanner.hasNextLine() && (line = scanner.nextLine()).length() >= 6) {
            return line;
        }
        if (!hasNextLine())
            return null;
        return "";

    }

    public boolean hasNextLine() {
        if (scanner.hasNextLine()) {
            return true;
        } else return false;
    }
}