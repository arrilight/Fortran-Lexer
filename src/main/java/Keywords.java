import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Keywords {
    private HashMap<String, Boolean> keywords;
    private ArrayList<String> keywordsList;

    Keywords() throws IOException {
        initializeKeywords();
        for (String keyword: keywordsList) {
            keywords.put(keyword, false);
        }

    }

    public boolean isKeyword(String value) {
        return keywords.containsKey(value);
    }

    public boolean isComposite(String value) {
        return keywords.get(value);
    }

    private void initializeKeywords() throws IOException {
        keywordsList = new ArrayList<String>();
        keywords = new HashMap<String, Boolean>();
        String keyword;
        BufferedReader reader = new BufferedReader(new FileReader("keywords.txt"));
        while ((keyword = reader.readLine()) != null) {
            keywordsList.add(keyword);
        }
    }
}
