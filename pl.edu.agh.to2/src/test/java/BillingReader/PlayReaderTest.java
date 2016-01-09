package BillingReader;

import BillingReader.billings.PlayReader;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class PlayReaderTest {

    private PlayReader playReader;
    private String text;
    private String line;
    private String[] elements;

    @Test
    public void testToSeconds() {
        playReader = mock(PlayReader.class);
        text = "02:33 min.,,";
        assertNotEquals(playReader.toSeconds(text), 153);
    }

    /*@Test
    public void testParseLine() {
        playReader = mock(PlayReader.class);
        line = "27,Wychodz�ce,Rozmowy g�osowe,2015.11.28,18:18:49,48725035819,G�OSOWE,02:33 min.,,";
        elements = line.split(",");
        playReader.parseLine(elements);
        assertNull(playReader.getDialList());
    }*/

}
