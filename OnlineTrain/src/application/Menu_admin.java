package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Menu_admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Entry.fxml"));
			Loader(loader);
		});
		
		add.setOnAction(event -> {// Открытие окна с добавление
			add.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("add_train.fxml"));
			Loader(loader);
		});
		
		delete.setOnAction(event -> {// Открытие окна с удалением
			delete.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Delete_train_admin.fxml"));
			Loader(loader);
		});
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
