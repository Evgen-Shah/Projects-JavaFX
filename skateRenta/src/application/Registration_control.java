package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Registration_control {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button reg;

	@FXML
	private TextField year;

	@FXML
	private TextField phone;

	@FXML
	private TextField pass;

	@FXML
	private TextField name;

	@FXML
	private TextField second_name;

	@FXML
	private Button back;

	BaseControl bs = new BaseControl();

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("RegAndEntry.fxml"));
			LoaderFXML(loader);

		});

		reg.setOnAction(event -> {// регитсриаця
			try {
				String text = phone.getText();
				Pattern pattern = Pattern.compile("[0-9]{10,12}");
				Matcher matcher = pattern.matcher(text);
				boolean b = matcher.matches();
				if (!b) {
					phone.setText("неправильно");
				}
				if (bs.getCheckHpone(phone.getText())) {
					phone.setText("Такой номер телефона уже есть ");
				}
				else if (tryParseInt(name.getText()) || tryParseInt(second_name.getText())) {
					name.setText("Только буквы");
					second_name.setText("Только буквы");
				} else {
					int lenghtPhone = phone.getText().length();
					if (lenghtPhone >= 12) {
						phone.setText("Меньше 12");
					}
					if (Integer.parseInt(year.getText()) > 6) {
						bs.addClients(name.getText(), second_name.getText(), Integer.parseInt(year.getText()),
								Long.parseLong(phone.getText()), pass.getText());
						reg.getScene().getWindow().hide();
						Entry_control.number = Long.parseLong(phone.getText());

						loader.setLocation(getClass().getResource("Options.fxml"));
						LoaderFXML(loader);

					} else {
						year.setText("Больше 6");
					}
				}
			} catch (NumberFormatException | SQLException e) {

			}

		});

	}

	public void LoaderFXML(FXMLLoader loader) {
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

	boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
