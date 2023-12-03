package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;
import model.Employee;
import model.EmployeeModel;
import view.View;

public class MainController {
	EmployeeModel model;
	View view;
	ContextMenu contextMenu;
	MenuItem deleteEmployee;
	public MainController() {
		
	}
	
	public MainController(EmployeeModel model, View view) {
		this.model = model;
		this.view = view;
		
		view.getTableView().setItems(model.getData());
		
		//setting the add button in the create tab
		view.getCreateTab().getAddButton().setOnAction(e -> {
			model.addEmployee(view.getCreateTab().createEmployeeFromFields());});
		
		//setting the retrieve all button in the retrieve tab
		view.getRetrieveTab().getRetrieveAllButton().setOnAction(e -> {
			view.getTableView().setItems(model.updateAndDisplayAllRecords());
			view.getRetrieveTab().getTableView().setItems(view.getTableView().getItems());
		});
		
		view.getRetrieveTab().getRetrieveOneButton().setOnAction(e -> {
			if (!view.getRetrieveTab().getID().getText().isBlank()) {
				
				System.out.println("ID");
				view.getTableView().setItems(model.searchDateWithID(Integer.parseInt(view.getRetrieveTab().getID().getText())));
				
			} else if (!view.getRetrieveTab().getFirst_name().getText().isBlank() & !view.getRetrieveTab().getLast_name().getText().isBlank()) {
				
				System.out.println("Full Name");
				view.getTableView().setItems(model.searchDateWithFullName(view.getRetrieveTab().getFirst_name().getText(),view.getRetrieveTab().getLast_name().getText()));
				
			} else if (!view.getRetrieveTab().getFirst_name().getText().isBlank() & view.getRetrieveTab().getLast_name().getText().isBlank()) {
				
				System.out.println("First Name");
				view.getTableView().setItems(model.searchDateWithFirstName(view.getRetrieveTab().getFirst_name().getText()));

			} else if (view.getRetrieveTab().getFirst_name().getText().isBlank() & !view.getRetrieveTab().getLast_name().getText().isBlank()) {
				
				System.out.println("Last Name");
				view.getTableView().setItems(model.searchDateWithLastName(view.getRetrieveTab().getLast_name().getText()));
			}
		});
		
		//creating a right click menu where you can delete employees
		contextMenu = new ContextMenu();
		deleteEmployee = new MenuItem("Delete Employee");
		deleteEmployee.setOnAction(e -> {
			model.removeEmployee(view.getTableView().getSelectionModel().getSelectedItem());
		});
		contextMenu.getItems().add(deleteEmployee);
		
		//setting that right click menu to each row of the table.
		//this whole chunk of code takes how we display tables, and adds a listener to wait for a right click
		//and then open our right click menu where our mouse is.
        view.getTableView().setRowFactory(tableView -> {
            final TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == javafx.scene.input.MouseButton.SECONDARY) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });
       
       //setting each colummn's listener to update the employee object associated and the database entry. 
        view.getTableView().getColumns().get(0).setOnEditCommit(e -> {
        	int oldEmployeeId = (int) e.getOldValue();
        	model.updateEmployee(oldEmployeeId, (Employee) e.getRowValue());
        });
        
        view.getTableView().getColumns().get(1).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setFirst_name((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(2).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setLast_name((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(3).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setStart_date((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(4).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setStart_salary((int) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(5).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setEmployee_contract_signed((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(6).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setSocial_security_number((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(7).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setBirth_date((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(8).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setPhone_number((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(9).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setEmergency_contact((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
        
        view.getTableView().getColumns().get(10).setOnEditCommit(e -> {
        	Employee oldEmployee = (Employee) e.getRowValue();
        	oldEmployee.setEmergency_number((String) e.getNewValue());
        	model.updateEmployee(oldEmployee.getId(), oldEmployee);
        });
		
	}
	
	
}
