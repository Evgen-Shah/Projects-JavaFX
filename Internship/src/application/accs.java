package application;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class accs {
	public static ObservableList<String> numberPersonsAndPass = FXCollections.observableArrayList();
	public static ObservableList<String> statementAndNumber = FXCollections.observableArrayList();
	public static ObservableList<String> statementPhoneAndStatus = FXCollections.observableArrayList();

	public static void AddNumberAndPass(String number) {
		numberPersonsAndPass.add(number);
	}

	public void AddNumberAndStatement (String number) {
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
				statement.add(statementAndNumber.get(i).split(":")[1]);
		}
		return statement;
	}

	public ObservableList<String> DeleteStatements(String number, String removeItem) {
		for (int i = 0; i < statementAndNumber.size(); i++) {
			if(statementAndNumber.get(i).split(":")[0].equals(number) && statementAndNumber.get(i).split(":")[1].equals(removeItem)) {
				statementAndNumber.remove(statementAndNumber.get(i));
			}
		}
		return statementAndNumber;
	}

	public void SetStatus(String info, String status) {
		String state[] = info.split("cтажировка: ");// 1 - название стажировка
		String numberPhone[] = state[0].split("номер телефона: ");// 1 - номер телефона
		statementPhoneAndStatus.add(state[1] + " : " + numberPhone[1] + " : " + status);
		System.out.println(statementPhoneAndStatus);
	}

}
