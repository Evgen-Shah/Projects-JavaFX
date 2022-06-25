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
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class Your_train {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<String> state;

	public static ObservableList<String> states = FXCollections.observableArrayList();


	static String finalAns;
	public static String newStatus = "";

	DataSaver ac = new DataSaver();

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		if(!ac.GetStatements(Entry.phones).isEmpty()) {//Проверка на пустой лист
			state.setItems(ac.GetStatements(Entry.phones));
			state.setValue(ac.GetStatements(Entry.phones).get(0));
		}
		else {
			state.setValue("Ничего нет");
		}

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
