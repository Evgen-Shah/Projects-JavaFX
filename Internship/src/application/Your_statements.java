package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Your_statements {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Check;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<String> state;

	@FXML
	private Text status;

	public static ObservableList<String> states = FXCollections.observableArrayList();
	public static ObservableList<String> statesAns = FXCollections.observableArrayList();

	static int nextStatus = 0;

	static	boolean  randomOrNot = true;
	static	boolean  newCheckStatus = false;

	static String finalAns;
	public static String newStatus = "";
	
	Base base = new Base();

	accs ac = new accs();

	@FXML
	void initialize() throws SQLException {
		
		GetName();
			
		FXMLLoader loader = new FXMLLoader();
	
		back.setOnAction(event -> {// Назад
			states.removeAll(states);
			statesAns.removeAll(statesAns);
			Base.deteleDouplicate();
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu.fxml"));
			Loader(loader);
		});
		
		Check.setOnAction(event -> {// Назад
			try {
				status.setText(Base.checkStatus(state.getSelectionModel().getSelectedItem().split(" компания:")[0].split("стажировка: ")[1]));
			} catch (SQLException e) {
				status.setText("На рассмотрении");
			}
		});
	}
	
	void GetName() {// Получения имени работодателя
		try {
			states = Base.getInterUser();
			statesAns = Base.getAnsUser();
			if(statesAns.isEmpty())
				Base.exp();
			states.addAll(statesAns);
			System.out.println(!states.isEmpty());
			if (!states.isEmpty() && !statesAns.isEmpty()) {// Проверка на пустой лист
				state.setItems(states);
				state.setValue(states.get(0));
			}
		} catch (SQLException e) {
			try {
				states.removeAll(states);
				states = Base.getInterUser();
				if (!states.isEmpty()) {// Проверка на пустой лист
					state.setItems(states);
					state.setValue(states.get(0));
				}
			} catch (SQLException e1) {
				try {
					statesAns.removeAll(statesAns);
					statesAns = Base.getAnsUser();
					if (!statesAns.isEmpty()) {// Проверка на пустой лист
						state.setItems(statesAns);
						state.setValue(statesAns.get(0));
					}
				} catch (SQLException e2) {
					state.setValue("Ничего нет!");
				}
			}
		}
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
