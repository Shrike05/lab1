import java.awt.*;

public class Car implements Movable{
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    public String modelName; // The car model name
    protected double x = 0;
    protected double y = 0;
    protected double angle = 0;
    
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
	    color = clr;
    }

    public void startEngine(){
	    currentSpeed = 0.1;
    }

    public void stopEngine(){
	    currentSpeed = 0;
    }
    
    public double speedFactor(){
        return 0;
    }

    public void incrementSpeed(double amount){
    }

    public void decrementSpeed(double amount){
    }
    
    public void gas(double amount) {
        //Constrain amount between 0 and 1
        amount = Math.max(Math.min(amount, 1), 0);

        double oldSpeed = currentSpeed;
        incrementSpeed(amount);

        //If the currentSpeed decreased
        //Then reset it to the old speed because it is not allowed to decrease
        if(currentSpeed < oldSpeed){
            currentSpeed = oldSpeed;
        }
        
        //Constrain currentSpeed between 0 and enginePower
        currentSpeed = Math.max(Math.min(currentSpeed, enginePower), 0);
    }

    public void brake(double amount){
        //Constrain amount between 0 and 1
        amount = Math.max(Math.min(amount, 1), 0);

        double oldSpeed = currentSpeed;
        decrementSpeed(amount);

        //If the currentSpeed increased
        //Then reset it to the old speed because it is not allowed to increase
        if(currentSpeed > oldSpeed){
            currentSpeed = oldSpeed;
        }
        
        //Constrain currentSpeed between 0 and enginePower
        currentSpeed = Math.max(Math.min(currentSpeed, enginePower), 0);
    }

    public void move(){
        x += Math.cos(angle) * currentSpeed;
        y += Math.sin(angle) * currentSpeed;
    }

    public void turnLeft(){
        angle += 90;
    }

    public void turnRight(){
        angle -= 90;
    }
}
