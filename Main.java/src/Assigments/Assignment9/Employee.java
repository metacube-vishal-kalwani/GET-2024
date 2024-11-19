package Assigments.Assignment9;

/**
 * This is an abstract class Employee which will be parent class of various
 * types of employees
 * this method contains various employee's info such as
 * name of employee = employeeName
 * department of employee = departmentName
 * base salary
 * bonus
 * Methods -
 * getBasicSalary() - returns the salary of an employee
 * getBonus() - returns the bonus of an employee
 * getCompensation() - returns the sum of base + bonus
 * getTotalSalary() - returns the total salary after taxation
 */
public abstract class Employee {
    public String employeeId;
    public String employeeName;
    public String departmentName;
    public float baseSalary;
    public float bonus;
    static final float Tax = 0.3f;

    /**
     * 
     * @return returns the employeeName
     */
    String getEmployeeName() {
        return employeeName;
    }

    /**
     * Initializes an employee with provided name , department name, base salary and
     * bonus of the employee
     * 
     * @param name
     * @param departmentName
     * @param baseSalary
     * @param bonus
     */
    Employee(String name, String departmentName, float baseSalary, float bonus) {
        if (name == "") {
            throw new AssertionError("Name can't be blank!!");
        }
        if (baseSalary < 0 || bonus < 0) {
            throw new AssertionError("Amount can't be negetive!!");
        }
        if (departmentName == "") {
            throw new AssertionError("department name can't be blank!!");

        }
        this.baseSalary = baseSalary;
        this.employeeName = name;
        this.bonus = bonus;
        this.departmentName = departmentName;
    }

    /**
     * abstract method to return the base salary of an employee
     * 
     * @return
     */
    public abstract float getBasicSalary();

    /**
     * abstract method to return the bonus of an employee
     * 
     * @return
     */
    public abstract float getBonus();

    /**
     * abstract method to return the compensation of an employee
     * 
     * @return
     */
    public abstract float getCompensation();

    /**
     * Abstract method to return the total salary after tax reduction of an employee
     * 
     * @return returns calculated salary
     */
    public float getTotalSalary() {
        return this.getCompensation() - this.getCompensation() * Tax;
    }
}
