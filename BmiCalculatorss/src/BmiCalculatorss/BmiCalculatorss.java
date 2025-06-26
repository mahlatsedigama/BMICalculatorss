package BmiCalculatorss;

import java.util.Scanner;
import java.util.Locale;

public class BmiCalculatorss {

    public static void displayCategory(double bmi) {
        if (bmi < 16.0) {
            System.out.println("Severely Underweight");
            System.out.println("Recommendation: It is highly advised to consult a healthcare professional or a registered dietitian. Severely underweight status can lead to health risks. Focus on nutrient-dense foods and professional guidance for healthy weight gain.");
        } else if (bmi >= 16.0 && bmi <= 18.4) {
            System.out.println("Underweight");
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            System.out.println("Normal");
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            System.out.println("Overweight");
        } else if (bmi >= 30.0 && bmi <= 34.9) {
            System.out.println("Moderately Obese");
        } else if (bmi >= 35.0 && bmi <= 39.4) {
            System.out.println("Severely Obese");
        } else if (bmi >= 40.0) {
            System.out.println("Morbidly Obese");
        }
    }

    public static char askTorepeat(Scanner scanner) {
        char input;
        while (true) {
            System.out.println("Do you want to run the program again? (Y/N)");
            input = scanner.next().charAt(0);
            if (input == 'Y' || input == 'y' || input == 'N' || input == 'n') {
                break;
            } else {
                System.out.println("Invalid input. Please enter Y/y for Yes or N/n for No.");
            }
        }
        return input;
    }

    // New method to get gender choice
    public static char getGenderChoice(Scanner scanner) {
        int choice;
        while (true) {
            System.out.println("\nSelect your Gender:");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Other / Prefer not to say");
            System.out.print("Please select 1, 2, or 3: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1) {
                    return 'M';
                } else if (choice == 2) {
                    return 'F';
                } else if (choice == 3) {
                    return 'O'; // 'O' for Other/Prefer not to say
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                scanner.next(); // Consume invalid input
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        char repeat;

        do {
            int unitChoice = getUnitChoice(scanner);
            char gender = getGenderChoice(scanner); // Get gender input

            double weight = (unitChoice == 1) ? getValidInput(scanner, "Enter your weight in kilograms : ", 10, 600)
                    : getValidInput(scanner, "Enter your weight in pounds : ", 22, 1300);

            System.out.println("\n--- How to Accurately Measure Your Height ");
            System.out.println("1. Stand with your back against a flat wall, with your heels together.");
            System.out.println("2. Use a tape to measure your height");
         
            if (unitChoice == 1) {
                System.out.println("   (Remember to convert to meters if needed, 1 meter = 100 cm)");
            } else {
                System.out.println("   (Remember to measure in inches)");
            }
            System.out.println("");

            double height = (unitChoice == 1) ? getValidInput(scanner, "Enter your height in meters : ", 0.5, 2.5)
                    : getValidInput(scanner, "Enter your height in inches : ", 20, 100);

            double bmi = calculateBMI(unitChoice, weight, height);
            System.out.printf("Your BMI is %.2f\n", bmi);
            BmiCalculatorss.displayCategory(bmi);

            // For demonstration, you could print the gender here, but it doesn't affect BMI calc
            // System.out.println("You entered gender: " + gender);

            repeat = askTorepeat(scanner);
            System.out.println();

        } while (repeat == 'Y' || repeat == 'y');

        System.out.println("Congratulations on using my calculator!");
        scanner.close();
    }

    public static int getUnitChoice(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("\nSelect a preferred unit:\n" // Added newline for spacing
                    + "1. Metric (kg, m)\n"
                    + "2. Imperial (lbs, in)\n"
                    + "Please select either option 1 or option 2:");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter either 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.next();
            }
        }
        return choice;
    }

    public static double getValidInput(Scanner scanner, String prompt, double min, double max) {
        double value;

        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.printf("Please enter a value between %.1f and %.1f.\n", min, max);
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
            }
        }
        return value;
    }

    public static double calculateBMI(int unitChoice, double weight, double height) {
        double totalBMI;

        if (unitChoice == 1) {
            totalBMI = weight / (height * height);
        } else {
            totalBMI = (703 * weight) / (height * height);
        }
        return totalBMI;
    }
}
