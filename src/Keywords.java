import java.io.BufferedReader;
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
        keywords.replace("block", false, true);
        keywords.replace("else", false, true);

    }

    public boolean isKeyword(String value) {
        return keywords.containsKey(value);
    }

    private void initializeKeywords() throws IOException {
        keywordsList = new ArrayList<String>();
        String keyword;
        BufferedReader reader = new BufferedReader(new FileReader("keywords.txt"));
        while ((keyword = reader.readLine()) != null) {
            keywordsList.add(keyword);
        }
    }
}
