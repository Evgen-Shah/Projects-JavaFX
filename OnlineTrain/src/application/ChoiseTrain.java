package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class ChoiseTrain {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;


    @FXML
    private DatePicker date;

	
	@FXML
	private ChoiceBox<String> train;

	@FXML
	private Button choise;
	
    @FXML
    private ChoiceBox<String> time;


	public static String choisesTrain;
	public static String choisesTime;
	
	DataSaver ac = new DataSaver();

	public static ObservableList<String> trains = FXCollections.observableArrayList("Йога","Planka pump","Garage","Dance","Streching","Fitness");
	
	public static ObservableList<String> times = FXCollections.observableArrayList("9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00");
	//Все тренировки и время

	@FXML
	void initialize() {

		date.setValue(LocalDate.now());
		FXMLLoader loader = new FXMLLoader();

		if(!trains.isEmpty()) {//Проверка на пустой лист
			train.setItems(trains);
			train.setValue(trains.get(0));
		}


		if(!times.isEmpty()) {//Проверка на пустой лист
			time.setItems(times);
			time.setValue(times.get(0));
		}
		
		choise.setOnAction(event -> {// Выбор
			String dateStr = date.getValue().toString();
			choisesTrain = train.getSelectionModel().getSelectedItem();
			choisesTime = time.getSelectionModel().getSelectedItem();

			ac.AddNumberTrainAndTime(Entry.phones + ":" + choisesTrain + ":" + choisesTime + ":" + dateStr);

			choise.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Ok.fxml"));
			Loader(loader);
		});

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
