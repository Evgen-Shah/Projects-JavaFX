package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Settings_control {

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
	void initialize() throws SQLException {
		setElements();
		FXMLLoader loader = new FXMLLoader();
		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Options.fxml"));
			LoaderFXML(loader);

		});

		reg.setOnAction(event -> {// Изменение 
			reg.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("change_ok.fxml"));
			LoaderFXML(loader);

			try {
				bs.updateData(name.getText(), second_name.getText(), Long.parseLong(phone.getText()), pass.getText(),
						Integer.parseInt(year.getText()));
				Entry_control.number = Long.parseLong(phone.getText());
			} catch (NumberFormatException | SQLException e) {

			}
		});
	}

	void setElements() throws SQLException {//Получение элеменов инфмрации
		String element = bs.getInfoClient();
		String elements[] = element.split(" ");
		name.setText(elements[0]);
		second_name.setText(elements[1]);
		year.setText(elements[2]);
		phone.setText(elements[3]);
		pass.setText(elements[4]);
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
}
