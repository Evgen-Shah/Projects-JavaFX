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

public class History_class {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> operations;

    @FXML
    private Button back;
    
    Base bs = new Base();

	public static ObservableList<String> history = FXCollections.observableArrayList();
    
    @FXML
    void initialize() throws SQLException {
    	FXMLLoader loader = new FXMLLoader();
    	history = Base.getActivesClosed();
    	if(!history.isEmpty()) {// Проверка на пустой лист
    	operations.setItems(history);
    	operations.setValue(history.get(0));
    	}
		back.setOnAction(event -> {// Действие при нажатии кнопки back
			history.removeAll(history);
			Base.removeClosed();
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
