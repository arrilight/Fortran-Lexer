import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 9/23/2018.
 */
public class LexerTest {
    Lexer testLexer;
    public LexerTest() throws IOException{
        testLexer = new Lexer();
    }
    @Test
    public void startLexicalAnalysis() throws Exception {
        String line = "subroutine main";
        Token token1 = new Token(TokenType.KEYWORD, "subroutine");
        Token token2 = new Token(TokenType.IDENTIFIER, "main");
        assertEquals(token1.getValue(), this.testLexer.startLexicalAnalysis(line).get(0).getValue());
        assertEquals(token1.getType(), this.testLexer.startLexicalAnalysis(line).get(0).getType());
        assertEquals(token2.getValue(), this.testLexer.startLexicalAnalysis(line).get(1).getValue());
        assertEquals(token2.getType(), this.testLexer.startLexicalAnalysis(line).get(1).getType());
        
        //more tests!!!

    }

    @Test
    public void nextToken() throws Exception {

    }

    @Test
    public void findKeywordOrIdentifier() throws Exception {

    }

    @Test
    public void findCompositeKeyword() throws Exception {

    }

    @Test
    public void findOperator() throws Exception {

    }

    @Test
    public void findLeftParen() throws Exception {

    }

    @Test
    public void findLogicalOperator() throws Exception {

    }

    @Test
    public void findLiteral() throws Exception {

    }

    @Test
    public void findStringLiteral() throws Exception {

    }

    @Test
    public void isDigit() throws Exception {
        assertEquals(true, testLexer.isDigit('4'));
        assertEquals(true, testLexer.isDigit('7'));
        assertEquals(true, testLexer.isDigit('0'));
        assertEquals(false, testLexer.isDigit('f'));
        assertEquals(false, testLexer.isDigit('*'));
        assertEquals(false, testLexer.isDigit('!'));
    }

    @Test
    public void isDigitLiteralChar() throws Exception {
        assertEquals(true, testLexer.isDigitLiteralChar('.'));
        assertEquals(true, testLexer.isDigitLiteralChar('e'));
        assertEquals(true, testLexer.isDigitLiteralChar('i'));
        assertEquals(true, testLexer.isDigitLiteralChar('a'));
        assertEquals(true, testLexer.isDigitLiteralChar('f'));
        assertEquals(true, testLexer.isDigitLiteralChar('d'));
        assertEquals(false, testLexer.isDigitLiteralChar('*'));
        assertEquals(false, testLexer.isDigitLiteralChar('!'));
        assertEquals(false, testLexer.isDigitLiteralChar('j'));
        assertEquals(false, testLexer.isDigitLiteralChar('q'));
        assertEquals(false, testLexer.isDigitLiteralChar('1'));
        assertEquals(false, testLexer.isDigitLiteralChar('5'));
    }

    @Test
    public void isPotentialDigitLiteral() throws Exception {
        assertEquals(true, testLexer.isDigit('4'));
        assertEquals(true, testLexer.isDigit('7'));
        assertEquals(true, testLexer.isDigit('0'));
        assertEquals(false, testLexer.isDigit('f'));
        assertEquals(false, testLexer.isDigit('*'));
        assertEquals(false, testLexer.isDigit('!'));
        assertEquals(true, testLexer.isDigitLiteralChar('.'));
        assertEquals(true, testLexer.isDigitLiteralChar('e'));
        assertEquals(true, testLexer.isDigitLiteralChar('i'));
        assertEquals(true, testLexer.isDigitLiteralChar('a'));
        assertEquals(true, testLexer.isDigitLiteralChar('f'));
        assertEquals(true, testLexer.isDigitLiteralChar('d'));
        assertEquals(false, testLexer.isDigitLiteralChar('*'));
        assertEquals(false, testLexer.isDigitLiteralChar('!'));
        assertEquals(false, testLexer.isDigitLiteralChar('j'));
        assertEquals(false, testLexer.isDigitLiteralChar('q'));
        assertEquals(false, testLexer.isDigitLiteralChar('1'));
        assertEquals(false, testLexer.isDigitLiteralChar('5'));

    }

    @Test
    public void isAComment() throws Exception {

    }

}