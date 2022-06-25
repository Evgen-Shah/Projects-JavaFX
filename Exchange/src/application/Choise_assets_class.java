package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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

public class Choise_assets_class {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField counts;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<String> active;

	@FXML
	private Button choise;

	public static ObservableList<String> actives = FXCollections.observableArrayList();

	public static String price;

	public static String choiseAsset;

	public static String countAssets;

	private String operations;

	Base bs = new Base();
	
	Random ran = new Random();
	int addPrice;
	@FXML
	void initialize() throws SQLException {
		actives = Base.getActives();
		FXMLLoader loader = new FXMLLoader();
		counts.setText("0");

		if (!actives.isEmpty()) {// Если пустой список, то мы не устанавливаем его в box
			active.setItems(Choise_assets_class.actives);
			active.setValue(Choise_assets_class.actives.get(0));
		}

		choise.setOnAction(event -> {// Действие при нажатии кнопки choise
			try {
				long count = Long.parseLong(counts.getText());// Введенное количесвто и проверки
				if (count == 0)
					counts.setText("Количество не может быть равно 0!");
				else if (count < 0)
					counts.setText("Количество не может быть меньше 0!");
				else {
					addPrice = ran.nextInt(20) - 10;
					choiseAsset = active.getSelectionModel().getSelectedItem();
					price = getPrice(active.getSelectionModel().getSelectedItem());
					countAssets = counts.getText();
					if ( Long.parseLong(Base.getBalance()) > Long.parseLong(countAssets) *Long.parseLong(price)) {
						choise.getScene().getWindow().hide();
						actives.removeAll();
						if (Trade_class.byuOrSell) {
							operations = "Long";
						} else {
							operations = "Short";
						}
						bs.updateBalanse(Long.parseLong(Base.getBalance())
								- Long.parseLong(price) * Long.parseLong(countAssets));
						Base.remove();
						bs.openActives(choiseAsset.split(" ")[0], Long.parseLong(countAssets), Long.parseLong(price) + addPrice,
								operations);
						bs.closedActives(choiseAsset.split(" ")[0], Long.parseLong(countAssets), Long.parseLong(price),
								operations);
						loader.setLocation(getClass().getResource("Thanks.fxml"));
						LoaderFXML(loader);
					}
					else {
						counts.setText("Недостаточно средств!");
					}
				}
			} catch (NumberFormatException e) {// Если ошибка NumberFormatException, то пишем, что только числа
				counts.setText("Только числа!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			actives.removeAll(actives);
			Base.remove();
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Trade.fxml"));
			LoaderFXML(loader);
		});
	}

	String getPrice(String textForPrice) {// Метод для получения цены актива
		String priceAndRubl[] = textForPrice.split(" ");
		return priceAndRubl[1];

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
