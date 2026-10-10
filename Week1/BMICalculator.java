public class BMICalculator {

    // Returns a category label based on the BMI value
    public static String getBmiStatus(double bmi) {
        if      (bmi < 18.5) return "Underweight";
        else if (bmi < 25.0) return "Normal";
        else if (bmi < 30.0) return "Overweight";
        else                 return "Obese";
    }

    // Prints the full wellness report as a formatted table
    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.printf("%-10s %-12s %-14s %-8s %-15s%n",
                          "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("-".repeat(62));

        for (int i = 0; i < heights.length; i++) {
            // BMI formula: weight (kg) / height^2 (m^2)
            double bmi    = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);

            System.out.printf("%-10d %-12.2f %-14.1f %-8.2f %-15s%n",
                              (i + 1), heights[i], weights[i], bmi, status);
        }
    }

    public static void main(String[] args) {
        // Sample data for 10 employees
        double[] heights = { 1.75, 1.60, 1.80, 1.55, 1.70,
                             1.65, 1.90, 1.72, 1.58, 1.68 };
        double[] weights = { 70,   90,   85,   45,   95,
                             60,  100,   68,   55,   75  };

        System.out.println("=== Corporate Wellness Report ===\n");
        printWellnessReport(heights, weights);
    }
}
