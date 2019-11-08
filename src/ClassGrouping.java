import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.Arrays;

public class ClassGrouping {

    static SecureRandom staticRandomNumber = new SecureRandom();
    private static int[] shuffledArray;
    private static String groupList;
    private static String[] cohortTwoNatives = {"SCV2001 Somto Momah", "SCV2002 Waliu Olaifa", "SCV2003 Chibuzo Ekejiuba", "SCV2004 Makinde Akinola", "SCV2005 Cynthia Ineh",
            " SCV2006 Nelson Ushingio", "SCV2007 Adejoke Opeyemi-Ogungbire", "SCV2008 Adebayo Adesanya", "SCV2009 Samuel Bako", "SCV2010 Oladayo Ayoola",
            "SCV2011 Hameed Sanusi", "SCV2012 Samuel Iruedo", "SCV2013 Oluwakemi Yusuf", "SCV2014 Naheem Noah", "SCV2015 Akin Odeku",
            "SCV2016 Joy Fatoye", "SCV2017 Joel Onojason", "SCV2018 Taver Targema", "SCV2019 Isaac Olowofila", "SCV2020 Adesola Adedugbe",
            "SCV2021 Joshua Matthew", "SCV2022 Joy Oluwagbemi", "SCV2023 Olufunso Odunlami", "SCV2024 Adekunle Adefokun", "SCV2025 Mbonu Ogochukwu",
            "SCV2026 Rufai Kayode", "SCV2027 Emmanuel Oboh", "SCV2028 Ajewola Bejide", "SCV2029 Elliot Akaweingha" +
            "", "SCV2030 Felix Femi Ogbangwor",
            "SCV2031 Florence Asuelimen", "SCV2032 Ojo Ilesanmi", "SCV2033` Tayo Olakunle", "SCV2034 Mogbitse Okitikpi", "SCV2035 Jibola Bakare",
            "SCV2036 Momoh Mohammed Sherif", "SCV2037 Alade Yinka", "SCV2038 Segun Ishola", "SCV2039 Abdulazeez Badeggi", "SCV2040 Abraham Ariyo"};
    static String[] shuffledCohortTwoNatives = new String[cohortTwoNatives.length];
    static private int[] originalArray;


//    public ClassGrouping(int numberOfCohorts){
//        shuffledArray =new int[numberOfCohorts];
//        originalArray = new int[numberOfCohorts];
//        for (int i = 1; i<=shuffledArray.length; i++) {
//            shuffledArray[i] = i;
//            originalArray[i] = i;
//        }

//    }
//    public ClassGrouping(){
//
//   }

    public static String getCohort(int index) {
        String cohortId;
        if (shuffledArray[index] < 10)
            cohortId = "SCV200" + shuffledArray[index];
        else

            cohortId = "SCV20" + shuffledArray[index];

        return cohortId;
    }

    public static void createCohorts(int numberOfCohorts) {
        shuffledArray = new int[numberOfCohorts];
        originalArray = new int[numberOfCohorts];
        for (int i = 0; i < shuffledArray.length; i++) {
            shuffledArray[i] = i + 1;
            originalArray[i] = i + 1;
        }

        System.out.println(Arrays.toString(shuffledArray));
    }

    public static void randomizeCohorts(int numberOfCohorts) {
        int firstHand = staticRandomNumber.nextInt(numberOfCohorts);
        int secondHand = staticRandomNumber.nextInt(numberOfCohorts);
        int thirdHand = shuffledArray[secondHand];
        shuffledArray[secondHand] = shuffledArray[firstHand];
        shuffledArray[firstHand] = thirdHand;

        for (int i = 0; i < shuffledArray.length; i++) {
            if (shuffledArray[i] == originalArray[i])
                randomizeCohorts(originalArray.length);

        }

    }

    public static void createPair(int numberPerPair) {
        groupList = "";
        int groupId = 1;
        int counter = 1;
        groupList = String.format("Group %d%n", groupId);
        while (counter < shuffledArray.length) {
            groupList += getCohort(counter) + " ";
//            groupList += String.format("%s%n", shuffledArray[counter-1]);
            if (counter % numberPerPair == 0) {
                groupId++;
                if (counter == 40)
                    break;
                groupList += String.format("%n%n Group %d%n", groupId);


            }
            counter++;

        }

    }

    public static void displayGroup() {
        createPair(Integer.parseInt(JOptionPane.showInputDialog("A group of?")));
        JTextArea textArea = new JTextArea(groupList);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(270, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Group List for Peer Programming",
                JOptionPane.YES_NO_OPTION);
    }

    public static void startProgram() {
        createCohorts(Integer.parseInt(JOptionPane.showInputDialog("Number of Cohorts")));
        randomizeCohorts(originalArray.length);
        System.out.println(Arrays.toString(shuffledArray));
        displayGroup();

    }

    public static void fillArray() {
        for (int i = 0; i < cohortTwoNatives.length; i++)
            shuffledCohortTwoNatives[i] = cohortTwoNatives[i];
    }

    public static void randomizeCohortTwoNatives() {
        SecureRandom staticRandomNumber = new SecureRandom();
        int firstHand = staticRandomNumber.nextInt(shuffledCohortTwoNatives.length);
        int secondHand = staticRandomNumber.nextInt(shuffledCohortTwoNatives.length);
        String thirdHand = shuffledCohortTwoNatives[secondHand];
        shuffledCohortTwoNatives[secondHand] = shuffledCohortTwoNatives[firstHand];
        shuffledCohortTwoNatives[firstHand] = thirdHand;

        for (int i = 0; i < shuffledCohortTwoNatives.length; i++) {
            if (shuffledCohortTwoNatives[i].equals(cohortTwoNatives[i])) {
                randomizeCohortTwoNatives();
            }
        }

    }

    public static void pairCohortTwoNatives() {
        int numberPerPair = (Integer.parseInt(JOptionPane.showInputDialog("A group of?")));

        groupList = "";
        int groupId = 1;
        int counter = 1;
        groupList = String.format("Group %d%n", groupId);
        while (counter < cohortTwoNatives.length) {
            groupList += String.format("%s%n", shuffledCohortTwoNatives[counter - 1]);
            if (counter % numberPerPair == 0) {
                groupId++;
                if (counter == cohortTwoNatives.length)
                    break;
                groupList += String.format("%n%n Group %d%n", groupId);
            }
            counter++;

        }

    }

    public static void displayCohortTwoGroup() {
        pairCohortTwoNatives();
        JTextArea textArea = new JTextArea(groupList);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(270, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Group List for Peer Programming",
                JOptionPane.YES_NO_OPTION);
    }

    public static void startCohortTwoPairingProgram() {
        fillArray();
        randomizeCohortTwoNatives();
        displayCohortTwoGroup();
    }

    public static int[] randomizeArray(int[] array) {
        int[] shuffledArray = new int[array.length];
        for (int i = 0; i < shuffledArray.length; i++) {
            shuffledArray[i] = array[i];
        }
        for (int i = 0; i < shuffledArray.length * 4; i++) {
            int firstHand = staticRandomNumber.nextInt(array.length);
            int secondHand = staticRandomNumber.nextInt(array.length);
            int thirdHand = shuffledArray[secondHand];
            shuffledArray[secondHand] = shuffledArray[firstHand];
            shuffledArray[firstHand] = thirdHand;
        }

        for (int i = 0; i < shuffledArray.length; i++) {
            if (shuffledArray[i] == array[i])
                randomizeArray(shuffledArray);

        }
        System.out.print(Arrays.toString(shuffledArray));
        return shuffledArray;
    }

    public static String[] randomizeArray(String[] array) {
        String[] shuffledArray = new String[array.length];
        for (int i = 0; i < shuffledArray.length; i++) {
            shuffledArray[i] = array[i];
        }
        for (int i = 0; i < shuffledArray.length * 4; i++) {
            int firstHand = staticRandomNumber.nextInt(array.length);
            int secondHand = staticRandomNumber.nextInt(array.length);
            String thirdHand = shuffledArray[secondHand];
            shuffledArray[secondHand] = shuffledArray[firstHand];
            shuffledArray[firstHand] = thirdHand;
        }

        for (int i = 0; i < shuffledArray.length; i++) {
            if (shuffledArray[i].equals(array[i]))
                randomizeArray(shuffledArray);

        }
        System.out.print(Arrays.toString(shuffledArray));
        return shuffledArray;
    }

    public static char[] randomizeArray(char[] array) {
        char[] shuffledArray = new char[array.length];
        for (int i = 0; i < shuffledArray.length; i++) {
            shuffledArray[i] = array[i];
        }
        for (int i = 0; i < shuffledArray.length * 4; i++) {
            int firstHand = staticRandomNumber.nextInt(array.length);
            int secondHand = staticRandomNumber.nextInt(array.length);
            char thirdHand = shuffledArray[secondHand];
            shuffledArray[secondHand] = shuffledArray[firstHand];
            shuffledArray[firstHand] = thirdHand;
        }

        for (int i = 0; i < shuffledArray.length; i++) {
            if (shuffledArray[i] == (array[i]))
                randomizeArray(shuffledArray);

        }
        System.out.print(Arrays.toString(shuffledArray));
        return shuffledArray;
    }

    public static double[] randomizeArray(double[] array) {
        double[] shuffledArray = new double[array.length];
        for (int i = 0; i < shuffledArray.length; i++) {
            shuffledArray[i] = array[i];
        }
        for (int i = 0; i < shuffledArray.length * 4; i++) {
            int firstHand = staticRandomNumber.nextInt(array.length);
            int secondHand = staticRandomNumber.nextInt(array.length);
            double thirdHand = shuffledArray[secondHand];
            shuffledArray[secondHand] = shuffledArray[firstHand];
            shuffledArray[firstHand] = thirdHand;
        }

        for (int i = 0; i < shuffledArray.length; i++) {
            if (shuffledArray[i] == (array[i]))
                randomizeArray(shuffledArray);

        }
        System.out.print(Arrays.toString(shuffledArray));
        return shuffledArray;
    }

    public void setShuffledArray(int index, int elementValue) {
        shuffledArray[index] = elementValue;
    }

}

