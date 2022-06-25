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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Office {

	@FXML
	private ResourceBundle resources;


	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> films;

	@FXML
	private Button back;

	@FXML
	private Text subscription;

	@FXML
	private Button addMonye;

	@FXML
	private Text monye;
	
	public static int mounth = 0;

	public static ObservableList<String> yoursFilms = FXCollections.observableArrayList();//Список фильмов 

	@FXML
	void initialize() throws SQLException {
		yoursFilms = database.getFilmsUsers();
		if(!yoursFilms.isEmpty()) {
			films.setItems(yoursFilms);
			films.setValue(yoursFilms.get(0));
		}else 
			films.setValue("Нет фильмов");
		
		subscription.setText("Месяцев подписки: " + database.getUsersSub());

		monye.setText("Средств на счету: " + database.getMoney());
		FXMLLoader loader = new FXMLLoader();

		addMonye.setOnAction(event -> {// Дейсвтие при нажатии кнопки addMonye
			yoursFilms.removeAll(yoursFilms);
			database.filmsName.removeAll(database.filmsName);
			database.filmsId.removeAll(database.filmsId);
			addMonye.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("pin_cord_card.fxml"));
			LoaderFXML(loader);
		
		});

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			yoursFilms.removeAll(yoursFilms);
			database.filmsName.removeAll(database.filmsName);
			database.filmsId.removeAll(database.filmsId);
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("choise_action.fxml"));
			LoaderFXML(loader);
		});
	}

	public void LoaderFXML(FXMLLoader loader) {// Метод для прогрузки окон
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
