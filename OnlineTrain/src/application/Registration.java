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

public class Registration {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField names;

	@FXML
	private TextField phone;

	@FXML
	private Button reg;

	@FXML
	private TextField pass;

	@FXML
	private TextField date;

	@FXML
	private Button back;

	public static String name;
	public static String phones;
	public static String dateBirth;

	@FXML
	void initialize() {

		FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Entry.fxml"));
			Loader(loader);
		});

		reg.setOnAction(event -> {// Назад
			if(!names.getText().matches("[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*")) {
				names.setText("ФИО может содержать только русские буквы!");
			}
			else if(!phone.getText().matches("([0-9]{11})|([0-9]{10})")) {
				phone.setText("Номер телефона может состоять только из цифр!");
			}			
			else if(!date.getText().matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
				date.setText("Неверный формат даты рождения!");
			}
			else if(pass.getText().isEmpty()) {
				pass.setText("Пароль не может быть пустым!");
			}
			else {
				name = names.getText();
				phones = phone.getText();
				dateBirth = date.getText();
				
				Entry.phones = phones = phone.getText();
				
				DataSaver.AddUsers(phones + ":" + name  + ":" + dateBirth);
				
				DataSaver.AddNumberAndPass(phones + ":" + pass.getText());// Добавляем в лист номер телефона и пароль нового пользователя.
				
			
				reg.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("Menu.fxml"));
				Loader(loader);
			}


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
