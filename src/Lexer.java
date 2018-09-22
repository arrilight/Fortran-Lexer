import java.io.IOException;

public class Lexer {
    private int pointer = 0;
    private Keywords keywords;
    private Logical logical;

    public Lexer() throws IOException {
        keywords = new Keywords();
        logical = new Logical();
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
            writer.writeEmptyLine();
        }

    }

    private Token nextToken(String line) {
        if (pointer <= 5) {
            Token label;
            if ((label = findLabel(line)) != null) {
                return label;
            }
        }
        while (line.charAt(pointer) == ' ') {
            pointer++;
        }
        if (Character.isLetter(line.charAt(pointer))) {
            Token keyword;
            if ((keyword = findKeywordOrIdentifier(line)) != null) {
                return keyword;
            }
        }
        Token result;
        if ((result = findOperator(line)) != null) {
            return result;
        }
        if ((result = findLiteral(line)) != null) {
            return result;
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
        int p = pointer;
        while(p < line.length() && line.charAt(p) != ' ' && line.charAt(p) != ',') {
            word.append(line.charAt(p));
            p++;
        }
        if (keywords.isKeyword(stringify(word))) {
            if (!keywords.isComposite(stringify(word))) {
                pointer = p;
                return new Token(TokenType.KEYWORD, word.toString());
            }
        }
        else {
            pointer = p;
            return new Token(TokenType.IDENTIFIER, word.toString());
        }
        pointer = line.length();
        return null;

    }

    private Token findOperator(String line) {
        int length = line.length();
        int p = pointer;
        if (p < length) {
            switch(line.charAt(p)) {
                case '=':
                    if (p < length + 1 && line.charAt(p + 1) == '=') {
                        pointer+=2;
                        return new Token(TokenType.OPERATOR, "COND_EQUAL");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "EQUAL");
                case '>':
                    if (p < length + 1 && line.charAt(p + 1) == '=') {
                        pointer+=2;
                        return new Token(TokenType.OPERATOR, "BIGGER_OR_EQ");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "BIGGER");
                case '<':
                    if (p < length + 1 && line.charAt(p + 1) == '=') {
                        pointer+=2;
                        return new Token(TokenType.OPERATOR, "LESS_OR_EQ");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "LESS");
                case '(':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "LEFT_PAREN");
                case ')':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "RIGHT_PAREN");
                case '*':
                    if (p - 1 > 0 && findLeftParen(line)) {
                        pointer++;
                        return new Token(TokenType.OPERATOR, "ASTERISK");
                    }
                    else if (p + 1 < length && line.charAt(p + 1) == '*') {
                        pointer+=2;
                        return new Token(TokenType.OPERATOR, "POWER");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "MULTIPLY");
                case '+':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "PLUS");
                case '-':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "MINUS");
                case '/':
                    if (p + 1 < length && line.charAt(p + 1) == '=') {
                        pointer+=2;
                        return new Token(TokenType.OPERATOR, "NOT_EQUAL");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "DIVIDE");
                case '.':
                    Token result;
                    if ((result = findLogicalOperator(line)) != null) {
                        return result;
                    }
                case ',':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "COMMA");



            }
        }
        return null;
    }

    private String stringify(StringBuilder word) {
        return word.toString().toLowerCase();
    }

    private boolean findLeftParen(String line) {
        int p = pointer - 1;
        while (p >= 5) {
            if (line.charAt(p) == ' ' || line.charAt(p) == '(') {
                if (line.charAt(p) == '(')
                    return true;
            }
            else {
                return false;
            }
            p--;
        }
        return false;
    }

    private Token findLogicalOperator(String line) {
        int p = pointer + 1;
        int length = line.length();
        StringBuilder word = new StringBuilder();
        while (p < length && line.charAt(p) != '.') {
            word.append(line.charAt(p));
            p++;
        }
        if (logical.isLogical(word.toString())) {
            if (logical.isLogicalLiteral(word.toString())) {
                pointer = p;
                return new Token(TokenType.LITERAL, word.toString());
            }
            pointer = p;
            return new Token(TokenType.OPERATOR, word.toString());
        }
        return null;
    }

    private Token findLiteral(String line) {
        int p = pointer;
        if (line.charAt(p) == '\"' || line.charAt(p) == '\'') {
            return findStringLiteral(line, line.charAt(p));
        }
        if (Character.isDigit(line.charAt(p))) {
            return findDigitLiteral(line);
        }
        return null;
    }

    private Token findStringLiteral(String line, char div) {
        int p = pointer;
        StringBuilder literal = new StringBuilder();
        literal.append(line.charAt(p));
        do {
            p++;
            literal.append(line.charAt(p));
        } while (line.charAt(p) != div);
        pointer = p + 1;
        return new Token(TokenType.LITERAL, literal.toString());
    }

    private Token findDigitLiteral(String line) {
        int p = pointer;
        int length = line.length();
        StringBuilder literal = new StringBuilder();
        while (p < length && Character.isDigit(line.charAt(p))) {
            literal.append(line.charAt(p));
            p++;
        }
        pointer = p;
        return new Token(TokenType.LITERAL, literal.toString());
    }
}
