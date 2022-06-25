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

public class Menu_admin_class {

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
	private Button history;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		
		delete.setOnAction(event -> {// Действие при нажатии кнопки delete
			delete.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Delete_asset.fxml"));
			LoaderFXML(loader);
		});
		
		add.setOnAction(event -> {// Действие при нажатии кнопки add
			add.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Add_asset.fxml"));
			LoaderFXML(loader);
		});

		back.setOnAction(event -> {// Действие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Login_window.fxml"));
			LoaderFXML(loader);
		});
		
		history.setOnAction(event -> {// Действие при нажатии кнопки back
			history.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Histroy_actions_actives.fxml"));
			LoaderFXML(loader);
		});
	}
	
	public void LoaderFXML(FXMLLoader loader) {
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
