package project.show;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import project.Category;
import project.Database;


public class ShowCategoriesController {
    
    ObservableList<Category> categoriesList;
    @FXML
    private TableView<Category> table;
    @FXML
    private TableColumn<Category, String> cat;
    @FXML
    private TableColumn<Category, String> id;
    private Database db;
    
    @FXML
    private void initialize() throws SQLException {
        categoriesList = db.showCategories();
        table.itemsProperty().setValue(categoriesList);
        cat.setCellValueFactory(new PropertyValueFactory<Category, String>("cat"));
        id.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));       
    }
    
    @FXML
    private void delete() throws SQLException {
        int x = table.getSelectionModel().getSelectedIndex();
        String del = String.valueOf(id.getCellData(x));
        if(del.equals("1")) 
            JOptionPane.showMessageDialog(null, "Nie można usunąć tej kategorii!.", "Błąd!", JOptionPane.ERROR_MESSAGE); 
        else {
            db.deleteCategory(del);
            initialize();
        }
    }
    
}
