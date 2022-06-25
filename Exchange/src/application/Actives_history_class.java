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
import javafx.stage.Stage;

public class Actives_history_class {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> history;

    public static ObservableList<String> actives = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException {
    	FXMLLoader loader = new FXMLLoader();
    	
    	actives = Base.getHistoryAddDelete();
		if (!actives.isEmpty()) {// Если пустой список, то мы не устанавливаем его в box
			history.setItems(actives);
			history.setValue(actives.get(0));
		}
				
		back.setOnAction(event -> {// Действие при нажатии кнопки back
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
