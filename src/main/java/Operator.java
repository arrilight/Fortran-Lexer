import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to represent operators that are used in FORTRAN 77, 90/95
 */
public class Operator {
    private ArrayList<String> operators;

    Operator() {
        try {
            initializeOperators();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeOperators() throws IOException {
        this.operators = new ArrayList<String>();
        String operator;
        BufferedReader reader = new BufferedReader(new FileReader("operators.txt"));
        while ((operator = reader.readLine()) != null) {
            operators.add(operator);
        }

    }

    public boolean isOperator(char c) {
        return operators.contains(Character.toString(c));
    }
}
