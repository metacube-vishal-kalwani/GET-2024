package Assigments.Assignment9;

import java.util.ArrayList;

/**
 * 
 * Organization is the top most class in the development of a payroll system of
 * an organization
 * It maintains list of all the departments and employees in an organization
 * 
 */
public class Organization {
    public ArrayList<Department> departmentList;
    public String organizationName;

    public Organization(String orgName) {
        this.organizationName = orgName;
        this.departmentList = new ArrayList<>();
    }

    /**
     * This methods adds a new deparment to the organization
     * 
     * @param department takes a department object
     * @return returns true if department is successfully added either returns false
     */
    public boolean addDepartment(Department department) {

        for (Department dept : departmentList) {
            if (dept.departmentName == department.departmentName) {
                throw new AssertionError("Department Already Exist!!");
            }
        }
        departmentList.add(department);
        return true;
    }

    /**
     * This method returns List of all the employees in an organization
     * 
     * @return return ArrrayList of type Employee
     * 
     */
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> totalEmployees = new ArrayList<>();
        for (Department department : departmentList) {
            totalEmployees.addAll(department.employeeList);
        }

        return totalEmployees;
    }

    /**
     * This functions finds department with given department name and returns it's
     * object
     * 
     * @param DepartmentName takes department name from user as a String
     * @return returns department object
     */
    public Department getDepartment(String DepartmentName) {

        for (Department department : this.departmentList) {
            if (department.departmentName.equals(DepartmentName)) {
                return department;
            }
        }
        return null;
    }

}
