import java.io.IOException;

public class Lexer {
    private int pointer = 0;
    private Keywords keywords;

    public Lexer() throws IOException {
        keywords = new Keywords();
    }

    void startLexicalAnalysis() {
        Reader reader = new Reader("in.txt");
        Writer writer = new Writer("out.txt");
        String line;
        while ((line = reader.nextLine()) != null) {
            pointer = 0;
            while (pointer < line.length() && line.length() > 5) {
                Token token;
                if ((token = nextToken(line)) != null) {
                    writer.write(token);
                }

            }
        }

    }

    private Token nextToken(String line) {
        if (pointer <= 5) {
            Token label;
            if ((label = findLabel(line)) != null) {
                return label;
            }
        }
        if (line.charAt(pointer) == ' ') {
            pointer++;
        }
        if (Character.isLetter(line.charAt(pointer))) {
            Token keyword;
            if ((keyword = findKeywordOrIdentifier(line)) != null) {
                return keyword;
            }
        }
        pointer = line.length();

        return null;

    }

    private Token findLabel(String line) {
        StringBuilder label = new StringBuilder();
        for (int i = pointer; i <= 5; i++) {
            int num = Character.getNumericValue(line.charAt(i));
            if (num >= 0 && num <= 9) {
                label.append(line.charAt(i));
            }
        }
        pointer = 6;
        if (!label.toString().equals("")) {
            return new Token(TokenType.LABEL, label.toString());
        }
        return null;
    }

    private Token findKeywordOrIdentifier(String line) {
        StringBuilder word = new StringBuilder();
        while(pointer < line.length() && Character.isLetter(line.charAt(pointer))) {
            word.append(line.charAt(pointer));
            pointer++;
        }
        if (keywords.isKeyword(word.toString())) {
            if (!keywords.isComposite(word.toString())) {
                return new Token(TokenType.KEYWORD, word.toString());
            }
        }
        else {
            return new Token(TokenType.IDENTIFIER, word.toString());
        }
        pointer = line.length();
        return null;

    }
}
