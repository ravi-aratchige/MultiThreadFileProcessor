import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String selection;

        do {
            System.out.println("\n----- MULTI-THREAD FILE PROCESSOR MENU -----");
            System.out.println("1. Launch a demo of the application");
            System.out.println("2. Launch the GUI");
            System.out.println("3. Exit");
            System.out.print("Enter your selection: ");
            selection = scanner.nextLine();

            switch (selection) {
                case "1":
                    launchDemo();
                    break;
                case "2":
                    launchGUI();
                    break;
                case "3":
                    System.out.println("Exiting application...");
                    System.out.println("Have a nice day!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        } while (!selection.equals("3"));

        scanner.close();
    }

    private static void launchDemo() {
        // set the directory path to the "demo" folder
        String directoryPath = "demo";

        // instantiate the ConcurrentFileProcessor
        ConcurrentFileProcessor processor = new ConcurrentFileProcessor(directoryPath);

        System.out.println("\n----- DEMO: MULTI-THREAD FILE PROCESSOR -----");
        // begin multithreaded file processor
        processor.processFiles();
        System.out.println("\n---------------------------------------------");
    }

    private static void launchGUI() {
        System.out.println("\n----- GUI: MULTI-THREAD FILE PROCESSOR -----");
        // start the GUI
        System.out.println("\nThe GUI has been launched.");
        new FileProcessorGui();
    }
}
