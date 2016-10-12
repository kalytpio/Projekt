package project;

import java.sql.Date;
import java.time.LocalDate;


public class Operation {
    
    private String name, cat;
    private int id;
    private double cash;
    private LocalDate date;
    
    public Operation(int id, String name, String cat, Date dt, double cash) {
        this.id = id;
        this.name = name;
        this.cat = cat;
        this.date = dt.toLocalDate();
        this.cash = cash;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCat() {
        return cat;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public double getCash() {
        return cash;
    }
}
