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

public class LK_ {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button auth;

    @FXML
    private Button reg;

    @FXML
    void initialize() {// ����� ��� ������������� ����������� ���� 
     	FXMLLoader loader = new FXMLLoader();
     	
    	auth.setOnAction(event -> {// �������� ��� ������� ������ auth
    		auth.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Autorization.fxml"));
			LoaderFXML(loader);
		});
    	
    	reg.setOnAction(event -> {// �������� ��� ������� ������ reg
    		reg.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("registration.fxml"));
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
