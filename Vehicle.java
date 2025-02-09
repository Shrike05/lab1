import java.awt.*;

public class Vehicle implements Movable{
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    public String modelName; // The car model name
    private double x = 0;
    private double y = 0;
    private double angle = 0;
    
    public void setNrDoors(int nrDoors){
        this.nrDoors = nrDoors;
    }

    public void setEnginePower(double enginePower){
        this.enginePower = enginePower;
    }

    public void setnrCurrentSpeed(double currentSpeed){
        this.currentSpeed = currentSpeed;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }


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

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
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
    
    protected double speedFactor(){
        return 0;
    }

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    private void decrementSpeed(double amount){
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
        angle += Math.PI/4;
    }

    public void turnRight(){
        angle -= Math.PI/4;
    }
}
