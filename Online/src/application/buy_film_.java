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

public class buy_film_ {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> genre;

	@FXML
	private ChoiceBox<String> rait;

	@FXML
	private ChoiceBox<String> price;

	@FXML
	private Button back;

	@FXML
	private Button ok;

	public static String films = "";

	static ObservableList<String> genreList = FXCollections.observableArrayList("������", "�������", "��������");// ������
																													// ������
	static ObservableList<String> raitList = FXCollections.observableArrayList("5-", "5+");// ������
	static ObservableList<String> priceList = FXCollections.observableArrayList("�� 299", "�� 399");// ���� ������
	static ObservableList<String> filmsForYou = FXCollections.observableArrayList();// ������ �������

	@FXML
	void initialize() {// ����� ��� ������������� ����������� ����
		setBoxex();
		FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// �������� ��� ������� ������ back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("choise_action.fxml"));
			LoaderFXML(loader);
		});

		ok.setOnAction(event -> {// �������� ��� ������� ������ ok
			try {
				filmsForYou = database.getFilms(genre.selectionModelProperty().getValue().getSelectedItem(),
						rait.selectionModelProperty().getValue().getSelectedItem(),
						Long.parseLong(price.selectionModelProperty().getValue().getSelectedItem().split(" ")[1]));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ok.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("films_for_you.fxml"));
			LoaderFXML(loader);
		});
	}

	void setBoxex() {// ����� ��� �������� ������ ������, �������� � �����
		genre.setItems(genreList);
		genre.setValue(genreList.get(0));

		rait.setItems(raitList);
		rait.setValue(raitList.get(0));

		price.setItems(priceList);
		price.setValue(priceList.get(0));
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
