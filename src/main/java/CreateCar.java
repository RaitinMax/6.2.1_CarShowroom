public class CreateCar {
    private String name;
    public void sendToShop(Manufactured manufactured) {
        try {
            System.out.format("Производитель %s выпустил 1 автомобиль!\n", name);
            Thread.sleep(1100);
            manufactured.addCar(new Manufactured(this));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
