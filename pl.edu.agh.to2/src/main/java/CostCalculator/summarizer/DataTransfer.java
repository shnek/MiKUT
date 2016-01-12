package CostCalculator.summarizer;


public enum DataTransfer {

    KB {
        public int toKb(int value) { return value; }
        public int toMb(int value) { return (int) Math.ceil(value/MbInKb); }
    },
    MB {
        public int toKb(int value) { return value*MbInKb; }
        public int toMb(int value) { return value; }
    },
    GB {
        public int toKb(int value) { return value*MbInKb*MbInKb; }
        public int toMb(int value) { return value*MbInKb; }
    };

    static final int MbInKb = 1024;

    public int toMb(int quantity)  {
        throw new AbstractMethodError();
    }
    public int toKb(int quantity)  {
        throw new AbstractMethodError();
    }

}
