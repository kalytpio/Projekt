package project.view;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.Database;
import project.Main;


public class AddCategoryController {
    
    @FXML
    private TextField categoryName;
    @FXML
    private Button cancel;
    @FXML
    private Label warning;
    public Database db;
    String errors;
    
    @FXML
    private void close() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void add() throws SQLException, IOException {
        errors = "";
        String name = categoryName.getText();
        if(validate(name)) {
            db.saveCategory(name);
            close(); 
            Main.showCategories();
        }
        else {
            errors += "Wprowadź jeszcze raz.";
            warning.setVisible(true);
            warning.setText(errors);
        }
    }
    
    private boolean validate(String name) {
        if(name.isEmpty()) 
            errors += "Nic nie wprowadziłeś!\n";        
        else if(name.contains(" "))
            errors += "Nazwa nie może zawierać spacji!\n";      
        
        if(errors.isEmpty()) return true;
        else return false;
    }
    
}
