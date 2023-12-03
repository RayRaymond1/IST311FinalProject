package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class EmployeeModel {
	
	public final static String DB_URL = "jdbc:mysql://localhost:3306/projectdb";
	public final static String DB_USERNAME = "root";
	public final static String DB_PASSWORD = "admin";
	

	private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

	public EmployeeModel() {
		// handle taking whats in the SQL database and adding it to the list
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement preparedStatement = myConn.prepareStatement("SELECT * FROM employeetb WHERE id BETWEEN 1 AND 4");
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
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("INSERT INTO employeeTb(first_name, last_name, start_date, start_salary, "
								+ "employee_contract_signed, social_security_number, birth_date, phone_number, emergency_contact, emergency_number) "
								+ "VALUES (?,?,?,?,?,?,?,?,?,?)");) {

			preparedStatement.setString(1, employee.getFirst_name());
			preparedStatement.setString(2, employee.getLast_name());
			preparedStatement.setString(3, employee.getStart_date());
			preparedStatement.setInt(4, employee.getStart_salary());
			preparedStatement.setString(5, employee.getEmployee_contract_signed());
			preparedStatement.setString(6, employee.getSocial_security_number());
			preparedStatement.setString(7, employee.getBirth_date());
			preparedStatement.setString(8, employee.getPhone_number());
			preparedStatement.setString(9, employee.getEmergency_contact());
			preparedStatement.setString(10, employee.getEmergency_number());
			preparedStatement.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	public void removeEmployee(Employee employee) {
		employeeList.remove(employee);
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("DELETE FROM employeetb WHERE id= ? AND first_name = ? AND last_name = ? AND start_date = ? AND start_salary = ? "
					            + "AND employee_contract_signed = ? AND social_security_number = ? AND birth_date = ? "
					            + "AND phone_number = ? AND emergency_contact = ? AND emergency_number = ?");) {

			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getFirst_name());
			preparedStatement.setString(3, employee.getLast_name());
			preparedStatement.setString(4, employee.getStart_date());
			preparedStatement.setInt(5, employee.getStart_salary());
			preparedStatement.setString(6, employee.getEmployee_contract_signed());
			preparedStatement.setString(7, employee.getSocial_security_number());
			preparedStatement.setString(8, employee.getBirth_date());
			preparedStatement.setString(9, employee.getPhone_number());
			preparedStatement.setString(10, employee.getEmergency_contact());
			preparedStatement.setString(11, employee.getEmergency_number());
			preparedStatement.executeUpdate();
			
			employeeList.remove(employee);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void updateEmployee(int oldEmployeeId, Employee updatedEmployee) {
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("UPDATE employeetb SET id= ?, first_name = ?, last_name = ?, start_date = ?, start_salary = ?, "
					            + "employee_contract_signed = ?, social_security_number = ?, birth_date = ?, "
					            + "phone_number = ?, emergency_contact = ?, emergency_number = ? WHERE id=?");) {

			preparedStatement.setInt(1, updatedEmployee.getId());
			preparedStatement.setString(2, updatedEmployee.getFirst_name());
			preparedStatement.setString(3, updatedEmployee.getLast_name());
			preparedStatement.setString(4, updatedEmployee.getStart_date());
			preparedStatement.setInt(5, updatedEmployee.getStart_salary());
			preparedStatement.setString(6, updatedEmployee.getEmployee_contract_signed());
			preparedStatement.setString(7, updatedEmployee.getSocial_security_number());
			preparedStatement.setString(8, updatedEmployee.getBirth_date());
			preparedStatement.setString(9, updatedEmployee.getPhone_number());
			preparedStatement.setString(10, updatedEmployee.getEmergency_contact());
			preparedStatement.setString(11, updatedEmployee.getEmergency_number());
			preparedStatement.setInt(12, oldEmployeeId);
			
			System.out.println(oldEmployeeId);
			System.out.println("SQL Statement: " + preparedStatement.toString());

			preparedStatement.executeUpdate();
			
			
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public ObservableList<Employee> getData() {
		return employeeList;
	}
	
	public ObservableList<Employee> updateAndDisplayAllRecords()
	{
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
				PreparedStatement preparedStatement = myConn.prepareStatement("SELECT * FROM employeetb");
				ResultSet myRs = preparedStatement.executeQuery();) {

			employeeList.clear();
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
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
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
				searchResults.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchResults;
	}
	
	public ObservableList<Employee> searchDateWithFirstName(String first_name) {
		ObservableList<Employee> searchResults = FXCollections.observableArrayList();
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("SELECT * FROM employeeTB WHERE first_name='" + first_name + "'");
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
				searchResults.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchResults;
	}
	
	public ObservableList<Employee> searchDateWithLastName(String last_name) {
		ObservableList<Employee> searchResults = FXCollections.observableArrayList();
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("SELECT * FROM employeeTB WHERE last_name='" + last_name + "'");
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
				searchResults.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchResults;
	}
	
	public ObservableList<Employee> searchDateWithFullName(String first_name, String last_name) {
		ObservableList<Employee> searchResults = FXCollections.observableArrayList();
		try (Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
				PreparedStatement preparedStatement = myConn
						.prepareStatement("SELECT * FROM employeeTB WHERE first_name='" + first_name + "' AND last_name='" + last_name + "'");
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
				searchResults.add(Employee);
			}

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchResults;
	}
	

}
