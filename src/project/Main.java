package project;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.view.ChangeBudgetController;
import project.view.MainViewController;

public class Main extends Application {
    
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
            Main.primaryStage = primaryStage;
            Main.primaryStage.setTitle("Portfel");           
            showMainView();  
        }      
    
    public static void showMainView() throws IOException, SQLException {        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();    
    }   
    
    public static void showAddOperation() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AddOperation.fxml"));
        BorderPane addOperation = loader.load();     
        Stage addStage = new Stage();
        addStage.setTitle("Nowa operacja");
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(primaryStage);
        Scene scene = new Scene(addOperation);
        addStage.setScene(scene);
        addStage.showAndWait();
    }
    
    public static void showAddCategory() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AddCategory.fxml"));
        BorderPane addCategory = loader.load();       
        Stage addStage = new Stage();
        addStage.setTitle("Nowa kategoria");
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(primaryStage);
        Scene scene = new Scene(addCategory);
        addStage.setScene(scene);
        addStage.showAndWait();
    }
    
    public static void showChangeBudget() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/ChangeBudget.fxml"));
        BorderPane changeBudget = loader.load();       
        Stage addStage = new Stage();
        addStage.setTitle("Zmiana budżetu");
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(primaryStage);
        Scene scene = new Scene(changeBudget);
        addStage.setScene(scene);
        addStage.showAndWait();
    }
    
    public static void showCategories() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("show/ShowCategories.fxml"));
        BorderPane categories = loader.load();
        mainLayout.setCenter(categories);
    }
    
    public static void showExpenses() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("show/ShowExpenses.fxml"));
        BorderPane expenses = loader.load();
        mainLayout.setCenter(expenses);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
