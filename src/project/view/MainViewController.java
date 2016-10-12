package project.view;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Database;
import project.Main;


public class MainViewController {   
    
    public Database db;
    @FXML
    private Label budget;
    private Main main; 
    
    @FXML
    public void initialize() throws SQLException {
        String cash = db.getCash();
        budget.setText(cash);
    }
    
    @FXML
    private void goAddOperation() throws IOException {
        main.showAddOperation();
    }
    
    @FXML
    private void goChangeBudget() throws IOException {
        main.showChangeBudget();
    }
    
    @FXML
    private void goAddCategory() throws IOException {
        main.showAddCategory();
    }
    
    @FXML
    private void goShowCategories() throws IOException {
        main.showCategories();
    }
    
    @FXML
    private void goShowExpenses() throws IOException {
        main.showExpenses();
    }
    
    @FXML
    private void goShowMain() throws IOException, SQLException {       
        main.showMainView();      
    }      
    
}
    
