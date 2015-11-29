import java.util.HashMap;
import java.util.Map;

public enum Operator {
    PLAY ("Play"),
    ORANGE ("Orange"),
    PLUS ("Plus"),
    TMOBILE ("T-Mobile"),
    NJU ("nju mobile"),
    LAJT ("lajt mobile"),
    HEYAH ("Heyah"),
    VIRGIN ("Virgin Mobile"),
    VIKINGS ("Mobile Vikings");


    private String name;

    private Operator(String name) {
        this.name = name;
    }

    public static Operator findByName(String name) {
        Operator[] list = Operator.values();
        for (Operator op : list) {
            if (op.name.equals(name)) {
                return op;
            }
        }
        return null;
    }

    public String getName () {
        return this.name;
    }

}
