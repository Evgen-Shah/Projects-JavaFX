package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class Delete_train_admin {

    @FXML
    private ChoiceBox<String> name;

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> time;

    @FXML
    private Button delete;

    @FXML
    void initialize() {
    	GetName();
    	FXMLLoader loader = new FXMLLoader();
    	
    	delete.setOnAction(event -> {// Назад
    		ChoiseTrain.trains.remove(name.getSelectionModel().getSelectedItem());
    		ChoiseTrain.times.remove(time.getSelectionModel().getSelectedItem());
    		delete.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Delete_train_admin.fxml"));
			Loader(loader);
		});
    	
    	back.setOnAction(event -> {// Назад
    		back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_admin.fxml"));
			Loader(loader);
		});
    }
    
    void GetName() {// Получения имени работодателя 
			name.setItems(ChoiseTrain.trains);
			time.setItems(ChoiseTrain.times);
	}
    
    
	public void Loader (FXMLLoader loader) {
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
