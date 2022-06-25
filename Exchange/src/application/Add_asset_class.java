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
import javafx.stage.Stage;

public class Add_asset_class {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button add;

	@FXML
	private TextField price;

	@FXML
	private TextField name;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox<String> assets;
	
	public static ObservableList<String> actives = FXCollections.observableArrayList();

	Base bs = new Base();

	Choise_assets_class ch = new Choise_assets_class();

	@FXML
	void initialize() throws SQLException {
		actives = Base.getActives();
		if (!actives.isEmpty()) {// Если пустой список, то мы не устанавливаем его в box
			assets.setItems(actives);
			assets.setValue(actives.get(0));
		}

		FXMLLoader loader = new FXMLLoader();

		add.setOnAction(event -> {// Действие при нажатии кнопки add

			try {
				if (name.getText().isEmpty()) {// Если имя пустой, то пишем, что нельзя пустое имя
					name.setText("Не может быть пуcтым!");
				} else {
					long priceLong = Long.parseLong(price.getText());
					bs.addActives(name.getText(), priceLong);
					bs.addChangeActives(name.getText()," добавлен");
					actives.removeAll(actives);
					Base.remove();
					add.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("Add_asset.fxml"));
					LoaderFXML(loader);
				}
			} catch (NumberFormatException e) {
				price.setText("Только числа!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			actives.removeAll(actives);
			Base.remove();
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_admin.fxml"));
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
