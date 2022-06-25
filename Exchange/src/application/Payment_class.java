package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Payment_class {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private PasswordField pass;

	@FXML
	private Button back;

	@FXML
	private Button pay;

	@FXML
	private Text price;

	@FXML
	private TextField prices;

	@FXML
	private Text balance;

	@FXML
	private Text noMoney;

	@FXML
	private TextField cardNumber;
	
	Base bs = new Base();

	History_class h = new History_class();
	My_assets_class m = new My_assets_class();

	Random ran = new Random();

	Pattern pattern = Pattern.compile("[0-9]{16}");

	String numberCard = "1111222233334444";
	String password = "1234";
	static long countMoney = 200000;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'в' HH:mm:ss z");
	Date date = new Date(System.currentTimeMillis());

	@FXML
	void initialize() throws SQLException {
		balance.setText("Баланс: " + Base.getBalance());
		FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_users.fxml"));
			LoaderFXML(loader);
		});

		pay.setOnAction(event -> {// Дейсвтие при нажатии кнопки pay
			Matcher matcher = pattern.matcher(cardNumber.getText());
			if (matcher.matches() && pass.getText().equals(password)) { // Проверка введенных данных для оплаты
				try {
					bs.updateBalanse(Long.parseLong(Base.getBalance()) + Long.parseLong(prices.getText()));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pay.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("Thanks.fxml"));
				LoaderFXML(loader);
			}

			else {
				cardNumber.setText("Неверный данные!");
				pass.setText("Неверный данные!");
			}
		});
	}

	public void LoaderFXML(FXMLLoader loader) {
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
