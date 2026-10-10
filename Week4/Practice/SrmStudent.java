// M4 - One-Time College Setup, Many Students
// CONCEPT: static block
// A static block runs ONCE when the class is first loaded by the JVM.
// It doesn't matter how many SrmStudent objects you create — the block runs exactly once.
// Use it for setup that belongs to the class itself, not to any individual object.

public class SrmStudent {

    // Static fields: shared by all students — set once in the static block
    static String collegeName;
    static String academicYear;

    // Instance field: each student gets their own name
    String studentName;

    // static block: runs ONCE when the class is first used, before any object is created
    static {
        collegeName  = "SRM Institute of Science and Technology";
        academicYear = "2025-26";
        System.out.println("College info loaded");   // prints only once, no matter how many objects
    }

    public SrmStudent(String studentName) {
        this.studentName = studentName;
        System.out.println("Student record created: " + studentName);
    }

    public static void main(String[] args) {
        String[] names = { "Ravi", "Meera", "Karthik", "Divya", "Anitha" };

        // Static block fires when SrmStudent is first touched — before this loop runs
        for (String name : names) {
            new SrmStudent(name);
        }
    }
}
