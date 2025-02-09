import java.awt.*;

public class Scania extends Truck {

    public Scania(){
        rampAngleDeg = 0;
        rampMaxAngleDeg = 70;
        rampMinAngleDeg = 0;
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
        modelName = "Saab95";
        stopEngine();
    }
    
    public void startEngine(){
        if(rampAngleDeg > 0){
            throw new IllegalStateException("Cannot start the car whilst the ramp is heightened");
        }

        super.startEngine();
    }
}
