package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nora on 24.09.2017.
 */
public class Table {
    ArrayList<Order> orders = new ArrayList<>();
    private int tableNr;

    public Table(){

    }

    public Table(int tableNr, ArrayList<Order> orders){
        this.tableNr = tableNr;
        this.orders = orders;
    }

    public ArrayList<Order> getOrders(){
        return orders;
    }

    public boolean checkOrder(Order order) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss a");
            Date fromTime2 = formatter.parse(order.getFromTime() + ":00 AM");
            Date toTime2 = formatter.parse(order.getToTime() + ":00 AM");

            if(orders.size()==0){
                return true;
            } else {
                for (int i = 0; i < orders.size(); i++) {
                    Date fromTime1 = formatter.parse(orders.get(i).getFromTime() + ":00 AM");
                    Date toTime1 = formatter.parse(orders.get(i).getToTime() + ":00 AM");

                    if(dateBetween(fromTime1, toTime1, fromTime2)){
                        return false;
                    } else if (dateBetween(fromTime1, toTime1, toTime2)){
                        return false;
                    } else if ((fromTime2.compareTo(fromTime1) < 0) && ((toTime2.compareTo(toTime1)) > 0)) {
                        return false;
                    } else if((fromTime2.compareTo(fromTime1) == 0) && ((toTime2.compareTo(toTime1)) == 0)) {
                        return false;
                    } else if ((fromTime2.compareTo(fromTime1) <0) && ((toTime2.compareTo(toTime1))== 0)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTableNr() {
        return tableNr;
    }

    public void setTableNr(int tableNr) {
        this.tableNr = tableNr;
    }

    private boolean dateBetween(Date a, Date b, Date c){
        return a.compareTo(c) * c.compareTo(b) >= 0;
    }
}
