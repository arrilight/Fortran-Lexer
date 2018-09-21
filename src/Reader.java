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
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.charAt(5) == ' ') {
                prLine = line;
                return line;
            } else {
                prLine += line.substring(6, line.length());
                return prLine;
            }
        } else
            return null;

    }

    public boolean hasNextLine() {
        if (scanner.hasNextLine()) {
            return true;
        } else return false;
    }
}