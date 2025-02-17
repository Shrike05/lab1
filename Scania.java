import java.awt.*;

public class Scania extends Truck {

    private Ramp ramp;

    public Scania(){
        ramp = new Ramp(0, 70, 0);

        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
        setModelName("Saab95");
        stopEngine();
    }

    public Ramp getRamp(){
        return ramp;
    }

    public void raiseRamp(double angle){
        if(getCurrentSpeed() > 0){
            throw new IllegalStateException("The ramp cannot be heightened whilst the vehicle is in motion");
        }

        ramp.setRampAngle(angle);
    }

    public void lowerRamp(double angle){
        if(getCurrentSpeed() > 0){
            throw new IllegalStateException("The ramp cannot be lowered whilst the vehicle is in motion");
        }

        ramp.setRampAngle(angle);
    }
    
    public void startEngine(){
        if(ramp.getRampAngle() > 0){
            throw new IllegalStateException("Cannot start the car whilst the ramp is heightened");
        }

        super.startEngine();
    }
}
