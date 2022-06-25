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

public class choise_action_ {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button subscription;

    @FXML
    private Button buy_film;
    
    @FXML
    private Button lk;

    @FXML
    private Button stuff;
    
    @FXML
    private Button back;

    @FXML
    void initialize() {// ����� ��� ������������� ����������� ���� 
    	FXMLLoader loader = new FXMLLoader();
     	
    	subscription.setOnAction(event -> {// �������� ��� ������� ������ subscription
    		subscription.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("buy_subscription.fxml"));
			LoaderFXML(loader);
		});
    	
    	buy_film.setOnAction(event -> {// �������� ��� ������� ������ buy_film
    		buy_film.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("buy_film.fxml"));
			LoaderFXML(loader);
		});
    	
    	stuff.setOnAction(event -> {// �������� ��� ������� ������ stuff
    		stuff.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("tech_supp.fxml"));
			LoaderFXML(loader);
		});
    	
    	lk.setOnAction(event -> {// �������� ��� ������� ������ stuff
    		lk.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Office.fxml"));
			LoaderFXML(loader);
		});
    	
    	
    	back.setOnAction(event -> {// �������� ��� ������� ������ back
    		back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("LK.fxml"));
			LoaderFXML(loader);
		});
    }
    
	public void LoaderFXML(FXMLLoader loader) {// ����� ��� ��������� ����
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
