package Assigments.Assignment9;

import java.util.ArrayList;

/**
 * This class Department contains the List of all employees belongs to a
 * perticular department
 */
public class Department {
    public String departmentName;
    public ArrayList<Employee> employeeList;

    /**
     * paramerized constructor to create a new department
     * 
     * @param departmentName takes the department name from user to initialize a
     *                       department
     */
    public Department(String departmentName) {
        this.departmentName = departmentName;
        employeeList = new ArrayList<>();

    }

    /**
     * This function adds a new employee to this department
     * 
     * @param userInput takes employee object a params
     * @return return true is employee is successfully added
     */
    public boolean join(Employee userInput) {
        employeeList.add(userInput);
        return true;
    }

    /**
     * This function removes a new employee to this department
     * 
     * @param userInput takes employee object a params
     * @return return true is employee is successfully removed
     */
    public boolean relieve(Employee userInput) {
        for (Employee employee : employeeList) {
            if (employee.getEmployeeName() == userInput.getEmployeeName()) {
                employeeList.remove(employee);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Employee> getAllEmployees() {
        return this.employeeList;
    }

    /**
     * Returns the employee from department
     * 
     * @param empId takes employee's Id from user
     * @return returns object of employee with same employee Id
     */

    public Employee getEmployeeWithId(String empId) {
        for (Employee employee : employeeList) {
            if (employee.employeeName.equals(empId)) {
                return employee;
            }
        }

        throw new AssertionError("No employee exist with this employee ID!");
    }

}
