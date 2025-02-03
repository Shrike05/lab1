import java.util.ArrayList;

public abstract class CarWorkshop<T extends Car> {
    private int maxCars;
    private ArrayList<T> cars;
    private ArrayList<Object> blacklistedCars;

    public CarWorkshop(int maxCars, ArrayList<Object> allowedCars){
        this.maxCars = maxCars;
        this.blacklistedCars = allowedCars;
        cars = new ArrayList<T>();
    }

    public ArrayList<T> getCars(){
        return cars;
    }

    public void addCar(T car){
        for (Object carType : blacklistedCars) {
            if(car.getClass() == carType.getClass()){
                throw new IllegalArgumentException("The workshop does not accept this type of car");
            }
        }

        if(cars.size() >= maxCars){
            throw new IllegalStateException("The workshop is already full of cars");
        }

        cars.add(car);
    }

    public T removeCar(int index){
        T car = cars.get(index);
        cars.remove(index);
        return car;
    }
}
