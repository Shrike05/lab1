import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

public class JUnitTest {

    private Volvo240 volvo;
    private Saab95 saab;

    @Before
    public void initCars(){
        volvo = new Volvo240();
        saab = new Saab95();
    }

    @Test
    public void testDoors() {
        assertEquals(4, volvo.getNrDoors());
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    public void testSpeedOnGas() {
        for(int i = 0; i < 100; i++){
            volvo.gas(1);
            saab.gas(1);
        }
        
        assertEquals(true, volvo.currentSpeed <= volvo.enginePower && volvo.currentSpeed >= 0);
        assertEquals(true, saab.currentSpeed <= saab.enginePower && saab.currentSpeed >= 0);
    }

    @Test
    public void testSpeedOnBrake() {
        for(int i = 0; i < 100; i++){
            volvo.brake(1);
            saab.brake(1);
        }
        
        assertEquals(volvo.currentSpeed <= 1 && volvo.currentSpeed >= 0, true);
        assertEquals(saab.currentSpeed <= 1 && saab.currentSpeed >= 0, true);
    }

    @Test
    public void testBrake() {
        assertThrows(IllegalArgumentException.class, () -> saab.brake(100));
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(100));

        assertThrows(IllegalArgumentException.class, () -> saab.brake(-1));
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(-1));
    }

    @Test
    public void testGas() {
        assertThrows(IllegalArgumentException.class, () -> saab.gas(100));
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(100));

        assertThrows(IllegalArgumentException.class, () -> saab.gas(-1));
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(-1));
    }

    @Test
    public void testEnginePower(){
        assertEquals(125, saab.enginePower, 0.001);
        assertEquals(100, volvo.enginePower, 0.001);
    }

    @Test
    public void testColor(){
        assertEquals(Color.red, saab.color);
        assertEquals(Color.black, volvo.color);
    }

    @Test
    public void testSetColor(){
        saab.setColor(Color.blue);
        volvo.setColor(Color.blue);

        assertEquals(Color.blue, saab.color);
        assertEquals(Color.blue, volvo.color);
    }

    @Test
    public void testStartEngine(){
        saab.startEngine();
        volvo.startEngine();

        assertEquals(0.1, saab.currentSpeed, 0.01);
        assertEquals(0.1, volvo.currentSpeed, 0.01);
    }

    public void testSpeedFactor(){
        assertEquals(saab.enginePower * 0.01 * (saab.turboOn ? 1.3 : 1), saab.speedFactor(), 0.01);
        assertEquals(volvo.enginePower * 0.01 * Volvo240.trimFactor, saab.speedFactor(), 0.01);
    }
}