public abstract class Truck extends Car {
    protected double rampAngleDeg;
    protected double rampMaxAngleDeg;
    protected double rampMinAngleDeg;

    public double getRampAngle(){
        return rampAngleDeg;
    }

    public void raiseRamp(double angle){
        if(currentSpeed > 0){
            throw new IllegalStateException("The ramp cannot be heightened whilst the vehicle is in motion");
        }

        rampAngleDeg = Math.min(rampAngleDeg + angle, rampMaxAngleDeg);
    }

    public void lowerRamp(double angle){
        if(currentSpeed > 0){
            throw new IllegalStateException("The ramp cannot be lowered whilst the vehicle is in motion");
        }

        rampAngleDeg = Math.max(rampAngleDeg - angle, rampMinAngleDeg);
    }
}
