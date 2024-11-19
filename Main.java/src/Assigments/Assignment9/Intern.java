package Assigments.Assignment9;

public class Intern extends Employee {

    static int internId = 0;

    public Intern(String name, String departmentName, float baseSalary, float bonus) {
        super(name, departmentName, baseSalary, bonus);
        this.employeeId = "Intern" + internId;
        internId++;
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
