package Assigments.Assignment9;

public class Manager extends Employee {

    static final float BASIC_SALARY = 20000.00f;
    static final float BONUS = 3000f;
    static int managerId = 0;

    public Manager(String name, String departmentName, float baseSalary, float bonus) {
        super(name, departmentName, baseSalary, bonus);
        this.employeeId = "Manager" + managerId;
        managerId++;

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
