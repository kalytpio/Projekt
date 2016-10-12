package project.view;


import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.Database;
import project.Main;


public class ChangeBudgetController {
    
    @FXML
    public TextField budget;
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
    private void change() throws SQLException, IOException {
        errors = "";
        String text = budget.getText();
        if(validate(text)) {
            double cash = Double.parseDouble(text);
            db.changeCash(cash);
            close();
            Main.showMainView();
        }
        else {
            warning.setVisible(true);
            errors += "Wprowadź jeszcze raz.";
            warning.setText(errors);     
        }
    }
    
    private boolean validate(String text) {            
        try { 
            double cash = Double.parseDouble(text);
            if(cash<0) errors += "Wprowadzona wartość jest ujemna!\n";
            int index = text.indexOf('.');
            if(index != -1) {       
                int length = text.length()-1;
                if(length-index>2) errors += "Za dużo miejsc po przecinku!\n";
            }
        } catch(NumberFormatException e) {
            errors += "Wprowadzona wartość nie jest liczbą!\n";
        }                   
        if(errors.isEmpty()) return true;
        else return false;
    }
    
}
