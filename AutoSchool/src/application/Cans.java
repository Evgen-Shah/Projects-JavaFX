package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Cans {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Cancel;

	@FXML
	private Button back;

	@FXML
	private ComboBox<String> cans;

	ControleDataBase db = new ControleDataBase();

	static ObservableList<String> addressAdd = FXCollections.observableArrayList();

	@FXML
	void initialize() {
		try {
			addAdress();
			Controller_coach.annOneTime = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// Использование кнопки для входа инструктором
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("SettingsCoach.fxml"));
			Loader(loader);
		});

		Cancel.setOnAction(event -> {// Использование кнопки для входа инструктором
			String[] second = getAddress().split(" ");
			try {
				db.cansel(second[1]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cancel.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("SettingsCoach.fxml"));
			Controller_coach.annOneTime = false;
			Loader(loader);
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

	public void addAdress() throws SQLException {// Добавление адресса
		try {
			addressAdd = ControleDataBase.getOrders();
			cans.setItems(addressAdd);
			cans.setValue(addressAdd.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			cans.setValue("Ничего нет");
		}

	}

	public String getAddress() {// ПОлучение названия услуги
		String adressStr = cans.getSelectionModel().getSelectedItem();
		return adressStr;
	}
}
