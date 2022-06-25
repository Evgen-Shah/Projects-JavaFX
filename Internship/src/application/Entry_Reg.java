package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Entry_Reg {

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
	
	public static String adminName;
	
	public static String phones;
	
	@FXML
	private Button reg;
	
	accs ac = new accs();
	
	Base base = new Base();

	@FXML
	void initialize() {//Метод, который вызывается при прогрузки визуального окна

		FXMLLoader loader = new FXMLLoader();

		entry.setOnAction(event -> {// Назад
			if(base.checkCompany(phone.getText()).equals(pass.getText())) {// Проверка ввода информации
				adminName = phone.getText();
				entry.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("Menu_admin.fxml"));
				Loader(loader);
			}
			else if(base.checkPass(phone.getText()).equals(pass.getText())) {
				Registration.phones = phone.getText();
				phones = phone.getText();
				entry.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("Menu.fxml"));
				Loader(loader);
			}
			else {
				phone.setText("Неверный номер телефона!");
				pass.setText("Неверный пароль!");
			}
		});

		reg.setOnAction(event -> {// Регистрация
	
			Your_statements.finalAns = "";
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
