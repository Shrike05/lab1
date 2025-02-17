public class Ramp {
    double rampAngleDeg;
    double rampMaxAngleDeg;
    double rampMinAngleDeg;

    public Ramp(double rampAngleDeg, double rampMaxAngleDeg, double rampMinAngleDeg){
        this.rampAngleDeg = Math.max(Math.min(rampAngleDeg, rampMaxAngleDeg), rampMinAngleDeg);
        this.rampMaxAngleDeg = rampMaxAngleDeg;
        this.rampMinAngleDeg = rampMinAngleDeg;
    }

    public double getRampAngle(){
        return rampAngleDeg;
    }

    public double getMaxRampAngle(){
        return rampMaxAngleDeg;
    }

    public double getMinRampAngle(){
        return rampMinAngleDeg;
    }

    public void setRampAngle(double rampAngleDeg){
        this.rampAngleDeg = Math.max(Math.min(rampAngleDeg, rampMaxAngleDeg), rampMinAngleDeg);
    }
}
