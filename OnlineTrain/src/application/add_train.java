package application;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class add_train {

	@FXML
	private ResourceBundle resources;

	@FXML
	private Button add;

	@FXML
	private TextField name;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<String> timeTrain;

	@FXML
	private TextField time;

	@FXML
	private ChoiceBox<String> train;

	ObservableList<String> trains = FXCollections.observableArrayList();

	@FXML
	void initialize() {//Метод, который вызывается при прогрузки визуального окна
		GetName();

		FXMLLoader loader = new FXMLLoader();

		add.setOnAction(event -> {// Нажатие кнопки добавить
			if(!name.getText().isEmpty()) {
				ChoiseTrain.trains.add(name.getText());
				add.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("add_train.fxml"));
				Loader(loader);
			}
			else {
				name.setText("Название не может быть пустым!");
			}
			if(!time.getText().isEmpty() && time.getText().matches("(?:[01][0-9]|2[0-3]):[0-5][0-9]")) {
				if(name.getText().equals("Название не может быть пустым!")) {
					name.clear();
				}
				else {
					ChoiseTrain.times.add(time.getText());
					add.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("add_train.fxml"));
					Loader(loader);
				}
			}
			else {
				time.setText("Неверный формат времени");
			}
		});

		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_admin.fxml"));
			Loader(loader);
		});
	}

	void GetName() {// Получения  
		if(!ChoiseTrain.trains.isEmpty()) {
			train.setItems(ChoiseTrain.trains);
			train.setValue(ChoiseTrain.trains.get(0));
		}

		if(!ChoiseTrain.times.isEmpty()) {
			timeTrain.setItems(ChoiseTrain.times);
			timeTrain.setValue(ChoiseTrain.times.get(0));
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
