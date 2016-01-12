package BillingReader;

import BillingReader.billings.PlusReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlusReaderTest {

    private PlusReader reader;
    private String line;

    @Test
    public void getNumberTest() {
        reader = new PlusReader();
        line = "734423466T-Mobile10,000,00";
        assertEquals("734423466", reader.getNumber(line));
    }

    @Test
    public void addSmsTest() {
        reader = new PlusReader();
        line = "07.10.201515:04:33734423466T-Mobile10,000,00";
        reader.parseLine(line);
        assertEquals("734423466", reader.getSmsList().get(0).getNumber());
    }

    @Test
    public void addMmsTest() {
        reader = new PlusReader();
        line = "19.09.201517:58:23608756122Play300 KB0,000,00";
        reader.parseLine(line);
        assertEquals("608756122", reader.getMmsList().get(0).getNumber());
    }

    @Test
    public void addDialTest() {
        reader = new PlusReader();
        line = "26.09.201513:05:12606883847T-Mobile2:050,000,00";
        reader.parseLine(line);
        assertEquals(125, reader.getDialList().get(0).getLength());
    }

    @Test
    public void addTransferTest() {
        reader = new PlusReader();
        line = "19.09.201505:52:40internet46 KB0,000,00";
        reader.parseLine(line);
        assertEquals(46, reader.getTransferList().get(0).getDataSize());
    }

}