package project.view;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.Database;
import project.Main;


public class AddOperationController {
    
    ObservableList<String> categoriesList;
    
    @FXML 
    private TextField operationName;
    @FXML 
    private TextField value;
    @FXML
    private ChoiceBox category;
    @FXML 
    private DatePicker date;
    @FXML
    private Button cancel;
    @FXML
    private Label warning;
    String errors;
    public Database db;
      
    @FXML
    private void initialize() throws SQLException {
        categoriesList = db.getCategories();
        date.setValue(LocalDate.now());      
        category.setItems(categoriesList);
        category.setValue("Wpłata");
    }
    
    @FXML
    private void close() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void add() throws SQLException, IOException {
        errors = "";
        String name = operationName.getText();
        String cost = value.getText();
        String cat = category.getSelectionModel().getSelectedItem().toString();
        LocalDate dt = date.getValue();
        
        if(validate(name, cost)) {
            double cash = Double.parseDouble(cost);
            db.saveOperation(name, cash, cat, dt);
            close();    
            Main.showExpenses();
        }
        else {
            errors += "Popraw dane";
            warning.setVisible(true);
            warning.setText(errors);
        }
    }
    
    private boolean validate(String name, String cost) {
        try { 
            double cash = Double.parseDouble(cost);
            if(cash<0) errors += "Wprowadzona wartość jest ujemna!\n";
            int index = cost.indexOf('.');
            if(index != -1) {       
                int length = cost.length()-1;
                if(length-index>2) errors += "Za dużo miejsc po przecinku!\n";
            }
        } catch(NumberFormatException e) {
            errors += "Wprowadzona wartość nie jest liczbą!\n";
        }
        if(name.isEmpty()) 
            errors += "Nie wprowadziłeś nazwy!\n";        
        else if(name.charAt(0) == ' ')
            errors += "Nazwa nie może zaczynać się od spacji!\n";
        
        if(errors.isEmpty()) return true;
        else return false;
    }
    
}
