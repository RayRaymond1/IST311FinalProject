package application;

public class Employee {

	private int id;
	private String first_name;
	private String last_name;
	private String start_date;
	private int start_salary;
	private String employee_contract_signed;
	private String social_security_number;
	private String birth_date;
	private String phone_number;
	private String emergency_contact;
	private String emergency_number;
	
	public Employee()
	{
		this.id = 0;
		this.first_name = "";
		this.last_name = "";
		this.start_date = "";
		this.start_salary = 0;
		this.employee_contract_signed = "";
		this.social_security_number = "";
		this.birth_date = "";
		this.phone_number = "";
		this.emergency_contact = "";
		this.emergency_number = "";
	}
	
	public Employee(int id, String first_name, String last_name,
			String start_date, int start_salary, String employee_contract_signed, 
			String social_security_number, String birth_date, String phone_number, 
			String emergency_contact, String emergency_number)
	{
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.start_date = start_date;
		this.start_salary = start_salary;
		this.employee_contract_signed = employee_contract_signed;
		this.social_security_number = social_security_number;
		this.birth_date = birth_date;
		this.phone_number = phone_number;
		this.emergency_contact = emergency_contact;
		this.emergency_number = emergency_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public int getStart_salary() {
		return start_salary;
	}

	public void setStart_salary(int start_salary) {
		this.start_salary = start_salary;
	}

	public String getEmployee_contract_signed() {
		return employee_contract_signed;
	}

	public void setEmployee_contract_signed(String employee_contract_signed) {
		this.employee_contract_signed = employee_contract_signed;
	}

	public String getSocial_security_number() {
		return social_security_number;
	}

	public void setSocial_security_number(String social_security_number) {
		this.social_security_number = social_security_number;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmergency_contact() {
		return emergency_contact;
	}

	public void setEmergency_contact(String emergency_contact) {
		this.emergency_contact = emergency_contact;
	}

	public String getEmergency_number() {
		return emergency_number;
	}

	public void setEmergency_number(String emergency_number) {
		this.emergency_number = emergency_number;
	}
}
