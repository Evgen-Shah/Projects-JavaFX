package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Registration {

	@FXML
	private TextField Phone_number;

	@FXML
	private Button Button_reg;

	@FXML
	private PasswordField pass;

	@FXML
	private Button Back;

	@FXML
	private TextField SecondName;

	@FXML
	private ComboBox<String> adressAutoSchool;

	@FXML
	private TextField Name;

	ControleDataBase db = new ControleDataBase();

	static ObservableList<String> address = FXCollections.observableArrayList();

	public void addAdress() throws SQLException {// Добавление адресса
		try {
			address = ControleDataBase.getAddress();
			adressAutoSchool.setItems(address);
			adressAutoSchool.setValue(address.get(0));
		} catch (Exception e) {
			adressAutoSchool.setValue("Ничего нет");
		}

	}

	public String getAddress() {// ПОлучение названия услуги
		String adress = adressAutoSchool.getSelectionModel().getSelectedItem();
		return adress;
	}

	@FXML
	void initialize() throws SQLException {
		addAdress();
		StyduWindow_controll.oneTimeAddName = false;
		FXMLLoader loader = new FXMLLoader();
		Back.setOnAction(event -> {// Использование кнопки назад
			Back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Study.fxml"));

			Loader(loader);
		});

		Button_reg.setOnAction(event -> {// Использование кнопки для регистрации
		
			try {
				String text = Phone_number.getText();
				Pattern pattern = Pattern.compile("[0-9]{10,12}");
				Matcher matcher = pattern.matcher(text);
				boolean b = matcher.matches();
				if (b) {
					Button_reg.getScene().getWindow().hide();
					ControleDataBase.phone = Long.parseLong(Phone_number.getText());
					db.addStudy(Name.getText(), SecondName.getText(), pass.getText(),
							Long.parseLong(Phone_number.getText()), getAddress());
					loader.setLocation(getClass().getResource("StyduMainWindow.fxml"));
					Loader(loader);
				}
				else {
					Phone_number.setText("Неверно");
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
				loader.setLocation(getClass().getResource("Registration.fxml"));
				Loader(loader);
			}

		});

	}

	public void Loader(FXMLLoader loader) {
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
