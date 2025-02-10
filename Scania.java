import java.awt.*;

public class Scania extends Truck {

    public Scania(){
        rampAngleDeg = 0;
        rampMaxAngleDeg = 70;
        rampMinAngleDeg = 0;
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
        setModelName("Saab95");
        stopEngine();
    }

    public void raiseRamp(double angle){
        if(getCurrentSpeed() > 0){
            throw new IllegalStateException("The ramp cannot be heightened whilst the vehicle is in motion");
        }

        rampAngleDeg = Math.min(rampAngleDeg + angle, rampMaxAngleDeg);
    }

    public void lowerRamp(double angle){
        if(getCurrentSpeed() > 0){
            throw new IllegalStateException("The ramp cannot be lowered whilst the vehicle is in motion");
        }

        rampAngleDeg = Math.max(rampAngleDeg - angle, rampMinAngleDeg);
    }
    
    public void startEngine(){
        if(rampAngleDeg > 0){
            throw new IllegalStateException("Cannot start the car whilst the ramp is heightened");
        }

        super.startEngine();
    }
}
