package project;


public class Category {
    
    private String cat;
    private int id;
    
    public Category(int id,String cat) {
        this.cat = cat;
        this.id = id;
    }
    
    public String getCat() {
        return cat;
    }
    
    public int getId() {
        return id;
    }
    
}
