// M5 - Instance vs Static: Splitting a Class Correctly
// INSTANCE fields: each object gets its own copy. name and attendance differ per student.
// STATIC fields: ONE copy shared by ALL objects. collegeName is the same for everyone.
// STATIC methods: belong to the class, not any object.
//                 They CANNOT access instance fields (name, attendance) because there
//                 is no "this" object — they don't know WHICH student to look at.

public class Student {

    // Instance fields — different for every student
    String name;
    int attendance;

    // Static field — shared once across ALL Student objects
    static String collegeName = "SRM Institute of Science and Technology";

    // Static counter — increments every time a new Student is created
    static int studentCount = 0;

    public Student(String name, int attendance) {
        this.name       = name;
        this.attendance = attendance;
        studentCount++;   // shared counter goes up every time
    }

    // Static method — can only use static fields (collegeName, studentCount)
    // If you try to print 'name' here, the compiler will give an error
    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {
        new Student("Arjun", 85);
        new Student("Priya",  90);

        // Call through the CLASS NAME, not through an object — this is the correct style for static
        Student.printCollegeInfo();
    }
}
