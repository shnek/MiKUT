package BillingReader;

import BillingReader.billings.PlusReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

public class PlusReaderTest {

    private PlusReader reader;
    private String line;

    @Test
    public void getNumberTest() {
        reader = new PlusReader();
        line = "734423466T-Mobile10,000,00";
        assertEquals("734423466", reader.getNumber(line));
    }

/*
    @Test
    public void addSmsTest() {
        reader = mock(PlusReader.class);
        line = "07.10.201515:04:33734423466T-Mobile10,000,00";
        reader.parseLine(line);
        assertNotEquals(reader.getSmsList().get(0).getNumber(), "734423466");
    }

    @Test
    public void addMmsTest() {
        reader = mock(PlusReader.class);
        line = "19.09.201517:58:23608756122Play300 KB0,000,00";
        reader.parseLine(line);
        assertNotEquals(reader.getMmsList().get(0).getNumber(),"608756122");
    }

    @Test
    public void addDialTest() {
        reader = mock(PlusReader.class);
        line = "26.09.201513:05:12606883847T-Mobile2:050,000,00";
        reader.parseLine(line);
        assertNotEquals(reader.getDialList().get(0).getLength(),125);
    }

    @Test
    public void addTransferTest() {
        reader = mock(PlusReader.class);
        line = "19.09.201505:52:40internet46 KB0,000,00";
        reader.parseLine(line);
        assertNotEquals(reader.getTransferList().get(0).getDataSize(),46);
    }
*/
}