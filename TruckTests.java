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
        assertEquals(true, 0 == scania.getRamp().getRampAngle());
    }

    @Test
    public void testNegativeRampAngle(){
        scania.lowerRamp(1);
        
        assertEquals(true, scania.getRamp().getRampAngle() >= 0);
    }

    // Biltransportens ramp har endast två lägen, uppe eller nere.
    @Test
    public void testCarrierRampStates(){
        carrier.lowerRamp();
        assertEquals(true, carrier.getRamp().getRampAngle() - carrier.getRamp().getMinRampAngle() < 0.01);

        carrier.raiseRamp();
        assertEquals(true, carrier.getRamp().getRampAngle() - carrier.getRamp().getMaxRampAngle() < 0.01);
    }

    // Rampen kan endast vara nere om biltransporten står stilla.
    @Test
    public void testRampCannotLowerInMotion(){
        carrier.startEngine();
        
        assertThrows(IllegalStateException.class, () -> carrier.lowerRamp());
    }

    // Bilar kan endast lastas om rampen är nere, och de befinner sig rimligt nära biltransporten (gör ett eget antagande, de exakta detaljerna är inte viktiga).
    @Test
    public void TestCarsLoadingWhenRampUp(){
        carrier.raiseRamp();

        assertThrows(IllegalStateException.class, () -> carrier.loadCar(new Saab95()));
    }

    @Test
    public void TestCarsLoadingWhenMoving(){
        carrier.startEngine();

        assertThrows(IllegalStateException.class, () -> carrier.loadCar(new Saab95()));
    }

    @Test
    public void TestCarsLoadingWhenFull(){
        carrier.lowerRamp();

        for(int i = 0; i < carrier.getMaxNCars(); i++){
            carrier.loadCar(new Saab95());
        }

        assertThrows(IllegalStateException.class, () -> carrier.loadCar(new Saab95()));
    }

    @Test
    public void TestCarsLoadingWhenCarIsFarAway(){
        carrier.lowerRamp();

        Car car = new Saab95();
        car.setX(100);
        car.setY(100);

        assertThrows(IllegalArgumentException.class, () -> carrier.loadCar(car));
    }


    // Bilar kan endast lossas om rampen är nere. De bör då hamna rimligt nära biltransporten.
    @Test
    public void TestCarsUnLoadingWhenRampUp(){
        carrier.lowerRamp();
        carrier.loadCar(new Saab95());

        carrier.raiseRamp();
        assertThrows(IllegalStateException.class, () -> carrier.unloadCar());
    }

    // Bilar kan endast lossas i omvänd ordning från hur de lastades, dvs den sista bilen som lastades måste vara först att lossas (first-in-last-out).
    @Test
    public void TestUnloadingOrder(){
        carrier.lowerRamp();

        Car car1 = new Saab95();
        Car car2 = new Volvo240();

        carrier.loadCar(car1);
        carrier.loadCar(car2);

        assertEquals(car2, carrier.unloadCar());
        assertEquals(car1, carrier.unloadCar());
    }

    // Under det att en bil är lastad på biltransporten ska dess position i världen alltid vara densamma som biltransportens position.
    @Test
    public void TestLoadedCarPositions(){
        carrier.lowerRamp();

        Car car = new Saab95();

        carrier.loadCar(car);

        carrier.move();

        assertEquals(true, Math.abs(carrier.getX() - car.getX()) < 0.01);
        assertEquals(true, Math.abs(carrier.getY() - car.getY()) < 0.01);
    }
}