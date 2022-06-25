package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class lk {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Text date;

	@FXML
	private Text phone;

	@FXML
	private Button back;

	@FXML
	private Text fio;

	DataSaver ds = new DataSaver();

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		
		ObservableList<String> info = DataSaver.GetInfoUsers(Entry.phones);
		
		date.setText("Дата рождения: " + info.get(0).split(":")[2]);
		phone.setText("Номер телефона" + info.get(0).split(":")[0]);
		fio.setText("ФИО: " + info.get(0).split(":")[1]);
		
		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu.fxml"));
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
