

import java.util.Calendar;

/**
 * Description: TODO
 *
 * @author shuaimeng
 * @since 29.03.2019
 */
public class Data {


    public static void main(String[] args) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -4);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.MONTH) + 1);


        System.out.println(String.format("%s%s%s%s%s", "1",2,"5",4,5));


    }
}
