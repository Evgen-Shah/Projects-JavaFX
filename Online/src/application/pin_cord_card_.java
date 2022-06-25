package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class pin_cord_card_ {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField money;

	@FXML
	private Text price;

	@FXML
	private TextField card;

	@FXML
	private TextField pinCode;

	@FXML
	private Button buy;

	@FXML
	private Button back;

	String pinMy = "1111";

	long moneyOnCard = 900;

	public static long moneys = 800;

	Pattern pattern = Pattern.compile("[0-9]{16}");

	buy_subscription_ bs = new buy_subscription_();
	films_for_you_ ffy = new films_for_you_();

	database base = new database();

	@FXML
	void initialize() throws SQLException {// Метод при инициализации визуального окна

		FXMLLoader loader = new FXMLLoader();
		if (database.getNumberCard().equals("")) {
			card.setText("");
		}
		else {
			card.setText(database.getNumberCard());
		}
		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("choise_action.fxml"));
			LoaderFXML(loader);
		});

		buy.setOnAction(event -> {// Дейсвтие при нажатии кнопки buy
			try {
				Matcher matcher = pattern.matcher(card.getText());
				if (!matcher.matches())// Проверяем вводимые данные в поле с номером карты
				{
					card.setText("Неверный формат карты!");
				} else if ((card.getText().equals("") || card.getText().equals("Номер карты не может быть пустым!"))) {
					card.setText("Номер карты не может быть пустым!");
				} else if (pinCode.getText().equals("") || pinCode.getText().equals("Пинк код не может быть пустым!")) {
					pinCode.setText("Пин-код не может быть пустым!");
				} else if (!pinCode.getText().equals(pinMy)) {
					pinCode.setText("Неверный пин-код!");
				} else {
					base.addCard(Long.parseLong(database.getMoney()),"Пополнение на " + money.getText());
					base.updateMoney(Long.parseLong(database.getMoney()) + Long.parseLong(money.getText()));
					buy.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("Office.fxml"));
					LoaderFXML(loader);
				}

			} catch (NumberFormatException e) {
				card.setText("Только цифры!");
				pinCode.setText("Только цифры!");
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
