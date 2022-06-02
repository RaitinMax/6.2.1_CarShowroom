import java.util.ArrayList;
import java.util.List;

class Manufactured {
private String name;

    public Manufactured(String name) {
        this.name = name;
    }

    public synchronized void sendToShop(ShowRoom showRoom) {
        try {
            System.out.format("Factory has been created %s  1 car!\n", name);
            Thread.sleep(100);
            showRoom.addCar(new CreateCar(this));
            System.out.format("There are %s left\n", showRoom.getNoOfCars());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}