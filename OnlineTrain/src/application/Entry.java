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

public class Entry {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button entry;

	@FXML
	private TextField phone;

	@FXML
	private TextField pass;
	
	public static String phones;

	@FXML
	private Button admin;

	@FXML
	private Button reg;

	DataSaver ac = new DataSaver();

	@FXML
	void initialize() {//Метод, который вызывается при прогрузки визуального окна

		FXMLLoader loader = new FXMLLoader();

		entry.setOnAction(event -> {// Назад
			if(ac.CheckPass(pass.getText()) && ac.CheckPhone(phone.getText())) {
				Registration.phones = phone.getText();
				phones = phone.getText();
				entry.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("Menu.fxml"));
				Loader(loader);
			}
			else {
				phone.setText("Нет такого номера телефона!");
				pass.setText("Неверный пароль!");
			}
		});
		
		admin.setOnAction(event -> {// Регистрация
			admin.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Check_admin_pass.fxml"));
			Loader(loader);
		});

		reg.setOnAction(event -> {// Регистрация
			reg.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Registration.fxml"));
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
