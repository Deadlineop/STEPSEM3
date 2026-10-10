public class CSVStudentParser {

    public static void parseStudentRecord(String csvLine) {
        // split(",") breaks the string at every comma and returns a String array
        String[] fields = csvLine.split(",");

        // Exactly 3 fields required: Name, RollNumber, Department
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name   = fields[0].trim();
        String rollNo = fields[1].trim();
        String dept   = fields[2].trim();

        System.out.println("Name: " + name + " | Roll No: " + rollNo + " | Dept: " + dept);
    }

    public static void main(String[] args) {
        parseStudentRecord("Ananya Verma,RA2211003010123,CSE");
        parseStudentRecord("Ananya Verma,CSE");             // only 2 fields, invalid
        parseStudentRecord("John,101,ECE,Extra");           // 4 fields, invalid
    }
}
