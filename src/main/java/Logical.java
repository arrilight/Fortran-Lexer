import java.util.ArrayList;

/** This class is used to represent logical operators that are used in
 * FORTRAN 77, 90/95
 *
 */

public class Logical {
    private ArrayList<String> logical;

    Logical() {
        initializeMap();
    }

    private void initializeMap() {
        logical = new ArrayList<String>();
        for (LogicalEnum op: LogicalEnum.values()) {
            logical.add(op.name().toLowerCase());
        }
    }

    public boolean isLogical(String value) {
        return logical.contains(value);
    }

    public boolean isLogicalLiteral(String value) {
        return (value.equals("true") || value.equals("false"));
    }
}
