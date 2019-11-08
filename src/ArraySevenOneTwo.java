import java.util.Arrays;
import java.util.Scanner;

public class ArraySevenOneTwo {
    int[] array;
    int input;

    Scanner userInput = new Scanner(System.in);

    public void collectInput(int position) {
        System.out.printf("Enter Number %d%n", position + 1);
        input = userInput.nextInt();
        firstCheck(position);
    }

    public void firstCheck(int position) {
        if (input >= 10 && input <= 100) {
            System.out.println("check 1 passed");
            secondCheck(position);
        } else {
            System.out.println("Error enter Number between 10 and 100");
            collectInput(position);
        }
    }

    public void secondCheck(int position) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == input) {
                System.out.printf("Checked against number %d Failed%nYour current input is identical to number %d%n", i + 1, i + 1);
                System.out.println("Enter Unique Number");
                collectInput(position);

            } else {
                System.out.printf("Checked against number %d passed%n", i + 1);
            }


        }
        System.out.printf("All checks passed, %d saved as  number %d%n", input, position + 1);

    }


    public void startProgram() {
        System.out.println("Enter the number of Unique numbers to work with");
        int arrayLenght = userInput.nextInt();
        array = new int[arrayLenght];
        for (int i = 0; i < array.length; i++) {
            collectInput(i);
            array[i] = input;
        }
        System.out.println(Arrays.toString(array));
    }
    //ariyotolulademi@gmail.com

}

