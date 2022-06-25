package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.crypto.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class time_control {

	public static boolean addOne = true;

	static ObservableList<String> timesPinks = FXCollections.observableArrayList();
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private TextField time;

	@FXML
	private Text error;
	
	@FXML
	private Text error1;

	@FXML
	private Button choise;

	private LocalDateTime localDateTime;

	BaseControl bs = new BaseControl();

	@FXML
	void initialize() throws SQLException {
		error.setVisible(false);
		error1.setVisible(false);

		FXMLLoader loader = new FXMLLoader();
		choise.setOnAction(event -> {// Использование кнопки для выбора
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
				localDateTime = LocalDateTime.parse(time.getText(), formatter);
			} catch (Exception E) {
				error1.setVisible(false);
				error.setText("Введите дату и время в формате (ДД-ММ-ГГ ЧЧ:ММ)");
				error.setVisible(true);
			}

			LocalDateTime dateTime = LocalDateTime.now();
			System.out.println(dateTime);

			String text = time.getText();
			Pattern pattern = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}");
			Matcher matcher = pattern.matcher(text);
			boolean b = matcher.matches();
			if (localDateTime.isBefore(dateTime)) {
				error.setVisible(false);
				error1.setText("Нельзя ввести дату, которая уже была!");
				error1.setVisible(true);
			} else if (b) {
				try {
					bs.updateData(getTime());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				choise.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("renta_ok.fxml"));
				LoaderFXML(loader);
				addOne = false;
			} else {
				error1.setVisible(false);
				error.setText("Введите дату и время в формате (ДД-ММ-ГГ ЧЧ:ММ)");
				error.setVisible(true);
			}
		});

		back.setOnAction(event -> {// Использование кнопки для выхода назад

			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Renta.fxml"));
			LoaderFXML(loader);
			addOne = false;
		});

	}

	public String getTime() {// ПОлучение времени
		String name = time.getText();
		return name;

	}

	public void LoaderFXML(FXMLLoader loader) {
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