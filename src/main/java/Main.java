import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Thread> mThreadList;

    public static void main(String[] args) throws IOException {
        Manufactured manufactured = new Manufactured();
        mThreadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            VisitorSchool vs = new VisitorSchool(manufactured);
            mThreadList.add(new Thread(vs, "Car #" + (i + 1)));
            mThreadList.get(i).start();
            try {
                Thread.sleep(500);
            }catch (Exception e){}
        }
    }
}
