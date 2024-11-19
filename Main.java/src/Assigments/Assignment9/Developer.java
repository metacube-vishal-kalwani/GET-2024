package Assigments.Assignment9;

/**
 * This class developer is a child class of employee and have some specific
 * properties
 */
public class Developer extends Employee {

    static int devId = 0;

    public Developer(String name, String departmentName, float baseSalary, float bonus) {
        super(name, departmentName, baseSalary, bonus);
        this.employeeId = "Dev" + devId;
        devId++;

    }

    public float getBasicSalary() {
        return baseSalary;
    }

    public float getBonus() {
        return bonus;
    }

    public float getCompensation() {
        return bonus + baseSalary;
    }

    public float getTax() {
        return (baseSalary + bonus) * 0.2f;
    }
}
