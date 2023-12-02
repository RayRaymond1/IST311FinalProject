package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.ApplicationView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class EmployeeModel {
	// we want to use an observable list so we have listeners when any of our
	// employee objects change.
	;

	private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

	public EmployeeModel() {
		// handle taking whats in the SQL database and adding it to the list
		try (Connection myConn = DriverManager.getConnection(ApplicationView.DB_URL, ApplicationView.DB_USERNAME,
				ApplicationView.DB_PASSWORD);
				PreparedStatement preparedStatement = myConn.prepareStatement("select * from employeeTB");
				ResultSet myRs = preparedStatement.executeQuery();) {

			while (myRs.next()) {
				Employee Employee = new Employee();
				Employee.setId(myRs.getInt("id"));
				Employee.setFirst_name(myRs.getString("first_name"));
				Employee.setLast_name(myRs.getString("last_name"));
				Employee.setStart_date(myRs.getString("start_date"));
				Employee.setStart_salary(myRs.getInt("start_salary"));
				Employee.setEmployee_contract_signed(myRs.getString("employee_contract_signed"));
				Employee.setSocial_security_number(myRs.getString("social_security_number"));
				Employee.setBirth_date(myRs.getString("birth_date"));
				Employee.setPhone_number(myRs.getString("phone_number"));
				Employee.setEmergency_contact(myRs.getString("emergency_contact"));
				Employee.setEmergency_number(myRs.getString("emergency_number"));
				employeeList.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}

	}

	public void addEmployee(Employee employee) {
		employeeList.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employeeList.remove(employee);
		// handle the deletion in the database
	}

	public ObservableList<Employee> getData() {
		return employeeList;
	}
	
	public ObservableList<Employee> updateAndDisplayAllRecords()
	{
		try (Connection myConn = DriverManager.getConnection(ApplicationView.DB_URL, ApplicationView.DB_USERNAME,
				ApplicationView.DB_PASSWORD);
				PreparedStatement preparedStatement = myConn.prepareStatement("select * from employeeTB");
				ResultSet myRs = preparedStatement.executeQuery();) {

			while (myRs.next()) {
				Employee Employee = new Employee();
				Employee.setId(myRs.getInt("id"));
				Employee.setFirst_name(myRs.getString("first_name"));
				Employee.setLast_name(myRs.getString("last_name"));
				Employee.setStart_date(myRs.getString("start_date"));
				Employee.setStart_salary(myRs.getInt("start_salary"));
				Employee.setEmployee_contract_signed(myRs.getString("employee_contract_signed"));
				Employee.setSocial_security_number(myRs.getString("social_security_number"));
				Employee.setBirth_date(myRs.getString("birth_date"));
				Employee.setPhone_number(myRs.getString("phone_number"));
				Employee.setEmergency_contact(myRs.getString("emergency_contact"));
				Employee.setEmergency_number(myRs.getString("emergency_number"));
				employeeList.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return employeeList;
	}

	// there are 4 variants of this  method here to help search with a first name, id, last name, or full name
	public ObservableList<Employee> searchDateWithID(int id) {
		ObservableList<Employee> searchResults = FXCollections.observableArrayList();
		try (Connection myConn = DriverManager.getConnection(ApplicationView.DB_URL, ApplicationView.DB_USERNAME,
				ApplicationView.DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("SELECT * FROM employeeTB WHERE id=" + id);
				ResultSet myRs = preparedStatement.executeQuery();) {

			while (myRs.next()) {
				Employee Employee = new Employee();
				Employee.setId(myRs.getInt("id"));
				Employee.setFirst_name(myRs.getString("first_name"));
				Employee.setLast_name(myRs.getString("last_name"));
				Employee.setStart_date(myRs.getString("start_date"));
				Employee.setStart_salary(myRs.getInt("start_salary"));
				Employee.setEmployee_contract_signed(myRs.getString("employee_contract_signed"));
				Employee.setSocial_security_number(myRs.getString("social_security_number"));
				Employee.setBirth_date(myRs.getString("birth_date"));
				Employee.setPhone_number(myRs.getString("phone_number"));
				Employee.setEmergency_contact(myRs.getString("emergency_contact"));
				Employee.setEmergency_number(myRs.getString("emergency_number"));
				employeeList.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchResults;
	}
	
	public ObservableList<Employee> searchDateWithFirstName(String first_name) {
		ObservableList<Employee> searchResults = FXCollections.observableArrayList();
		try (Connection myConn = DriverManager.getConnection(ApplicationView.DB_URL, ApplicationView.DB_USERNAME,
				ApplicationView.DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("SELECT * FROM employeeTB WHERE first_name='" + first_name + "'");
				ResultSet myRs = preparedStatement.executeQuery();) {

			while (myRs.next()) {
				Employee Employee = new Employee();
				// Employee.setId(resultSet.getInt("id"));
				Employee.setFirst_name(myRs.getString("first_name"));
				Employee.setFirst_name(myRs.getString("last_name"));
				Employee.setStart_date(myRs.getString("start_date"));
				Employee.setStart_salary(myRs.getInt("start_salary"));
				Employee.setEmployee_contract_signed(myRs.getString("employee_contract_signed"));
				Employee.setSocial_security_number(myRs.getString("social_security_number"));
				Employee.setBirth_date(myRs.getString("birth_date"));
				Employee.setPhone_number(myRs.getString("phone_number"));
				Employee.setEmergency_contact(myRs.getString("emergency_contact"));
				Employee.setEmergency_number(myRs.getString("emergency_number"));
				employeeList.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchResults;
	}
	
	public ObservableList<Employee> searchDateWithLastName(String last_name) {
		ObservableList<Employee> searchResults = FXCollections.observableArrayList();
		try (Connection myConn = DriverManager.getConnection(ApplicationView.DB_URL, ApplicationView.DB_USERNAME,
				ApplicationView.DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("SELECT * FROM employeeTB WHERE last_name='" + last_name + "'");
				ResultSet myRs = preparedStatement.executeQuery();) {

			while (myRs.next()) {
				Employee Employee = new Employee();
				// Employee.setId(resultSet.getInt("id"));
				Employee.setFirst_name(myRs.getString("first_name"));
				Employee.setFirst_name(myRs.getString("last_name"));
				Employee.setStart_date(myRs.getString("start_date"));
				Employee.setStart_salary(myRs.getInt("start_salary"));
				Employee.setEmployee_contract_signed(myRs.getString("employee_contract_signed"));
				Employee.setSocial_security_number(myRs.getString("social_security_number"));
				Employee.setBirth_date(myRs.getString("birth_date"));
				Employee.setPhone_number(myRs.getString("phone_number"));
				Employee.setEmergency_contact(myRs.getString("emergency_contact"));
				Employee.setEmergency_number(myRs.getString("emergency_number"));
				employeeList.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchResults;
	}
	
	public ObservableList<Employee> searchDateWithFullName(String first_name, String last_name) {
		ObservableList<Employee> searchResults = FXCollections.observableArrayList();
		try (Connection myConn = DriverManager.getConnection(ApplicationView.DB_URL, ApplicationView.DB_USERNAME,
				ApplicationView.DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("SELECT * FROM employeeTB WHERE first_name='" + first_name + "' AND last_name='" + last_name + "'");
				ResultSet myRs = preparedStatement.executeQuery();) {

			while (myRs.next()) {
				Employee Employee = new Employee();
				// Employee.setId(resultSet.getInt("id"));
				Employee.setFirst_name(myRs.getString("first_name"));
				Employee.setFirst_name(myRs.getString("last_name"));
				Employee.setStart_date(myRs.getString("start_date"));
				Employee.setStart_salary(myRs.getInt("start_salary"));
				Employee.setEmployee_contract_signed(myRs.getString("employee_contract_signed"));
				Employee.setSocial_security_number(myRs.getString("social_security_number"));
				Employee.setBirth_date(myRs.getString("birth_date"));
				Employee.setPhone_number(myRs.getString("phone_number"));
				Employee.setEmergency_contact(myRs.getString("emergency_contact"));
				Employee.setEmergency_number(myRs.getString("emergency_number"));
				employeeList.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchResults;
	}
	

}
