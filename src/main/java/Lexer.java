import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to represent the lexer for FORTRAN 90/95 language.
 */

public class Lexer {
    private int pointer = 0; // index of the character in the line
    private Keywords keywords; // structure of keywords that FORTRAN 90/95 uses
    private Logical logical; // structure of logical operators that FORTRAN 90/95 uses
    private Operator operator; // structure of operators that FORTRAN 90/95 uses
    private String line; // current line
    private String lower; // lower case of the current line

    /**
     * The constructor for the lexer initializes the structures.
     * @throws IOException in case keywords.txt or operators.txt is not found
     */

    public Lexer() throws IOException {
        keywords = new Keywords();
        logical = new Logical();
        operator = new Operator();
    }

    /**
     * Function that starts lexical analysis for a given line.
     */
    ArrayList<Token> startLexicalAnalysis(String line) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        this.line = line;
        this.lower = this.line.toLowerCase();
        pointer = 0;
        while (pointer < line.length() && line.length() > 5) {
            Token token;
            if ((token = nextToken()) != null) {
                tokens.add(token);
            }
        }
        return tokens;

    }

    /**
     * Function that returns next token in the line.
     * @return Token if it was able to found one, null otherwise
     */
    Token nextToken() {
        if (isAComment()) { // if this line is a comment
            pointer = line.length();
            return null; // we don't do the analysis
        }
        while (pointer < line.length() && line.charAt(pointer) == ' ') {
            pointer++; // skip all spaces
        }
        if (pointer == line.length())  // if nothing left to analyze
            return null;  // return null
        if (Character.isLetter(line.charAt(pointer))) { // if char is a letter
            Token keyword;
            // find keyword or identifier
            if ((keyword = findKeywordOrIdentifier()) != null) {
                return keyword;
            }
        }
        Token result;
        if ((result = findOperator()) != null) {
            // find operators
            return result;
        }
        if ((result = findLiteral()) != null) {
            // find literals
            return result;
        }

        // code should never reach this point
        System.out.println("Something probably went wrong!");
        return null;

    }

    /**
     * This function is used to find keywords, identifiers or literals in the source code.
     * @return token if found one, null otherwise
     */
    Token findKeywordOrIdentifier() {
        Token result;
        if ((result = findLiteral()) != null) { // find a literal
            return result;
        }
        StringBuilder word = new StringBuilder();
        int p = pointer;
        while (p < line.length() && line.charAt(p) != ' '
                && !operator.isOperator(line.charAt(p))) {
            // append the word until operator or a blank space
            word.append(line.charAt(p));
            p++;
        }
        if (keywords.isKeyword(stringify(word))) { //if this word is a keyword
            if ((result = findCompositeKeyword(stringify(word), p)) != null) {
                // if this keyword consists of multiple words (END DO, for example)
                return result;
            }
            pointer = p;
            return new Token(TokenType.KEYWORD, word.toString());
        } else { // if no such keywords exists, that's an identifier
            pointer = p;
            return new Token(TokenType.IDENTIFIER, word.toString());
        }
    }

    /**
     * This function is used to find composite keywords based on the first word
     * in that keyword.
     * @param word the first word
     * @param p pointer
     * @return token if a composite keyword if found, null otherwise
     */
    Token findCompositeKeyword(String word, int p) {
        if (word.equals("else")) {
            p++;
            while (p < line.length() && line.charAt(p) == ' ')
                p++;
            if (p + 1 < line.length() &&
                    lower.substring(p, p + 2).equals("if")) {
                pointer = p + 2;
                return new Token(TokenType.KEYWORD, "ELSE IF");
            }
        }
        if (word.equals("block")) {
            p++;
            while (line.charAt(p) == ' ')
                p++;
            if (p + 3 < line.length() &&
                    lower.substring(p, p + 4).equals("data")) {
                pointer = p + 2;
                return new Token(TokenType.KEYWORD, "ELSE IF");
            }
        }
        if (word.equals("go")) {
            p++;
            while (line.charAt(p) == ' ')
                p++;
            if (p + 1 < line.length() &&
                    lower.substring(p, p + 2).equals("to")) {
                pointer = p + 2;
                return new Token(TokenType.KEYWORD, "GO_TO");
            }
        }
        if (word.equals("end")) {
            p++;
            while (line.charAt(p) == ' ')
                p++;
            if (p + 1 < line.length() &&
                    lower.substring(p, p + 2).equals("do")) {
                pointer = p + 2;
                return new Token(TokenType.KEYWORD, "END_DO");
            }
        }
        return null;
    }

    /**
     * This function is used to find operators in the code.
     * @return token with an operator if found, null otherwise
     */
    Token findOperator() {
        int length = line.length();
        int p = pointer;
        if (p < length) {
            switch (line.charAt(p)) { //swtich in the character
                case '=':
                    if (p < length + 1) {
                        if (line.charAt(p + 1) == '=') {
                            pointer += 2;
                            return new Token(TokenType.OPERATOR, "COND_EQUAL");
                        }
                        if (line.charAt(p + 1) == '>') {
                            pointer += 2;
                            return new Token(TokenType.OPERATOR, "POINTER");
                        }
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "EQUAL");
                case '>':
                    if (p < length + 1 && line.charAt(p + 1) == '=') {
                        pointer += 2;
                        return new Token(TokenType.OPERATOR, "BIGGER_OR_EQ");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "CLOSING_ANGLE__BRACKET");
                case '<':
                    if (p < length + 1 && line.charAt(p + 1) == '=') {
                        pointer += 2;
                        return new Token(TokenType.OPERATOR, "LESS_OR_EQ");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "OPENING_ANGLE_BRACKET");
                case '(':
                    pointer++;
                    return new Token(TokenType.SEPARATOR, "LEFT_PAREN");
                case ')':
                    pointer++;
                    return new Token(TokenType.SEPARATOR, "RIGHT_PAREN");
                case '*':
                    if (p + 1 < length && line.charAt(p + 1) == '*') {
                        pointer += 2;
                        return new Token(TokenType.OPERATOR, "POWER");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "ASTERISK");
                case '+':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "PLUS");
                case '-':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "MINUS");
                case '/':
                    if (p + 1 < length && line.charAt(p + 1) == '=') {
                        pointer += 2;
                        return new Token(TokenType.OPERATOR, "NOT_EQUAL");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "DIVIDE");
                case '.':
                    Token result;
                    if ((result = findLogicalOperator()) != null) {
                        return result;
                    }
                case ',':
                    pointer++;
                    return new Token(TokenType.SEPARATOR, "COMMA");
                case '$':
                    pointer++;
                    return new Token(TokenType.SEPARATOR, "DOLLAR_DELIMITER");
                case ':':
                    if (p + 1 < length && line.charAt(p + 1) == ':') {
                        pointer += 2;
                        return new Token(TokenType.OPERATOR, "TYPE_ASSIGNMENT");
                    }
                    pointer++;
                    return new Token(TokenType.OPERATOR, "COLUMN_DELIMITER");
                case '%':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "SPECIAL_FUNCTION");
                case '?':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "REQUEST_NAME");
                case '\\':
                    pointer++;
                    return new Token(TokenType.OPERATOR, "ESCAPE_CHARACTER");
                case '!':
                    // if ! has been encountered, ignore the rest of the line
                    pointer = line.length();
                    return new Token(TokenType.OPERATOR, "COMMENT_SYMBOL");
                case '&':
                    pointer++;
                    return new Token(TokenType.SEPARATOR, "CONCAT");
            }
        }
        // if no operator was found
        return null;
    }

    /**
     * This function basically takes the string builder, converts it to the string
     * and makes it lower case. This function is used to support multiple FORTRAN language versions
     * (such as 77, 90 and 95)
     * @param word is the stringbuilder that needs to be stringified.
     * @return lower-case string
     */
    private String stringify(StringBuilder word) {
        return word.toString().toLowerCase();
    }

    /**
     * This function is used to find logical operators in FORTRAN 77
     * @return
     */
    Token findLogicalOperator() {
        int p = pointer + 1;
        int length = line.length();
        StringBuilder word = new StringBuilder();
        // operators have a format .OPERATOR.
        while (p < length && line.charAt(p) != '.') {
            word.append(line.charAt(p));
            p++;
        }
        if (logical.isLogical(stringify(word))) {
            if (logical.isLogicalLiteral(stringify(word))) {
                // if the value is .TRUE. or .FALSE.
                pointer = p + 1;
                return new Token(TokenType.LITERAL, word.toString());
            }
            pointer = p + 1;
            return new Token(TokenType.OPERATOR, word.toString());
        }
        return null;
    }

    /**
     * This function is used to find digit-based and string-based literals
     * @return token if one is found, null otherwise
     */
    Token findLiteral() {
        int p = pointer;
        // If we see ' or "
        if (line.charAt(p) == '\"' || line.charAt(p) == '\'') {
            // find string literal
            return findStringLiteral(line.charAt(p));
        }
        // if this could be a digit literal
        if (isPotentialDigitLiteral(lower.charAt(p))) {
            Token digitLiteral;
            if ((digitLiteral = findDigitLiteral()) != null)
                return digitLiteral;
        }
        // if nothing is found
        return null;
    }

    /**
     * This function is used to find string literals in the code
     * @param div is either ' or ", since FOTRAN treats both as literals.
     * @return token if the literal is found, null otherwise
     */
    Token findStringLiteral(char div) {
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

    /**
     * This function is used to find complicated digit literals in FORTRAN.
     * @return token if literal is found, null otherwise
     */
    Token findDigitLiteral() {
        int p = pointer;
        int numOfLetters = 0; // maximum 1 letter is the literal
        int length = line.length();
        StringBuilder literal = new StringBuilder();
        while (p < length) {
            if (Character.isLetter((lower.charAt(p)))) {
                if (isDigitLiteralChar(lower.charAt(p))) {
                    // to keep the track of letters
                    numOfLetters++;
                } else
                    // if more than 1 letter
                    return null;
            }
            if (lower.charAt(p) == ' ') {
                pointer++;
                break;
            }
            if (operator.isOperator(lower.charAt(p)) && lower.charAt(p) != '.') {
                if (literal.toString().equals("")) {
                    return null;
                } else
                    break;
            }
            if (numOfLetters > 1)
                return null;
            literal.append(line.charAt(p));
            p++;
        }
        pointer = p;
        return new Token(TokenType.LITERAL, literal.toString());
    }

    /**
     * Function checks whether a char is a digit. Used just for code
     * minimization.
     * @param c char that needs to be checked
     * @return true if it is a digit, false otherwise
     */
    boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    /**
     * This function is used to check whether the char in the potential literal
     * is one of the allowed ones by the language.
     * @param c is the char that needs to be checked
     * @return true if it is allowed, false otherwise
     */
    boolean isDigitLiteralChar(char c) {
        return c == '.' || c == 'e' || c == 'i'
                || c == 'a' || c == 'f' || c == 'd';
    }

    /**
     * Checks whether this character might belong to the digit literal or not
     * @param c char that needs to be checked
     * @return true if it might, false otherwise
     */
    boolean isPotentialDigitLiteral(char c) {
        return isDigit(c) || isDigitLiteralChar(c);
    }

    /**
     * This function checks whether the line is a comment
     * @return
     */
    boolean isAComment() {
        int p = 0;
        // skip empty symbols
        while (p + 1 < line.length() && line.charAt(p + 1) == ' ') {
            p++;
        }
        // if the first symbol in the line is the comment symbol
        if (p + 1 < line.length())
            return line.charAt(p + 1) == '!';
        return false;
    }
}
