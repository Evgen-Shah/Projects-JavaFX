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

public class Cancel_state {

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

	Base bs = new Base();

	public static ObservableList<String> states = FXCollections.observableArrayList();// Лист со стажировками

	accs ac = new accs();

	@FXML
	void initialize() throws SQLException {// Метод, который вызывается при прогрузки визуального окна
		FXMLLoader loader = new FXMLLoader();

		states = Base.getInterUser();

		if (!states.isEmpty()) {// Проверка на пустой лист
			state.setItems(states);
			state.setValue(states.get(0));
		} else {
			state.setValue("Ничего нет");
		}

		delete.setOnAction(event -> {// Удаление
			try {
				bs.addCancel(
						state.getSelectionModel().getSelectedItem().split(" компания:")[0].split("стажировка: ")[1],
						state.getSelectionModel().getSelectedItem().split(" компания:")[1]);
				bs.deleteBid(
						state.getSelectionModel().getSelectedItem().split(" компания:")[0].split("стажировка: ")[1]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Base.deteleDouplicate();
			states.removeAll(states);
			delete.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu.fxml"));
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
