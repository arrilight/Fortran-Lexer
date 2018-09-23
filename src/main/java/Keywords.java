import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to represent the keywords that are used in the FORTRAN 77, 90/95
 */

public class Keywords {
    private ArrayList<String> keywords;

    Keywords() throws IOException {
        initializeKeywords();

    }

    public boolean isKeyword(String value) {
        return keywords.contains(value);
    }

    private void initializeKeywords() throws IOException {
        keywords = new ArrayList<String>();
        String keyword;
        BufferedReader reader = new BufferedReader(new FileReader("keywords.txt"));
        while ((keyword = reader.readLine()) != null) {
            keywords.add(keyword);
        }
    }
}
