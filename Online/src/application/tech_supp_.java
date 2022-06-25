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

public class tech_supp_ {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> appeal;

	@FXML
	private Button ok;

	@FXML
	private Button back;
	
    @FXML
    private Text textSupp;

	static ObservableList<String> appealList = FXCollections.observableArrayList();

	@FXML
	void initialize() throws SQLException {// Метод при инициализации визуального окна 
		textSupp.setText("");
		appealList = database.getTheme();
		appeal.setItems(appealList);
		appeal.setValue(appealList.get(0));

		FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			appealList.removeAll(appealList);
			database.theme.removeAll(database.theme);
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("choise_action.fxml"));
			LoaderFXML(loader);
		});
		
		ok.setOnAction(event -> {// Дейсвтие при нажатии кнопки ok
			try {
				textSupp.setText(database.getAns(appeal.getSelectionModel().getSelectedItem()));
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
