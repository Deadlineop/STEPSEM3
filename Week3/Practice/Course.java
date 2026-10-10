// M3 - Overloaded Constructors for a Course
// Constructor OVERLOADING means having multiple constructors with different parameters.
// this(...) CHAINING means one constructor calls another to avoid repeating code.
// Theory-only courses just call the full constructor with labCredits = 0.

public class Course {

    String code;
    String title;
    int credits;
    int labCredits;

    // Full constructor: used when there is a lab component
    public Course(String code, String title, int credits, int labCredits) {
        this.code       = code;
        this.title      = title;
        this.credits    = credits;
        this.labCredits = labCredits;
    }

    // Overloaded constructor: theory-only courses — chains to the 4-arg constructor above
    // this(...) must be the FIRST line in the constructor body
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);  // labCredits is 0 for theory-only
    }

    // Returns the combined credit count
    public int totalCredits() {
        return credits + labCredits;
    }

    public static void main(String[] args) {
        // Theory-only: uses the 3-argument constructor, labCredits = 0 internally
        Course theoryOnly = new Course("21CSC201J", "Data Structures", 4);

        // Course with lab: uses the 4-argument constructor
        Course withLab = new Course("21CSC205L", "DSA Lab", 3, 1);

        System.out.println(theoryOnly.code + " total credits: " + theoryOnly.totalCredits());
        System.out.println(withLab.code    + " total credits: " + withLab.totalCredits());
    }
}
