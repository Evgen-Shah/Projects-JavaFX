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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class buy_subscription_ {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> choise_term;

	@FXML
	private Button back;

	@FXML
	private Text noMOney;

	@FXML
	private Button buy;

	public static long price;

	database data = new database();

	String choise = "";

	static ObservableList<String> terms = FXCollections.observableArrayList();

	@FXML
	void initialize() throws SQLException {// Метод при инициализации визуального окна
		terms = database.getSub();
		noMOney.setVisible(false);

		choise_term.setItems(terms);
		choise_term.setValue(terms.get(0));

		FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			terms.removeAll(terms);
			database.subs.removeAll(database.subs);
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("choise_action.fxml"));
			LoaderFXML(loader);
		});

		buy.setOnAction(event -> {// Дейсвтие при нажатии кнопки buy
			choise = choise_term.getSelectionModel().getSelectedItem();
			System.out.println(choise);
			try {
				if (Long.parseLong(database.getMoney()) >= Long.parseLong(choise.split(" ")[2])) {
					try {
						data.addCard(Long.parseLong(database.getMoney()),"Покупка подписки за " + Long.parseLong(choise.split(" ")[2]));
						data.updateSub(Long.toString(
								(Long.parseLong(database.getUsersSub()) + Long.parseLong(choise.split(" ")[0]))));
						terms.removeAll(terms);
						database.subs.removeAll(database.subs);
						data.updateMoney(Long.parseLong(database.getMoney()) - Long.parseLong(choise.split(" ")[2]));
						buy.getScene().getWindow().hide();
						loader.setLocation(getClass().getResource("thsnks_sub.fxml"));
						LoaderFXML(loader);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					noMOney.setVisible(true);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
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
}
