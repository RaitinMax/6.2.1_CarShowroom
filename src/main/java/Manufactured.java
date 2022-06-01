import java.util.ArrayList;
import java.util.List;

class Manufactured {
    private  CreateCar createCar;
    private int noOfCars = 3;
    public VisitorSchool(CreateCar createCar) {
        this.createCar = createCar;
    }
    private List<Manufactured> cars = new ArrayList<>();

    public int getNoOfCars() {
        return cars.size();
    }

    public void setNoOfCars(int noOfCars) {
        this.noOfCars = noOfCars;
    }

    public synchronized void addCar(Manufactured manufactured){
        cars.add(manufactured);
        notify();
    }

}