// M3 Assignment - Overloaded Constructors for an Employee
// Permanent employees have a known salary from day one.
// Interns join without a salary (salary = 0) and isIntern = true.
// The intern constructor chains to the permanent constructor via this(...),
// then sets isIntern = true afterwards — no duplicated setup code.

public class Employee {

    String empId;
    String empName;
    double salary;
    boolean isIntern;

    // Permanent employee constructor
    public Employee(String empId, String empName, double salary) {
        this.empId    = empId;
        this.empName  = empName;
        this.salary   = salary;
        this.isIntern = false;
    }

    // Intern constructor — chains to the 3-arg constructor with salary = 0,
    // then overrides isIntern to true
    public Employee(String empId, String empName) {
        this(empId, empName, 0);   // must be the FIRST statement
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }

    public static void main(String[] args) {
        Employee permanent = new Employee("E-101", "Divya", 65000);
        Employee intern    = new Employee("E-102", "Arjun");

        permanent.printProfile();
        intern.printProfile();
    }
}
