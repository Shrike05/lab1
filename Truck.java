public abstract class Truck extends Vehicle {
    protected double rampAngleDeg;
    protected double rampMaxAngleDeg;
    protected double rampMinAngleDeg;

    public double getRampAngle(){
        return rampAngleDeg;
    }

    @Override
    public double speedFactor(){
        return getEnginePower() * 0.01;
    }
}
