import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

public class TruckTests {

    private Scania scania;
    private Carrier carrier;

    @Before
    public void initCars(){
        scania = new Scania();
        carrier = new Carrier();
    }

    @Test
    public void testRampHeighten(){
        scania.startEngine();

        assertThrows(IllegalStateException.class, () -> scania.raiseRamp(1));
    }

    @Test
    public void testRampLower(){
        scania.startEngine();

        assertThrows(IllegalStateException.class, () -> scania.lowerRamp(1));
    }

    @Test
    public void testStartEngineWithHeightenedRamp(){
        scania.raiseRamp(1);

        assertThrows(IllegalStateException.class, () -> scania.startEngine());
    }

    @Test
    public void testGetRampAngle(){
        assertEquals(true, 0 == scania.getRampAngle());
    }

    @Test
    public void testNegativeRampAngle(){
        scania.lowerRamp(1);
        
        assertEquals(true, scania.getRampAngle() >= 0);
    }

    // Biltransportens ramp har endast två lägen, uppe eller nere.
    @Test
    public void testCarrierRampStates(){
        carrier.lowerRamp(0);
        assertEquals(true, carrier.getRampAngle() - carrier.rampMinAngleDeg < 0.01);

        carrier.raiseRamp(0);
        assertEquals(true, carrier.getRampAngle() - carrier.rampMaxAngleDeg < 0.01);
    }

    // Rampen kan endast vara nere om biltransporten står stilla.
    @Test
    public void testRampCannotLowerInMotion(){
        carrier.startEngine();

        assertThrows(IllegalStateException.class, () -> carrier.lowerRamp(0));
    }

    // Bilar kan endast lastas om rampen är nere, och de befinner sig rimligt nära biltransporten (gör ett eget antagande, de exakta detaljerna är inte viktiga).
    @Test
    public void TestCarsLoadingWhenRampUp(){
        carrier.raiseRamp(0);

        assertThrows(IllegalStateException.class, () -> carrier.loadCar(new Saab95()));
    }

    // Bilar kan endast lossas om rampen är nere. De bör då hamna rimligt nära biltransporten.
    @Test
    public void TestCarsUnLoadingWhenRampUp(){
        carrier.raiseRamp(0);
        carrier.loadCar(new Saab95());

        assertThrows(IllegalStateException.class, () -> carrier.unloadCar());
    }

    // Bilar kan endast lossas i omvänd ordning från hur de lastades, dvs den sista bilen som lastades måste vara först att lossas (first-in-last-out).
    @Test
    public void TestUnloadingOrder(){
        carrier.lowerRamp(0);

        Car car1 = new Saab95();
        Car car2 = new Volvo240();

        carrier.loadCar(car1);
        carrier.loadCar(car2);

        assertEquals(car2, carrier.unloadCar());
        assertEquals(car1, carrier.unloadCar());
    }

    // Biltransporten ska inte kunna lasta på en annan biltransport.
    @Test
    public void TestCarrierLoadingCarrier(){
        carrier.lowerRamp(0);
        Carrier otherCarrier = new Carrier();

        assertThrows(IllegalArgumentException.class, () -> carrier.loadCar(otherCarrier));
    }

    // Under det att en bil är lastad på biltransporten ska dess position i världen alltid vara densamma som biltransportens position.
    @Test
    public void TestLoadedCarPositions(){
        carrier.lowerRamp(0);

        Car car = new Saab95();

        carrier.loadCar(car);

        carrier.move();

        assertEquals(true, Math.abs(carrier.x - car.x) < 0.01);
        assertEquals(true, Math.abs(carrier.y - car.y) < 0.01);
    }
}