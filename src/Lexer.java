public class Lexer {
    private int pointer = 0;

    public void startLexicalAnalysis() {
        Reader reader = new Reader("in.txt");
        String line;
        while ((line = reader.nextLine()) != null) {
            while (pointer < line.length()) {
                nextToken(line);
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
        switch(line.charAt(pointer)) {

        }

        return null;

    }

    private Token findLabel(String line) {
        StringBuilder label = new StringBuilder();
        for (int i = pointer; i <= 5; i++) {
            int num = Character.getNumericValue(line.charAt(i)) - 48;
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
}
