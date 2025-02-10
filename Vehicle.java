import java.awt.*;
     
enum Direction{
    N,
    S,
    W,
    E
}

public abstract class Vehicle implements Movable{
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double x = 0; // X position
    private double y = 0; // Y position
    private Direction dir = Direction.N;
    private int directionIndex = 0;
    private final Direction[] DIRECTIONS = {Direction.N, Direction.W, Direction.S, Direction.E};
    
    public void setNrDoors(int nrDoors){
        this.nrDoors = nrDoors;
    }

    public void setModelName(String modelName){
        this.modelName = modelName;
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
    
    public abstract double speedFactor();

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
        double angle = 0;

        switch (dir) {
            case Direction.N:
                angle = Math.PI/2;
                break;
            case Direction.S:
                angle = 3*Math.PI/2;
                break;
            case Direction.W:
                angle = Math.PI;
                break;
            case Direction.E:
                angle = 0;
                break;
            default:
                angle = 0;
                break;
        }

        x += Math.cos(angle) * currentSpeed;
        y += Math.sin(angle) * currentSpeed;
    }

    public void turnLeft(){
        directionIndex += 1;
        directionIndex %= DIRECTIONS.length;
        dir = DIRECTIONS[directionIndex];
    }

    public void turnRight(){
        directionIndex = directionIndex == 0 ? DIRECTIONS.length-1 : directionIndex - 1;
        dir = DIRECTIONS[directionIndex];
    }
}
