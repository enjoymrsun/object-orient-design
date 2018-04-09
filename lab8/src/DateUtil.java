import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Use to get the XX before date.
 */
public class DateUtil {
  /**
   * Get the before date.
   *
   * @param year  year
   * @param month month
   * @param day   day
   * @param delta how many days you want to go backward
   * @return new date as string
   */
  public static String getBeforeDate(int year, int month, int day, int delta) {
    Calendar ca = Calendar.getInstance();
    ca.set(year, month - 1, day);
    ca.add(Calendar.DATE, -delta);
    Date resultDate = ca.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    return sdf.format(resultDate);
  }
}


