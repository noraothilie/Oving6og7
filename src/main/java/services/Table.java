package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nora on 24.09.2017.
 */
public class Table {
    ArrayList<Order> orders = new ArrayList<>();
    //private int tableNr;

    public Table(){

    }

    public boolean checkOrder(Order order) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss a");
            Date fromTime2 = formatter.parse(order.getFromTime() + ":00 AM");
            Date toTime2 = formatter.parse(order.getToTime() + ":00 AM");
            Calendar cal2 = Calendar.getInstance();
            for (int i = 0; i < orders.size(); i++) {
                Date fromTime1 = formatter.parse(orders.get(i).getFromTime() + ":00 AM");
                Date toTime1 = formatter.parse(orders.get(i).getToTime() + ":00 AM");
                Calendar cal1 = Calendar.getInstance();
                if (((fromTime2.compareTo(fromTime1) > 0) && ((fromTime2.compareTo(toTime1) < 0))) ||
                        ((toTime2.compareTo(fromTime1) > 0) && (toTime2.compareTo(toTime1) < 0)) ||
                        ((fromTime1.compareTo(fromTime2) > 0) && ((toTime1.compareTo(toTime2) < 0)))) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("No available tables");
        return false;
    }
}
