
import java.util.Map;
import java.util.Random;

/**
 * Description: TODO
 *
 * @author shuaimeng
 * @since 08.04.2019
 */
public class TestOutofMermoy {
    public static void main(String[] args) {
        while (true){
            Map map = System.getProperties();
            Random r = new Random();
            while (true) {
                map.put(r.nextInt(), "value");
            }
        }
    }
}
