import java.util.ArrayList;

public class Logical {
    private ArrayList<String> logical;

    Logical() {
        initializeMap();
    }

    private void initializeMap() {
        logical = new ArrayList<>();
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
