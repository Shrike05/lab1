import java.awt.*;
import java.util.Stack;

public class Carrier extends Truck {
    private Ramp ramp;
    private int nMaxCars;
    private Stack<Car> carryingCars;

    public Carrier(){
        carryingCars = new Stack<Car>();
        ramp = new Ramp(0, 0, -45);

        nMaxCars = 4;
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
        setModelName("Saab95");
        stopEngine();
    }

    public Ramp getRamp(){
        return ramp;
    }

    public int getMaxNCars(){
        return nMaxCars;
    }

    //Raise it as much as possible
    public void raiseRamp(){
        if(getCurrentSpeed() > 0){
            throw new IllegalStateException("The ramp cannot be heightened whilst the vehicle is in motion");
        }
        
        ramp.setRampAngle(ramp.getMaxRampAngle());
    }

    //Lower it as much as possible
    public void lowerRamp(){
        if(getCurrentSpeed() > 0){
            throw new IllegalStateException("The ramp cannot be lowered whilst the vehicle is in motion");
        }

        ramp.setRampAngle(ramp.getMinRampAngle());
    }

    public void loadCar(Car car){
        if(getCurrentSpeed() > 0 || ramp.getRampAngle() >= ramp.getMaxRampAngle()){
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
        if(getCurrentSpeed() > 0 || ramp.getRampAngle() >= ramp.getMaxRampAngle()){
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
