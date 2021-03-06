import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ShowRoom implements Runnable {
    Manufactured manufactured;
    private  static final List<CreateCar> cars = new ArrayList<>();

    public ShowRoom(Manufactured manufactured) {
        this.manufactured = manufactured;
    }

    public void run() {
       enterToShowRoom(manufactured);
        if (CarsGreaterThanZero()) {
            getInTheCarAndDrive();
        } else if (CarsEqualToZero()) {
            waitForCar();
            getInTheCarAndDrive();
        }
    }

    public boolean CarsGreaterThanZero() {
        synchronized (manufactured) {
            if (getNoOfCars() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean CarsEqualToZero() {
        synchronized (manufactured) {
            if (getNoOfCars() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    void waitForCar() {
        synchronized (manufactured) {

            try {
                System.out.println("There are NO car left");
                manufactured.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void getInTheCarAndDrive() {
        synchronized (manufactured) {
            removeNoOfCars();
            System.out.println(Thread.currentThread().getName()
                    + " has bought, " + getNoOfCars()
                    + " available");
        }
        Random numGen = new Random();
        int r = numGen.nextInt(1000);
        try {
            Thread.sleep(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void addCar(CreateCar createCar) {
        cars.add(createCar);
        notify();
    }

    public int getNoOfCars() {
        return cars.size();
    }

    public void removeNoOfCars() {
        int index = cars.size() - 1;
        cars.remove(index);
    }
    public synchronized void enterToShowRoom(Manufactured manufactured){
        System.out.println(Thread.currentThread().getName()+" entered to showroom");
    }
}