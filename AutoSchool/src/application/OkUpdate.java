package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OkUpdate {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Commet;

    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();
    	Commet.setOnAction(event -> {// Использование кнопки для входа инструктором
    		Commet.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("StyduMainWindow.fxml"));

			Loader(loader);
		});
    }
    
    public void Loader(FXMLLoader loader) {
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
