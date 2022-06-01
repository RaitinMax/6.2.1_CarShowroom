import java.util.Random;

class VisitorSchool implements Runnable {
    Manufactured manufactured;

    public VisitorSchool(Manufactured manufactured) {
        this.manufactured = manufactured;
    }

    public void run() {
        if (CarsGreaterThanZero()) {
            getInTheCarAndDrive();
        } else if (CarsEqualToZero()) {
            waitForCar();
            getInTheCarAndDrive();
        }
    }

    public boolean CarsGreaterThanZero() {
        synchronized (manufactured) {
            if (manufactured.getNoOfCars() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean CarsEqualToZero() {
        synchronized (manufactured) {
            if (manufactured.getNoOfCars() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    void waitForCar() {
        synchronized (manufactured) {
            try {
                manufactured.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void getInTheCarAndDrive() {
        synchronized (manufactured) {
            manufactured.setNoOfCars(manufactured.getNoOfCars() - 1);
            System.out.println(Thread.currentThread().getName()
                    + " is driving, " + manufactured.getNoOfCars()
                    + " available");
        }
        Random numGen = new Random();
        int r = numGen.nextInt(1000);
        try {
            Thread.sleep(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (manufactured) {
            manufactured.setNoOfCars(manufactured.getNoOfCars() + 1);
            System.out.println(Thread.currentThread().getName()
                    + " has created 1 car. There are " + manufactured.getNoOfCars()
                    + " available");
            manufactured.notify();
        }
    }
}