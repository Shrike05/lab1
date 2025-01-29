import java.awt.*;

public abstract class Car implements Movable{
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
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
    
    public void gas(double amount) {
        if(amount > 1 || amount < 0){
            throw new IllegalArgumentException("Amount must be in the range [0,1]");
        }

        incrementSpeed(amount);
    }

    public void brake(double amount){
        if(amount > 1 || amount < 0){
            throw new IllegalArgumentException("Amount must be in the range [0,1]");
        }

        decrementSpeed(amount);
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
