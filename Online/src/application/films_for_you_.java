package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class films_for_you_ {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> films;

	@FXML
	private Button ok;

	@FXML
	private Button back;

	public static String priceFilm = "";

	@FXML
	private Text noMOney;
	
	@FXML
	private Text sub;

	buy_film_ by = new buy_film_();
	
	database data = new database();

	@FXML
	void initialize() throws NumberFormatException, SQLException {// Метод при инициализации визуального окна 
		noMOney.setVisible(false);
		FXMLLoader loader = new FXMLLoader();
		
		if(Long.parseLong(database.getUsersSub()) != 0) {
			buy_film_.filmsForYou.set(0, buy_film_.filmsForYou.get(0).split(" цена: ")[0] + " цена: 0");
			sub.setVisible(true);
			films.setItems(buy_film_.filmsForYou);
			films.setValue(buy_film_.filmsForYou.get(0));
		}
		
		else {
			sub.setVisible(false);
			films.setItems(buy_film_.filmsForYou);
			films.setValue(buy_film_.filmsForYou.get(0));
		}
		
		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("buy_film.fxml"));
			LoaderFXML(loader);
			buy_film_.filmsForYou.removeAll(buy_film_.filmsForYou);
		});

		ok.setOnAction(event -> {// Дейсвтие при нажатии кнопки ok
			try {
				if(Long.parseLong(database.getMoney()) > Long.parseLong(films.selectionModelProperty().getValue().getSelectedItem().split(" цена: ")[1])) {
					data.addCard(Long.parseLong(database.getMoney()),"Покупка фильма на " + Long.parseLong(films.selectionModelProperty().getValue().getSelectedItem().split(" цена: ")[1]));
					data.updateMoney(Long.parseLong(database.getMoney()) - Long.parseLong(films.selectionModelProperty().getValue().getSelectedItem().split(" цена: ")[1]));
					data.addFilms(films.selectionModelProperty().getValue().getSelectedItem().split(" цена: ")[0]);
					buy_film_.filmsForYou.removeAll(buy_film_.filmsForYou);
					ok.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("thsnks_sub.fxml"));
					LoaderFXML(loader);
				}
				else {
					noMOney.setVisible(true);
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

	String getPrice (String textForSplit) {
		String price[] = textForSplit.split("- ");
		String pricePerFilm[] = price[1].split(" ");
		return pricePerFilm[0];
	}
}
