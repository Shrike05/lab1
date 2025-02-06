import java.util.ArrayList;

public abstract class CarWorkshop<T extends Car> {
    private int maxCars;
    private ArrayList<T> cars;
    
    public CarWorkshop(int maxCars){
        this.maxCars = maxCars;
        cars = new ArrayList<T>();
    }

    public ArrayList<T> getCars(){
        return cars;
    }

    public void addCar(T car){
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
