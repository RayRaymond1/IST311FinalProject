package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Employee;
import Model.EmployeeModel;
import application.ApplicationView;

public class MainController {
	
	public void addEmployee(Employee employee) {
		{

			//SQL Database Connection Parameters
			
;
			
			try( Connection myConn = DriverManager.getConnection(ApplicationView .DB_URL, ApplicationView.DB_USERNAME, ApplicationView.DB_PASSWORD);
				PreparedStatement preparedStatement = myConn.prepareStatement("insert into employeeTb(first_name, last_name, start_date, start_salary, "
						+ "employee_contract_signed, social_security_number, birth_date, phone_number, emergency_contact, emergency_number) "
						+ "values (?,?,?,?,?,?,?,?,?,?)");) {
				//1. Get a Connection
				System.out.println("Database Connection successful! \n");
					
				//3. Execute SQL query
	
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
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}
			

		}
		
	}
	
}
