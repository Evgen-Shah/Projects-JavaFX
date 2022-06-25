package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSaver {
	public static ObservableList<String> numberPersonsAndPass = FXCollections.observableArrayList();
	public static ObservableList<String> statementAndNumber = FXCollections.observableArrayList();
	public static ObservableList<String> statementPhoneAndStatus = FXCollections.observableArrayList();
	public static ObservableList<String> infoUsers = FXCollections.observableArrayList();

	public static void AddNumberAndPass(String number) {
		numberPersonsAndPass.add(number);
	}

	public void AddNumberTrainAndTime (String number) {
		statementAndNumber.add(number);
	}

	public boolean CheckPass (String pass) {
		for (int i = 0; i < numberPersonsAndPass.size(); i++) {
			if(numberPersonsAndPass.get(i).split(":")[1].equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public boolean CheckPhone (String pass) {
		for (int i = 0; i < numberPersonsAndPass.size(); i++) {
			if(numberPersonsAndPass.get(i).split(":")[0].equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public ObservableList<String> GetStatements(String number) {
		ObservableList<String> statement = FXCollections.observableArrayList();
		for (int i = 0; i < statementAndNumber.size(); i++) {
			if(statementAndNumber.get(i).split(":")[0].equals(number))
				statement.add(statementAndNumber.get(i).split(":")[1] + " в " + statementAndNumber.get(i).split(":")[2]+ ":" + statementAndNumber.get(i).split(":")[3] + " дата: " + statementAndNumber.get(i).split(":")[4]);
		}
		return statement;
	}

	public ObservableList<String> DeleteStatements(String number, String removeItem) {
		for (int i = 0; i < statementAndNumber.size(); i++) {
			System.out.println(statementAndNumber.get(i).split(":")[1]);
			if(statementAndNumber.get(i).split(":")[0].equals(number) && (statementAndNumber.get(i).split(":")[1] + " в " + statementAndNumber.get(i).split(":")[2]+ ":" + statementAndNumber.get(i).split(":")[3] + " дата: " + statementAndNumber.get(i).split(":")[4]).equals(removeItem)) {
				statementAndNumber.remove(statementAndNumber.get(i));
			}
		}
		return statementAndNumber;
	}

	public static void AddUsers (String number) {
		infoUsers.add(number);
	}
	
	public static ObservableList<String> GetInfoUsers (String number) {
		ObservableList<String> statement = FXCollections.observableArrayList();
		for (int i = 0; i < infoUsers.size(); i++) {
			if(infoUsers.get(i).split(":")[0].equals(number)) {
				statement.add(infoUsers.get(i));
			}
		}
		return statement;
	}
}
