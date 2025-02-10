import java.awt.*;
import java.util.Stack;

public class Carrier extends Truck {
    private int nMaxCars;
    private Stack<Car> carryingCars;

    public Carrier(){
        carryingCars = new Stack<Car>();
        rampAngleDeg = 0;
        rampMaxAngleDeg = 0;
        rampMinAngleDeg = -45;

        nMaxCars = 4;
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
        setModelName("Saab95");
        stopEngine();
    }

    public int getMaxNCars(){
        return nMaxCars;
    }

    //Raise it as much as possible
    @Override
    public void raiseRamp(double angle){
        super.raiseRamp(Double.MAX_VALUE);
    }

    //Lower it as much as possible
    @Override
    public void lowerRamp(double angle){
        super.lowerRamp(Double.MAX_VALUE);
    }

    public void loadCar(Car car){
        if(getCurrentSpeed() > 0 || rampAngleDeg >= rampMaxAngleDeg){
            throw new IllegalStateException("Cannot load a vehicle right now");
        }

        if(carryingCars.size() >= nMaxCars){
            throw new IllegalStateException("This carrier cannot hold more cars");
        }

        if(Math.pow(car.getX() - getX(), 2) + Math.pow(car.getY() - getY(), 2) > 3){
            throw new IllegalArgumentException("The car is too far away");
        }

        carryingCars.add(car);
    }

    public Car unloadCar(){
        if(getCurrentSpeed() > 0 || rampAngleDeg >= rampMaxAngleDeg){
            throw new IllegalStateException("Cannot unload a vehicle right now");
        }

        return carryingCars.pop();
    }

    @Override
    public void move(){
        super.move();

        for (Car car : carryingCars) {
            car.setX(getX());
            car.setY(getY());
        }
    }
}
