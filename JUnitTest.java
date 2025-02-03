import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

public class JUnitTest {

    private Volvo240 volvo;
    private Saab95 saab;
    private Scania scania;

    @Before
    public void initCars(){
        volvo = new Volvo240();
        saab = new Saab95();
        scania = new Scania();
    }

    @Test
    public void testDoors() {
        assertEquals(4, volvo.getNrDoors());
        assertEquals(2, saab.getNrDoors());
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
    public void testGasLowers() {
        double oldVolvoSpeed = volvo.currentSpeed;
        volvo.gas(1);
        assertEquals(true, volvo.currentSpeed >= oldVolvoSpeed);

        double oldSaabSpeed = saab.currentSpeed;
        saab.gas(1);
        assertEquals(true, saab.currentSpeed >= oldSaabSpeed);
    }

    @Test
    public void testBrakeHigher() {
        double oldVolvoSpeed = volvo.currentSpeed;
        volvo.brake(1);
        assertEquals(true, volvo.currentSpeed <= oldVolvoSpeed);

        double oldSaabSpeed = saab.currentSpeed;
        saab.brake(1);
        assertEquals(true, saab.currentSpeed <= oldSaabSpeed);
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

    @Test
    public void testSpeedFactor(){
        assertEquals(saab.enginePower * 0.01 * (saab.turboOn ? 1.3 : 1), saab.speedFactor(), 0.01);
        assertEquals(volvo.enginePower * 0.01 * Volvo240.trimFactor, saab.speedFactor(), 0.01);
    }

    @Test
    public void testDeckHeight(){
        scania.startEngine();

        assertThrows(IllegalStateException.class, () -> scania.heightenDeck());
    }

    @Test
    public void testStartEngineWithHeightenedDeck(){
        scania.heightenDeck();

        assertThrows(IllegalStateException.class, () -> scania.startEngine());
    }

    @Test
    public void testGetDeckAngle(){
        assertEquals(true, 0 == scania.getDeckAngle());
    }

    @Test
    public void testNegativeDeckAngle(){
        scania.lowerDeck();
        
        assertEquals(true, 0 >= scania.getDeckAngle());
    }
}