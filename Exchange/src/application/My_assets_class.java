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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class My_assets_class {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private TextField counts;

	@FXML
	private ChoiceBox<String> positions;

	@FXML
	private Button close;

	@FXML
	private Text balance;

	int newPrices;

	Random ran = new Random();

	Base bs = new Base();

	String choisePos = " ";
	public static ObservableList<String> operations = FXCollections.observableArrayList();

	@FXML
	void initialize() throws SQLException {
		balance.setText("Баланс: " + Base.getBalance());// Устанавливаем баланс
		FXMLLoader loader = new FXMLLoader();
		operations = Base.getActivesOpen();

		if (!operations.isEmpty()) {// Проверка на пустой лист
			positions.setItems(operations);
			positions.setValue(operations.get(0));
		}

		close.setOnAction(event -> {// Дейсвтие при нажатии кнопки close
			try {
				choisePos = positions.getSelectionModel().getSelectedItem();
				Base.removeOpen();
				int priceAdde = ran.nextInt(20) - 10;
				operations.removeAll(operations);
				Base.removeOpen();
				if (Long.parseLong(counts.getText()) <= 0) {// Проверка на отрицательный ввод
					counts.setText("Не может быть отрицательным!");
				}

				if (Long.parseLong(counts.getText()) >= Long.parseLong(choisePos.split(" ")[3])) {
					bs.updateBalanse(Long.parseLong(Base.getBalance())
							+ Long.parseLong(choisePos.split(" ")[5]) * Long.parseLong(choisePos.split(" ")[3]));

					bs.deletePos(choisePos.split(" ")[1]);

					bs.closedActives(choisePos.split(" ")[1], Long.parseLong(choisePos.split(" ")[3]),
							Long.parseLong(choisePos.split(" ")[5]), choisePos.split(" ")[7] + " ЗАКРЫТО");
				} else {
					bs.updateBalanse(Long.parseLong(Base.getBalance())
							+ Long.parseLong(choisePos.split(" ")[5]) * Long.parseLong(counts.getText()));

					bs.deletePos(choisePos.split(" ")[1]);

					bs.openActives(choisePos.split(" ")[1],
							Long.parseLong(choisePos.split(" ")[3]) - Long.parseLong(counts.getText()),
							Long.parseLong(choisePos.split(" ")[5]) + priceAdde, choisePos.split(" ")[7]);

					bs.closedActives(choisePos.split(" ")[1], Long.parseLong(counts.getText()),
							Long.parseLong(choisePos.split(" ")[5]), choisePos.split(" ")[7] + " ЗАКРЫТО");
				}

				close.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("My_assets.fxml"));
				LoaderFXML(loader);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				counts.setText("Только положительные числа!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			operations.removeAll(operations);
			Base.removeOpen();
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_users.fxml"));
			LoaderFXML(loader);
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
