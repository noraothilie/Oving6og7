package services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Nora on 17.09.2017.
 */
@Path("/order")
public class Service {
    private static Map<Integer, Order> orders = new HashMap<>();
    private static ArrayList<Table> tables = new ArrayList<>();
    private static final AtomicInteger count = new AtomicInteger(0);

    //Adding three tables to the arraylist
    static {
        for (int i = 0; i < 3; i++) {
            tables.add(new Table(i + 1, new ArrayList<Order>()));
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int addOrder(Order order) throws ParseException {
       //Setting an id to an order
        int id = count.incrementAndGet();
        order.setId(id);

        //Adding half an hour to each course the customer has ordered
        String ap = order.getAp();
        String din = order.getDin();
        String des = order.getDes();
        int foods = 0;
        if(!(ap.equals("None"))){ foods++; }
        if(!(din.equals("None"))){ foods++; }
        if(!(des.equals("None"))){ foods++; }

        String fromTime = order.getFromTime();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss a");
        Date date2 = formatter.parse(fromTime+ ":00 AM");
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.add(Calendar.MINUTE, foods*30);
        SimpleDateFormat printTimeFormat = new SimpleDateFormat("HH:mm:ss a");
        order.setToTime(printTimeFormat.format(cal2.getTime()));

        //Checking if there is a table available
        int notAvailable = 0;
        for(int i=0; i<tables.size(); i++){
            if(tables.get(i).checkOrder(order)){
                order.setTableNr(i+1);
                tables.get(i).getOrders().add(order);
                orders.put(order.getId(), order);
                break;
            } else {
                notAvailable++;
            }
        }
        if(notAvailable == tables.size()){
            throw new javax.ws.rs.NotFoundException();
        }
        return order.getId();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("id") String id) {
        if(!(orders.containsKey(id))) {
            throw new javax.ws.rs.NotFoundException("404 not found");
        }
        return orders.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> getOrders () {
        return orders.values();
    }

    @DELETE
    @Path("/{id}")
    public void deleteOrder(@PathParam("id") int id){ orders.remove(id);}

}
