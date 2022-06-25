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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Update_driver {

	DataBaseController db = new DataBaseController();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField Auto;

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
	private TextField Name;

	/**
	 * @throws SQLException
	 * 
	 */
	@FXML
	void initialize() throws SQLException {
		String old = db.getInfoClient();
		String[] oldArr = old.split(" ");

		Auto.setText(oldArr[3]);
		Phone_number.setText(oldArr[2]);
		pass.setText(oldArr[4]);
		SecondName.setText(oldArr[1]);
		Name.setText(oldArr[0]);

		FXMLLoader loader = new FXMLLoader();

		Back.setOnAction(event -> {// Назад
			Back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("ChoiseWindow.fxml"));
			Loader(loader);

		});

		Button_reg.setOnAction(event -> {// обновление
			Button_reg.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("ChoiseWindow.fxml"));
			Loader(loader);
			try {
				db.updateData(Name.getText(), SecondName.getText(), Long.parseLong(Phone_number.getText()),
						Long.parseLong(pass.getText()), Auto.getText());
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
