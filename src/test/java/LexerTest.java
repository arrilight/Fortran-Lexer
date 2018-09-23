import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.SplittableRandom;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 9/23/2018.
 */
public class LexerTest {
    Lexer testLexer;
    public String commentsTest = new String(" !kjbsdc");
    public LexerTest() throws IOException{
        testLexer = new Lexer();
    }
    @Test
    public void startLexicalAnalysis() throws Exception {
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
        assertEquals(testLexer.findCompositeKeyword("ELSE IF","else",4).getType(),TokenType.KEYWORD);
        assertEquals(testLexer.findCompositeKeyword("ELSE IF","else",4).getValue(), "ELSE IF");
    }

    @Test
    public void findOperator() throws Exception {
        testLexer.pointer = 0;
        assertEquals(testLexer.findOperator("+").getType(),TokenType.OPERATOR);
        testLexer.pointer = 0;
        assertEquals(testLexer.findOperator("+").getValue(),"PLUS");
        testLexer.pointer = 0;
        assertEquals(testLexer.findOperator("(").getType(),TokenType.SEPARATOR);
        testLexer.pointer = 0;
        assertEquals(testLexer.findOperator("(").getValue(),"LEFT_PAREN");
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
        String testString = "jhalsdkm7";
        char div = '7';
        testLexer.pointer = 0;
        assertEquals(testLexer.findStringLiteral(testString,div).getType(), TokenType.LITERAL);
        testLexer.pointer = 0;
        assertEquals(testLexer.findStringLiteral(testString,div).getValue(), "jhalsdkm7");
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
        assertEquals(testLexer.isAComment("  !kjasdc kj"),true);
        assertEquals(testLexer.isAComment("kjasdc kj"),false);
        assertEquals(testLexer.isAComment(" !kj"),true);
        assertEquals(testLexer.isAComment(""),false);
        assertEquals(testLexer.isAComment("  !134"),true);
        assertEquals(testLexer.isAComment("sdva"),false);
    }

}