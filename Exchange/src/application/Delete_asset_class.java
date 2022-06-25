package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
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

public class Delete_asset_class {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> assets;

	@FXML
	private Button back;

	@FXML
	private Button delete;

	public static ObservableList<String> actives = FXCollections.observableArrayList();
	
	Base bs = new Base();
	
	String choiseAsset;
	
	Choise_assets_class ch = new Choise_assets_class();

	@FXML
	void initialize() throws SQLException {
		actives = Base.getActives();
		if (!actives.isEmpty()) {// Если пустой список, то мы не устанавливаем его в box
			assets.setItems(actives);
			assets.setValue(actives.get(0));
		}

		FXMLLoader loader = new FXMLLoader();

		delete.setOnAction(event -> {// Действие при нажатии кнопки admin
			choiseAsset = assets.getSelectionModel().getSelectedItem();
			actives.removeAll(actives);
			Base.remove();
			try {
				bs.addChangeActives(choiseAsset.split(" ")[0]," удален");
				bs.cancel(choiseAsset.split(" ")[0]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			delete.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Delete_asset.fxml"));
			LoaderFXML(loader);
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
