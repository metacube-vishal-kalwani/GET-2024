/**
 * Assignment 7 - Inheritance vs composition
 * @author Vishal Kalwani
 */

package Assigments;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Assigments.Assignment9.Department;
import Assigments.Assignment9.Developer;
import Assigments.Assignment9.Employee;
import Assigments.Assignment9.Intern;
import Assigments.Assignment9.Manager;
import Assigments.Assignment9.Organization;

public class Payroll {
    private static int menuInput = 1;

    /**
     * This functions validates that whether the input given by user is valid or not
     * 
     * @param scanner takes scanner object to scan values from CLI
     * @return returns a valid integer value
     */
    public static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid integer input!!");
                scanner.next();
            }
        }
    }

    /**
     * This functions validates that whether the input given by user is valid or not
     * 
     * @param scanner takes scanner object to scan values from CLI
     * @return returns a valid integer value
     */
    public static float getValidFloatInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("Enter  valid floating input!!");
                scanner.next();
            }
        }
    }

    public static float getValidAmount(Scanner scanner) {
        float amount = 0.0f;
        while (true) {
            try {
                amount = getValidFloatInput(scanner);
                if (amount < 0) {
                    throw new AssertionError("Amount can't be negetive!! Enter again");
                }
                break;
            } catch (AssertionError e) {
                System.out.println(e.getMessage());
                continue;
            }

        }
        return amount;
    }

    /**
     * This functions updates the static menuInput with the value given by user
     * menuInput defines whether we want to use the functionality again or not
     * 
     * @param scanner takes scanner object to scan values from CLI
     */
    public static void updateMenuInput(Scanner scanner) {
        while (true) {
            menuInput = getValidIntegerInput(scanner);
            if (menuInput == 1 || menuInput == 0)
                break;
            else {
                System.out.println("please Enter a valid Input (0 or 1)!!");
            }
        }
    }

    static Department selectDepartment(Organization organization, Scanner scanner) {
        int index = 1;
        System.out.println("Available Departments : ");
        for (Department department : organization.departmentList) {
            System.out.println(index + " " + department.departmentName);
            index++;
        }

        System.out.println("Enter your choice : ");
        int choice;
        while (true) {
            try {
                choice = getValidIntegerInput(scanner);
                if (choice < 1 || choice > organization.departmentList.size()) {
                    throw new AssertionError("Invalid choice!!");
                }
                break;
            } catch (AssertionError e) {
                System.out.println(e.getMessage());
            }
        }
        return organization.departmentList.get(choice - 1);

    }

    static void addEmployee(Department department, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter Name : ");
        String empName = scanner.nextLine();
        System.out.println("Enter base Salary : ");
        float baseSalary = getValidAmount(scanner);
        System.out.println("Enter bonus : ");
        float bonus = getValidAmount(scanner);

        System.out.println("Enter employement type  - "
                + "\n1 Intern"
                + "\n2 Developer"
                + "\n3 Manager");

        while (true) {
            System.out.print("Enter your choice : ");
            int choice = getValidIntegerInput(scanner);
            switch (choice) {
                case 1:
                    Intern newIntern = new Intern(empName, department.departmentName, baseSalary, bonus);
                    department.join(newIntern);
                    break;

                case 2:
                    Developer newDeveloper = new Developer(empName, department.departmentName, baseSalary, bonus);
                    department.join(newDeveloper);
                    break;

                case 3:
                    Manager newManager = new Manager(empName, department.departmentName, baseSalary, bonus);
                    department.join(newManager);
                    break;

                default:
                    System.out.println("Invalid Choice!!");
                    break;
            }

            if (choice < 1 || choice > 3) {
                continue;
            } else {
                break;
            }
        }

    }

    static boolean relieveEmployee(Organization organization, Scanner scanner) {

        Department department = selectDepartment(organization, scanner);
        ArrayList<Employee> employeeList = department.getAllEmployees();
        System.out.print("Enter employee Id : ");
        String empId = scanner.next();

        for (Employee employee : employeeList) {
            if (employee.employeeId.equals(empId) == true) {
                return department.relieve(employee);
            }
        }
        return false;
    }

    static void printAllEmployee(Organization organization) {
        ArrayList<Employee> employeeList = organization.getEmployees();
        System.out.println();
        System.out.println("EID\t" + "Name\t" + "Department");
        for (Employee emp : employeeList) {
            System.out.println(emp.employeeId + "\t" + emp.employeeName + "\t" + emp.departmentName);
        }
    }

    static void printPaySlip(Organization organization, String empId) {

        try {
            ArrayList<Employee> employeeList = organization.getEmployees();
            for (Employee employee : employeeList) {
                if (employee.employeeId.equals(empId) == true) {
                    System.out.println();
                    System.out.println("Employee Name : " + employee.employeeName);
                    System.out.println("salary : " + employee.getBasicSalary());
                    System.out.println("Bonus : " + employee.getBonus());
                    System.out.println("compensation  : " + employee.getCompensation());
                    System.out.println("Total Salary : " + employee.getTotalSalary());
                    System.out.println();
                    return;
                }
            }
            throw new AssertionError("Employee does'nt exist!!");
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }

    }

    static float dispatchSalary(Organization organization, String empId) {
        try {
            ArrayList<Employee> employeeList = organization.getEmployees();
            for (Employee employee : employeeList) {
                if (employee.employeeId.equals(empId) == true) {
                    return employee.getTotalSalary();
                }
            }

            throw new AssertionError("Employee does'nt exist!!");
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Organization organization = new Organization("Meta Cube");
        Department HR = new Department("HR");
        Department IT = new Department("IT");
        Department engineering = new Department("Engineering");

        organization.addDepartment(IT);
        organization.addDepartment(HR);
        organization.addDepartment(engineering);

        while (menuInput == 1) {
            System.out.println();
            System.out.println("----------Welcome to " + organization.organizationName + "------------");
            System.out.println("1 Add a new employee"
                    + "\n2 relieve an employee"
                    + "\n3 print payslip of an employee"
                    + "\n4 dispatch salary"
                    + "\n5 show all employees");
            System.out.print("Enter your choice : ");

            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    try {
                        Department department = selectDepartment(organization, scanner);
                        addEmployee(department, scanner);
                        printAllEmployee(organization);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        if (relieveEmployee(organization, scanner)) {
                            System.out.println("Employee deleted successfully");
                        } else {
                            System.out.println("Employee doesn't exist!!");
                        }
                        // printAllEmployee(organization);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter Employee Id : ");
                        String empId = scanner.next();
                        printPaySlip(organization, empId);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter Employee Id : ");
                        String empId = scanner.next();
                        System.out.println("Salary = " + dispatchSalary(organization, empId));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                case 5:
                    try {
                        printAllEmployee(organization);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid choice!!!");
                    break;
            }

            System.out.println("press 1 to use again and press 0 to exit!!");
            updateMenuInput(scanner);
        }

    }

}
