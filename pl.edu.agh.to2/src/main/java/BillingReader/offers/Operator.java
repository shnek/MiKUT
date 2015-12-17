package BillingReader.offers;

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

    Operator(String name) {
        this.name = name;
    }

    public static Operator findByName(String name) {
        Operator[] list = Operator.values();
        for (Operator op : list) {
            //System.out.println(op.getName()+" vs "+name);
            if (op.name.equals(name)) {
                //System.out.println("MATCH XD");
                return op;
            }
        }
        return null;
    }

    public String getName () {
        return this.name;
    }

}
