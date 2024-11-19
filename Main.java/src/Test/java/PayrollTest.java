package Test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import Assigments.Assignment9.Department;
import Assigments.Assignment9.Developer;
import Assigments.Assignment9.Intern;
import Assigments.Assignment9.Manager;
import Assigments.Assignment9.Organization;

public class PayrollTest {

    Organization organization = new Organization("meta cube");
    Department HR = new Department("HR");
    Department IT = new Department("IT");
    Department admin = new Department("admin");
    Department engineering = new Department("Engineering");

    PayrollTest() {
        organization.addDepartment(HR);
        organization.addDepartment(IT);
        organization.addDepartment(admin);
        organization.addDepartment(engineering);
    }

    private static Stream<Arguments> TestAddEmployeeInput() {
        return Stream.of(
                Arguments.of("Vishal", 15000, 1000, "IT", "Manager"),
                Arguments.of("bhanu", 10, 100, "admin", "Developer"),
                Arguments.of("rahul", 5000, 100, "Engineering", "Intern")

        );
    }

    @ParameterizedTest
    @MethodSource("TestAddEmployeeInput")
    public void TestAddEmployee(String name, float basicSalary, float bonus, String DeptName, String designation) {

        if (designation.equals("Manager")) {
            Manager manager = new Manager(name, DeptName, basicSalary, bonus);
            assertEquals(true, organization.getDepartment(DeptName).join(manager));

        } else if (designation.equals("Developer")) {
            Developer developer = new Developer(name, DeptName, basicSalary, bonus);
            assertEquals(true, organization.getDepartment(DeptName).join(developer));

        } else if (designation.equals("Intern")) {
            Intern intern = new Intern(name, DeptName, basicSalary, bonus);
            assertEquals(true, organization.getDepartment(DeptName).join(intern));
        }
    }

    private static Stream<Arguments> negetiveTestAddEmployeeInput() {
        return Stream.of(
                Arguments.of("", 15000, 1000, "IT", "Manager", "Name can't be blank!!"),
                Arguments.of("vishal", -10, 100, "admin", "Developer", "Amount can't be negetive!!"),
                Arguments.of("rahul", 5000, -100, "Engineering", "Intern", "Amount can't be negetive!!")

        );
    }

    @ParameterizedTest
    @MethodSource("negetiveTestAddEmployeeInput")
    public void negetiveTestAddEmployee(String name, float basicSalary, float bonus, String DeptName,
            String designation, String errorMsg) {

        if (designation.equals("Manager")) {
            AssertionError exp = assertThrows(AssertionError.class, () -> {
                Manager manager = new Manager(name, DeptName, basicSalary, bonus);
            });
            assertEquals(errorMsg, exp.getMessage());

        } else if (designation.equals("Developer")) {
            AssertionError exp = assertThrows(AssertionError.class, () -> {
                Developer developer = new Developer(name, DeptName, basicSalary, bonus);
            });
            assertEquals(errorMsg, exp.getMessage());

        } else if (designation.equals("Intern")) {
            AssertionError exp = assertThrows(AssertionError.class, () -> {
                Intern intern = new Intern(name, DeptName, basicSalary, bonus);
            });
            assertEquals(errorMsg, exp.getMessage());

        }
    }

    private static Stream<Arguments> testRelieveEmpInput() {
        return Stream.of(
                Arguments.of("Manager0", "IT", "Manager", true),
                Arguments.of("Dev0", "admin", "Developer", true),
                Arguments.of("Intern0", "Engineering", "Intern", true));
    }

    @ParameterizedTest
    @MethodSource("testRelieveEmpInput")

    public void testRelieveEmp(String empId, String deptName,
            String designation, boolean expectedResult) {
        Department testDept = organization.getDepartment(deptName);
        if (designation.equals("Manager")) {
            Manager newEmp = new Manager("Test", deptName, 1000, 10);
            testDept.join(newEmp);
            assertEquals(true, testDept.relieve(newEmp));

        } else if (designation.equals("Intern")) {
            Intern newEmp = new Intern("Test", deptName, 1000, 10);
            testDept.join(newEmp);
            assertEquals(true, testDept.relieve(newEmp));

        } else if (designation.equals("Developer")) {
            Developer newEmp = new Developer("Test", deptName, 1000, 10);
            testDept.join(newEmp);
            assertEquals(true, testDept.relieve(newEmp));

        }
    }

    @Test
    public void negativeTestRelieveEmp() {
        String empId = "FE2";
        Department testDept = organization.getDepartment("Engineering");
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            testDept.relieve(testDept.getEmployeeWithId(empId));
        });
        assertEquals("No employee exist with this employee ID!", exception.getMessage());
    }

}
