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

public class Menu {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button cancel;

	@FXML
	private Button sign_up;
	
    @FXML
    private Button lk;

	@FXML
	private Button back;

	@FXML
	private Button yours_roll;

	@FXML
	void initialize() {

		FXMLLoader loader = new FXMLLoader();

		sign_up.setOnAction(event -> {// Записаться
			sign_up.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("ChoiseTrain.fxml"));
			Loader(loader);
		});

		yours_roll.setOnAction(event -> {// Ваши записи
			yours_roll.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Your_train.fxml"));
			Loader(loader);
		});

		cancel.setOnAction(event -> {// Отмена записи
			cancel.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Cancel_train.fxml"));
			Loader(loader);
		});
		
		lk.setOnAction(event -> {// Отмена записи
			lk.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("lk.fxml"));
			Loader(loader);
		});

		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Entry.fxml"));
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
