public class FileExtensionValidator {

    public static String validateFileExtension(String filename) {
        // lastIndexOf('.') finds the position of the LAST dot in the filename
        // This handles cases like "report.v2.pdf" correctly
        int dotIndex = filename.lastIndexOf('.');

        // -1 means no dot found; dotIndex at the end means empty extension
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return "Rejected - invalid file type";
        }

        // Extract everything after the last dot and lowercase it
        String extension = filename.substring(dotIndex + 1).toLowerCase();

        if (extension.equals("pdf") || extension.equals("docx") || extension.equals("zip")) {
            return "Accepted";
        }

        return "Rejected - invalid file type";
    }

    public static void main(String[] args) {
        String[] testFiles = {
            "Assignment1.PDF",
            "notes.txt",
            "project.docx",
            "archive.ZIP",
            "report.v2.pdf",
            "noextension"
        };

        for (String file : testFiles) {
            System.out.println("\"" + file + "\"  ->  " + validateFileExtension(file));
        }
    }
}
