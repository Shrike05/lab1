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
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
        modelName = "Saab95";
        stopEngine();
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
        if(currentSpeed > 0 || rampAngleDeg >= rampMaxAngleDeg){
            throw new IllegalStateException("Cannot load a vehicle right now");
        }

        if(car.getClass() == Truck.class){
            throw new IllegalArgumentException("The Carrier cannot load this vehicle");
        }

        if(carryingCars.size() >= nMaxCars){
            throw new IllegalStateException("This carrier cannot hold more cars");
        }

        if(Math.pow(car.x - x, 2) + Math.pow(car.y - y, 2) > 3){
            throw new IllegalArgumentException("The car is too far away");
        }

        carryingCars.add(car);
    }

    public Car unloadCar(){
        if(currentSpeed > 0 || rampAngleDeg >= rampMaxAngleDeg){
            throw new IllegalStateException("Cannot unload a vehicle right now");
        }

        return carryingCars.pop();
    }

    @Override
    public void move(){
        super.move();

        for (Car car : carryingCars) {
            car.x = x;
            car.y = y;
        }
    }
}
