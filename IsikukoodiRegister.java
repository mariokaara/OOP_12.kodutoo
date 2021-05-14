import java.util.ArrayList;
import java.util.List;

public class IsikukoodiRegister {

    private List<String> list = new ArrayList<>();

    public void registreeri(String isikukood) {
        synchronized (this) {
            if (!list.contains(isikukood)) {
                list.add(isikukood);
            }
        }
    }

    public synchronized int järjekorranumber(String isikukood) {
        synchronized (this) {
            if (list.contains(isikukood)) {
                return list.indexOf(isikukood) + 1;
            } else {
                return -1;
            }

        }

    }
}