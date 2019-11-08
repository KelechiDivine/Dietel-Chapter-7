/*
Ask number of scores

Collect Score
*
*
* */

import java.util.Scanner;

public class GradePrinter {
    private int[] score;
    private Scanner userInput = new Scanner(System.in);

    @SuppressWarnings("unused")
    private static String gradeStudent(int grade) {
        String point;
        if (grade > 100) {
            point = "A";
        } else if (grade > 90) {
            point = "B";
        } else if (grade > 80) {
            point = "C";
        } else if (grade > 70) {
            point = "D";
        } else if (grade > 60) {
            point = "E";
        } else if (grade > 50) {
            point = "F";
        } else {
            point = "Go home";
        }
        return point;

    }

    public void setScore(int position) {
        System.out.printf("Enter score for student %d: %n", position + 1);
        int input = userInput.nextInt();
        checkNumber(input, position);
    }

    public void checkNumber(int input, int position) {
        if (input >= 0 && input <= 100) {
            System.out.printf("Valid Score%nNumber within Range %nStudent %d score saved as %d%n", position + 1, input);
            score[position] = input;
        } else {
            System.out.printf("Error Re-enter score between 0 and 100%n for student %d%n", position + 1);
            setScore(position);

        }

    }

    public char getGrade(int position) {
        int score = this.score[position];
        char grade;
        switch (score / 10) {
            case 10:
            case 9:
                grade = 'A';
                break;
            case 8:
            case 7:
                grade = 'B';
                break;
            case 6:
                grade = 'C';
                break;
            case 5:
                grade = 'D';
                break;
            case 4:
                grade = 'E';
                break;
            default:
                grade = 'F';
                break;

        }
        return grade;
    }

    public void startProgram() {

        System.out.printf("Enter Number of Students: %n");
        score = new int[userInput.nextInt()];
        for (int i = 0; i < score.length; i++) {
            setScore(i);
        }
        System.out.printf("%n%n");
        System.out.printf("%s%10s%10s%n", "Student Number", "Student Score", "Student Grade");
        for (int i = 0; i < score.length; i++) {
            System.out.printf("Student %d%10d%10s%n", i + 1, score[i], getGrade(i));
        }
    }

}
