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
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class Enroll {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<String> state;

	@FXML
	private Button choise;

	public static String choises;
	
	public static String company;
	
	accs ac = new accs();
	
	Base base = new Base();

	public static ObservableList<String> states = FXCollections.observableArrayList();

	@FXML
	void initialize() throws SQLException {
		
		states = Base.getInter();
		
		FXMLLoader loader = new FXMLLoader();

		if(!states.isEmpty()) {//Проверка на пустой лист
			state.setItems(states);
			state.setValue(states.get(0));
		}

		choise.setOnAction(event -> {// Выбор
			choises = state.getSelectionModel().getSelectedItem().split(" : ")[0];
			company = state.getSelectionModel().getSelectedItem().split(" : ")[1];
			states.removeAll(states);
			choise.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Competence.fxml"));
			Loader(loader);
		});

		back.setOnAction(event -> {// Назад
			Base.deteleDouplicate();
			states.removeAll(states);
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
