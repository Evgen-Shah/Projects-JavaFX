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

public class Competence {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> educ;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<String> time;

	@FXML
	private Button choise;

	@FXML
	private ChoiceBox<String> exp;
	
	Base base = new Base();

	Whos_click wc = new Whos_click();

	public static String[] nameOrg;

	public static ObservableList<String> choiseEduc = FXCollections.observableArrayList("Высшее образование","Среднее образование","Общее образование","Без образования"); 
	public static ObservableList<String> choiseTime = FXCollections.observableArrayList("От 1 - 10 часов в неделю","От 10 - 20 часов в неделю","От 20 - 30 часов в неделю","От 30 - 40 часов в неделю"); 
	public static ObservableList<String> cgoiseExp = FXCollections.observableArrayList("Без опыта","1 - 3 года","3 - 6 лет","7 + лет"); 


	@FXML
	void initialize() {//Метод, который вызывается при прогрузки визуального окна

		FXMLLoader loader = new FXMLLoader();

		educ.setItems(choiseEduc);// Установка всех значений в ChoiceBox
		educ.setValue(choiseEduc.get(0));

		time.setItems(choiseTime);
		time.setValue(choiseTime.get(0));

		exp.setItems(cgoiseExp);
		exp.setValue(cgoiseExp.get(0));

		choise.setOnAction(event -> {// Выбор
			try {
				base.addBid(Enroll.choises,Enroll.company,educ.getSelectionModel().getSelectedItem() + " " + time.getSelectionModel().getSelectedItem() + " " + exp.getSelectionModel().getSelectedItem());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			choise.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Sent.fxml"));
			Loader(loader);

		});

		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Enroll.fxml"));
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
