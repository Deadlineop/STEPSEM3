// M2 - Payroll Batch Bonus Round
// CONCEPT: the 'this' keyword to resolve field vs parameter naming clashes
// When a parameter has the same name as a field, 'this.salary' means the FIELD
// and plain 'salary' means the parameter. Without 'this', the field never gets set.

public class EmployeePayroll {

    String empId;
    double salary;   // <-- instance field

    // 'salary' parameter has the same name as the field
    // 'this.salary' = the field; 'salary' = the constructor parameter
    public EmployeePayroll(String empId, double salary) {
        this.empId   = empId;
        this.salary  = salary;   // without 'this.', the field stays 0
    }

    // Same clash in the method: 'salary' parameter vs 'this.salary' field
    public void raiseSalary(double salary) {
        this.salary += salary;   // adds parameter value TO the field
    }

    public void printSalary() {
        System.out.println(empId + " | Final Salary: Rs " + salary);
    }

    public static void main(String[] args) {
        EmployeePayroll[] employees = {
            new EmployeePayroll("E-101", 40000),
            new EmployeePayroll("E-102", 55000),
            new EmployeePayroll("E-103", 62000),
            new EmployeePayroll("E-104", 48000)
        };

        // Apply the same bonus to every employee in one pass
        for (EmployeePayroll emp : employees) {
            emp.raiseSalary(5000);
            emp.printSalary();
        }
    }
}
