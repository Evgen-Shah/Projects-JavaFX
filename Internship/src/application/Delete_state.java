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

public class Delete_state {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<String> state;

	@FXML
	private Button delete;

	Base base = new Base();

	ObservableList<String> states = FXCollections.observableArrayList();

	@FXML
	void initialize() throws SQLException {
		GetName();
		FXMLLoader loader = new FXMLLoader();

		delete.setOnAction(event -> {// Назад
			// Enroll.states.remove(state.getSelectionModel().getSelectedItem());
			try {
				base.deletInter(state.getSelectionModel().getSelectedItem());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			states.removeAll(states);
			delete.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Delete_state.fxml"));
			Loader(loader);
		});

		back.setOnAction(event -> {// Назад
			states.removeAll(states);
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_admin.fxml"));
			Loader(loader);
		});
	}

	void GetName() throws SQLException {// Получение имени
		states = Base.getInterForCompany();

		if (!states.isEmpty()) {// Проверка на пустой лист
			state.setItems(states);
			state.setValue(states.get(0));
		}
	}

	public void Loader(FXMLLoader loader) {
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
