package project.show;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import project.Database;
import project.Operation;


public class ShowExpensesController {
    
    ObservableList<Operation> operationsList;
    ObservableList<String> categoriesList;
    ObservableList<String> monthsList = FXCollections.observableArrayList("Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","Październik","Listopad","Grudzień");
    ObservableList<String> yearsList = FXCollections.observableArrayList();
    private CategoryAxis xAxisC = new CategoryAxis();
    private NumberAxis yAxisC = new NumberAxis();
    private CategoryAxis xAxisM = new CategoryAxis();
    private NumberAxis yAxisM = new NumberAxis();
    private Database db;
    @FXML private TableView<Operation> table;   
    @FXML private TableColumn<Operation, String> id, name, cat, date, cash;    
    @FXML private Button delete;   
    @FXML private Tab tab, tabC;   
    @FXML private GridPane filter;  
    @FXML private RadioButton all, day, month, year;  
    @FXML private CheckBox catB, dateB;  
    @FXML private DatePicker dateP;   
    @FXML private ListView catL, monthL, yearL;
    @FXML private BarChart catChart, monthChart;
    
    @FXML
    private void initialize() throws SQLException {
        operationsList = db.showOperations();
        categoriesList = db.categories().sorted();
        yearsList = db.years().sorted();
        table.itemsProperty().setValue(operationsList);
        id.setCellValueFactory(new PropertyValueFactory<>("id")); 
        name.setCellValueFactory(new PropertyValueFactory<>("name")); 
        cat.setCellValueFactory(new PropertyValueFactory<>("cat"));
        date.setCellValueFactory(new PropertyValueFactory<>("date")); 
        cash.setCellValueFactory(new PropertyValueFactory<>("cash")); 
        shhd();        
        dateP.setValue(LocalDate.now());      
        catL.setItems(categoriesList);
        catL.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);   
        monthL.setItems(monthsList);    
        monthL.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
        yearL.setItems(yearsList);    
        yearL.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);                                       
    }
    
    @FXML
    private void filter() throws SQLException {
        String m = "";
        String y = "";
        int c = catL.getSelectionModel().getSelectedItems().size();
        String subquery = "";
        if(catB.isSelected())
            for(int x=0; x<c; x++) {
                if(x==0)
                    subquery += "category='"+catL.getSelectionModel().getSelectedItems().get(x).toString()+"'";
                else
                    subquery += "category='"+catL.getSelectionModel().getSelectedItems().get(x).toString()+"' OR ";
            }
        if(all.isSelected() || (!catB.isSelected() && !dateB.isSelected()))
            initialize();
        else {
            if(month.isSelected())
                m = monthL.getSelectionModel().getSelectedItems().toString();
            if(month.isSelected() || year.isSelected())
                y = yearL.getSelectionModel().getSelectedItems().toString();
            if(m.contains("Styczeń")) m = m.replaceAll("Styczeń", "JANUARY");
            if(m.contains("Luty")) m = m.replaceAll("Luty", "FEBUARY");
            if(m.contains("Marzec")) m = m.replaceAll("Marzec", "MARCH");
            if(m.contains("Kwiecień")) m = m.replaceAll("Kwiecień", "APRIL");
            if(m.contains("Maj")) m = m.replaceAll("Maj", "MAY");
            if(m.contains("Czerwiec")) m = m.replaceAll("Czerwiec", "JUNE");
            if(m.contains("Lipiec")) m = m.replaceAll("Lipiec", "JULY");
            if(m.contains("Sierpień")) m = m.replaceAll("Sierpień", "AUGUST");
            if(m.contains("Wrzesień")) m = m.replaceAll("Wrzesień", "SEPTEMBER");
            if(m.contains("Październik")) m = m.replaceAll("Październik", "OCTOBER");
            if(m.contains("Listopad")) m = m.replaceAll("Listopad", "NOVEMBER");
            if(m.contains("Grudzień")) m = m.replaceAll("Grudzień", "DECEMBER"); 
            String query = "SELECT * FROM operations";
            if(day.isSelected())
                query += " WHERE date='"+dateP.getValue()+"'";
            if(catB.isSelected() && day.isSelected())
                query += " AND "+subquery;
            else if(catB.isSelected())
                query += " WHERE "+subquery;
            operationsList = db.getOperations(query, m, y);
            table.itemsProperty().setValue(operationsList);
            id.setCellValueFactory(new PropertyValueFactory<>("id")); 
            name.setCellValueFactory(new PropertyValueFactory<>("name")); 
            cat.setCellValueFactory(new PropertyValueFactory<>("cat"));
            date.setCellValueFactory(new PropertyValueFactory<>("date")); 
            cash.setCellValueFactory(new PropertyValueFactory<>("cash")); 
        }
    }
    
    @FXML
    private void delete() throws SQLException {
        int x = table.getSelectionModel().getSelectedIndex();
        String del = String.valueOf(id.getCellData(x));
        db.deleteOperation(del);
        initialize();        
    }
    
    @FXML
    private void sh() throws SQLException {
        catChart.getData().clear();
        monthChart.getData().clear();
        if(tab.isSelected()) {
            delete.setVisible(true);
            initialize();
        }
        else {
            delete.setVisible(false);
            if(tabC.isSelected()) {               
                for(int x=0; x<categoriesList.size(); x++) 
                    catChart.getData().addAll(db.chartDataC(categoriesList.get(x))); 
            }
            else {               
                for(int y=0; y<yearsList.size(); y++) 
                    monthChart.getData().addAll(db.chartDataM(yearsList.get(y)));
            }
        }
    }    
    
    @FXML
    private void shhd() {
        if(all.isSelected())
            filter.setDisable(true);
        else
            filter.setDisable(false);
    }   
    
    @FXML
    private void checkD() {        
        if(dateB.isSelected()) {
            day.setDisable(false);
            month.setDisable(false);
            year.setDisable(false);
            markD();
        }
        else {
            day.setDisable(true);
            month.setDisable(true);
            year.setDisable(true);
            day.setSelected(false);
            month.setSelected(false);
            year.setSelected(false);
            dateP.setDisable(true);
            monthL.setDisable(true);
            yearL.setDisable(true);
        }
    } 
    
    @FXML
    private void checkC() {
        if(catB.isSelected())
            catL.setDisable(false);
        else
            catL.setDisable(true);
    }
    
    @FXML
    private void markD() {
        day.setSelected(true);
        dateP.setDisable(false);
        month.setSelected(false);
        year.setSelected(false);  
        monthL.setDisable(true);
        yearL.setDisable(true);
        monthL.getSelectionModel().select(-1);
        yearL.getSelectionModel().select(-1);
    }
    
    @FXML
    private void markM() {
        dateP.setDisable(true);
        monthL.setDisable(false);
        yearL.setDisable(false);
        monthL.getSelectionModel().select(-1);
        yearL.getSelectionModel().select(-1);
    }
    
    @FXML
    private void markL() {
        dateP.setDisable(true);
        monthL.setDisable(true);
        yearL.setDisable(false);
        yearL.getSelectionModel().select(-1);
    }
    
}
