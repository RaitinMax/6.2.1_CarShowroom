import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Thread> mThreadList;

    public static void main(String[] args) throws IOException {

        Manufactured manufactured = new Manufactured("Toyota");
        mThreadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ShowRoom showRoom = new ShowRoom(manufactured);
            mThreadList.add(new Thread(showRoom, "Client #" + (i + 1)));
            mThreadList.get(i).start();
            new Thread(() -> manufactured.sendToShop(showRoom), manufactured.toString()).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < mThreadList.size(); i++) {


        }
    }
}
