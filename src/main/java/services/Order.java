package services;


/**
 * Created by Nora on 20.09.2017.
 */
public class Order {
    private String fromTime;
    private String toTime;
    private int antPers;
    private String ap;
    private String din;
    private String des;
    private String drink;
    private int antDrinks;
    private int id;
    private int tableNr;

    public Order(){
    }

    public String getFromTime(){
        return fromTime;
    }
    public void setFromTime(String fromTime){
        this.fromTime = fromTime;
    }

    public String getToTime(){ return toTime; }
    public void setToTime(String toTime){
        this.toTime = toTime;
    }

    public int getAntPers(){
        return antPers;
    }
    public void setAntPers(int antPers){
        this.antPers = antPers;
    }

    public String getAp(){ return ap; }
    public void setAp(String ap){ this.ap = ap; }

    public String getDin(){ return din; }
    public void setDin(String din){ this.din = din; }

    public String getDes(){ return des; }
    public void setDes(String des){ this.des = des; }

    public String getDrink(){ return drink; }
    public void setDrink(String drink){ this.drink = drink; }

    public int getAntDrinks(){
        return antDrinks;
    }
    public void setAntDrinks(int antDrinks){
        this.antDrinks = antDrinks;
    }

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public int getTableNr(){ return tableNr; }
    public void setTableNr(int tableNr){ this.tableNr = tableNr; }


}
