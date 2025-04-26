import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int numSub = in.nextInt();

        int[] marks = new int[numSub];
        int totalMarks = 0;

        for (int i = 0; i < numSub; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = in.nextInt();
            totalMarks += marks[i];
        }

        double averagePercentage = (double) totalMarks / numSub;

        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("\n--- Result ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        in.close();
    }
}
