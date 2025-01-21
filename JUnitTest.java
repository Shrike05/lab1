import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JUnitTest {

    @Test
    public void TestMethods() {
        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();

        assertEquals(volvo.getNrDoors(), 4);
        assertEquals(saab.getNrDoors(), 2);
    }
}