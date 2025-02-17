public abstract class Truck extends Vehicle {
    @Override
    public double speedFactor(){
        return getEnginePower() * 0.01;
    }
}
