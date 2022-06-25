package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class pay {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField pinCode;

	@FXML
	private Button back;

	@FXML
	private Button pay;

	@FXML
	private TextField card;

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();

		pay.setOnAction(event -> {// Назад
			if(card.getText().matches("[0-9]{16}")) {
				pay.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("Add_state.fxml"));
				Loader(loader);
			}
			else 
				card.setText("Номер карты не верен!");
		});
		
		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_admin.fxml"));
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
