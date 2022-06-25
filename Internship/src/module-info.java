module Internship {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires ojdbc8;
	
	opens application to javafx.graphics, javafx.fxml;
}
