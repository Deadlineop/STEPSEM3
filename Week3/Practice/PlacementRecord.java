// M1 - From Parallel Arrays to a Class
// OOP replaces three separate parallel arrays with one clean object per record.
// If the arrays go out of sync, the program breaks. With a class, each student
// carries their own data — they can never become inconsistent.

public class PlacementRecord {

    // Instance fields: every object has its own copy of these
    String studentName;
    String company;
    double packageLpa;

    // Constructor: sets all three fields in one shot
    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company     = company;
        this.packageLpa  = packageLpa;
    }

    // Instance method: prints one formatted line for this record
    public void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }

    public static void main(String[] args) {
        // Store objects in an array instead of three parallel arrays
        PlacementRecord[] records = {
            new PlacementRecord("Ravi",    "TCS",      4.5),
            new PlacementRecord("Anitha",  "Zoho",     6.2),
            new PlacementRecord("Karthik", "Infosys",  4.0)
        };

        // Print each record in a loop — clean and extensible
        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}
