//import javax.swing.*;

import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;

//import java.sql.Date;

@SuppressWarnings("CanBeFinal")

public class AirlineReservation {
    private static double guess;
    private String[] passengerName = new String[10];
    private boolean[] seatPosition = new boolean[10];
    private Scanner userInput = new Scanner(System.in);
    private int totalSeatAvailable = 10;
    private int firstClassSeatAvailable = 5;
    private int economySeatAvailable = 5;

    private static void guesser(double number) {
        SecureRandom guesser = new SecureRandom();
        guess = 1 + guesser.nextInt((int) number);
    }

    private static void calculateSquareRoot(double number) {
        System.out.printf("Guess is %.2f%n", guess);
        double quotient = number / guess;
        double newGuess = (quotient + guess) / 2;
        if (Math.abs(newGuess - guess) >= 0.0001) {
            guess = newGuess;
            calculateSquareRoot(number);
        } else
            System.out.printf("The square root is: %.2f", newGuess);
    }

    @SuppressWarnings("unused")
    private static void squareRoot(double number) {
        guesser(number);
        calculateSquareRoot(number);

    }

    private String getPassengerName(int seatNumber) {
        return passengerName[seatNumber - 1];
    }

    private void setPassengerName(int seatNumber, String passengerName) {
        this.passengerName[seatNumber - 1] = passengerName;
    }

    private int getSeatPosition(int seatNumber) {
        return seatNumber;
    }

    private void setSeatPosition(int seatNumber) {
        seatPosition[seatNumber - 1] = true;
        totalSeatAvailable--;
        if (seatNumber <= 5)
            firstClassSeatAvailable--;
        else
            economySeatAvailable--;

    }

    private void collectPassengerName() {
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Enter Full Name: \n");
            String name = userInput.nextLine();
            setPassengerClass(name);
        } catch (InputMismatchException Exception) {
            System.out.println("Wrong input, try Again");

        }


    }

    private void setPassengerClass(String passengerName) {
        int passengersClassChoice = 0;
        System.out.print("\nEnter 1 for First Class, 2 for Economy Class:\n ");
        try {
            passengersClassChoice = userInput.nextInt();
            if (passengersClassChoice > 2 || passengersClassChoice < 1) {
                System.out.print("Wrong Input, Enter 1 or 2");
                setPassengerClass(passengerName);
            } else {
                assignSeat(passengerName, passengersClassChoice);
            }

        } catch (InputMismatchException Exception) {


        }

    }

    private void assignSeat(String passengerName, int passengerClass) {
        try {
            if (passengerClass == 1) {
                System.out.print("Choose Preferred Seat from 1 - 5: ");
                showAvailableSeats(passengerClass, passengerName);
                int seatChoice = userInput.nextInt();
                if (seatChoice > 5 || seatChoice < 1) {
                    System.out.println("Kindly Enter number between 1 and 5");
                    assignSeat(passengerName, passengerClass);
                }
                checkSeatAvailability(passengerName, seatChoice, passengerClass);
            } else {
                System.out.print("Choose Preferred Seat from 6 - 10: ");
                showAvailableSeats(passengerClass, passengerName);
                int seatChoice = userInput.nextInt();
                if (seatChoice > 10 || seatChoice < 6) {
                    System.out.println("Kindly Enter number between 6 and 10");
                    assignSeat(passengerName, passengerClass);
                }
                checkSeatAvailability(passengerName, seatChoice, passengerClass);
            }
        } catch (InputMismatchException Exception) {
            System.out.println("Wrong Input");
            //assignSeat(passengerName,passengerClass);
        }
    }

    private void showAvailableSeats(int passengersClass, String passengerName) {
        System.out.print("\nSeats available: ");
        if (passengersClass == 1) {
            if (firstClassSeatAvailable == 0) {
                System.out.println("No Seat Available");
                checkIfSeatsAreFull(passengerName, passengersClass);
            }
            for (int i = 0; i < 5; i++) {
                if (!seatPosition[i]) {
                    System.out.print(i + 1 + " ");

                }
            }
        } else {
            if (economySeatAvailable == 0) {
                System.out.println("No Seat Available");
                checkIfSeatsAreFull(passengerName, passengersClass);
            }

            for (int i = 5; i < seatPosition.length; i++) {
                if (!seatPosition[i]) {
                    System.out.print(i + 1 + " ");

                }
            }
        }
        System.out.println();
    }

    private void checkSeatAvailability(String passengerName, int userSeatChoice, int passengerClass) {
        checkIfSeatsAreFull(passengerName, 3); //checks if the flight is full before checking the classes
        checkIfSeatsAreFull(passengerName, passengerClass);

        if (seatPosition[userSeatChoice - 1]) {
            System.out.print("Seat Booked by another passenger; Choose another seat\n");
            assignSeat(passengerName, passengerClass);


        } else {
            setSeatPosition(userSeatChoice);
            setPassengerName(userSeatChoice, passengerName);
            printUserTicket(userSeatChoice, passengerClass);
        }

    }

    private void checkIfSeatsAreFull(String passengerName, int passengerClass) {
        int alternateClass = 0;
        int seatAvailable;
        switch (passengerClass) {
            case 1:
                seatAvailable = firstClassSeatAvailable;
                alternateClass = 2;
                break;

            case 2:
                seatAvailable = economySeatAvailable;
                alternateClass = 1;
                break;

            default:
                seatAvailable = totalSeatAvailable;
                break;
        }

        try {

            if (seatAvailable == 0) {
                if (passengerClass == 1) {
                    System.out.println("First Class Full, Wanna try our Economy Class? Press 1: ");
                    if (userInput.nextInt() == 1) {
                        assignSeat(passengerName, alternateClass);
                        startReservation(); //fixes a bug
                    } else {
                        System.out.println("Sorry, Next Flight Leaves in 3Hours");
                        startReservation();
                    }

                }
                if (passengerClass == 2) {
                    System.out.println("Economy Class Full, Wanna try our First Class? Press 1: ");
                    if (userInput.nextInt() == 1) {
                        assignSeat(passengerName, alternateClass);
                        startReservation(); //fixes bugs in return of method call
                    } else {
                        System.out.println("Sorry, Next Flight Leaves in 3Hours");
                        startReservation();
                    }
                }
                if (passengerClass == 3) {
                    System.out.println("Flight fully booked,\nNext Flight is in 3 Hours, Have a nice trip");
                    printFlightManifest();
                    System.exit(0);
                }
            }
        } catch (InputMismatchException Exception) {
            System.out.println("Wrong Input re-enter");
        }

    }

    private void printFlightManifest() {
        String flightClass;
        System.out.println("\n\n****************************");
        System.out.println("Semicolon Airline \nPassenger's Manifest");
        System.out.println("PassengerName    Seat Number     Class\n");
        for (int i = 1; i <= seatPosition.length; i++) {
            if (getSeatPosition(i) >= 1 && getSeatPosition(i) <= 5) {
                flightClass = "First Class";
            } else {
                flightClass = "Economy Class";
            }
            System.out.printf("%s               %d           %s%n", getPassengerName(i), getSeatPosition(i), flightClass);
        }
    }

    private void printUserTicket(int position, int passengerClass) {
        String flightClass;
        if (passengerClass == 1) {
            flightClass = "First Class";
        } else {
            flightClass = "Economy Class";
        }
        System.out.println("\n\nSemicolon Airline");
        System.out.println("*******************************");
        System.out.println("*Your Flight has been booked,\n" +
                "*Here are the details:    ");
        System.out.printf("*Ticket Number:       %s\n", System.currentTimeMillis());
        System.out.printf("*Passenger Name:      %s \n", getPassengerName(position));
        System.out.printf("*Seat number:         %d  \n", position);
        System.out.printf("*Flight Class:        %s  \n", flightClass);
        System.out.println("*Enjoy your flight");
        System.out.println("*******************************\n");

    }

    @SuppressWarnings("WeakerAccess")
    public void startReservation() {

        for (int i = 0; i < seatPosition.length; i++) {
            collectPassengerName();

        }
    }
}

