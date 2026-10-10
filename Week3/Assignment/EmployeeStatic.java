// M5 Assignment - Instance vs Static: Splitting an Employee Class Correctly
// Renamed to EmployeeStatic to avoid conflict with Employee.java in the same folder.
// companyName is static — one copy shared by all employees.
// empName and salary are instance — each employee object has its own copy.
// printCompanyInfo() is static — it can only access static fields, not empName or salary.

public class EmployeeStatic {

    // Instance fields — different for every employee
    String empName;
    double salary;

    // Static field — shared across ALL EmployeeStatic objects
    static String companyName = "Bright Horizon Technologies";

    // Static counter — goes up each time a new object is created
    static int employeeCount = 0;

    public EmployeeStatic(String empName, double salary) {
        this.empName = empName;
        this.salary  = salary;
        employeeCount++;
    }

    // Static method — uses only static fields
    // Trying to print empName here would cause a compile error
    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        new EmployeeStatic("Rohan",  55000);
        new EmployeeStatic("Sneha",  62000);
        new EmployeeStatic("Vikram", 48000);

        // Called on the CLASS, not on any object — this is the correct style for static methods
        EmployeeStatic.printCompanyInfo();
    }
}
