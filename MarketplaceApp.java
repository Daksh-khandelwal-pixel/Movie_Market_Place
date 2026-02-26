package Movie_Market_Place;
import java.util.*;

 ==========================================
// 1. MAIN APPLICATION ( The "Controller" )
// ==========================================

public class MarketplaceApp {
    
    // STORAGE: A static list to hold users while the app runs
    static ArrayList<User> userDatabase = new ArrayList<>();

    // FEATURE: Create Account
    public static void signup() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter new username: ");
        String newName = scanner.nextLine();

        // LOGIC: Check for duplicates before creating
        for (User user : userDatabase) {
            if (user.username.equals(newName)) {
                System.out.println(">> [ERROR] User already exists.");
                return; // Stop the function immediately
            }
        }

        System.out.print("Enter password: ");
        String newPass = scanner.nextLine();

        userDatabase.add(new User(newName, newPass));
        System.out.println(">> [SUCCESS] Account created.");
    }

    

    // FEATURE: Authentication
    // Returns boolean so Main knows if we should proceed to the store
    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        for (User user : userDatabase) {
            if (user.username.equals(name) && user.password.equals(pass)) {
                System.out.println(">> [SUCCESS] Logged in!");
                return true;
            }
        }
        System.out.println(">> [ERROR] Invalid credentials.");
        return false;
    }



    // FEATURE: The Core Logic (Searching & Renting)
    public static void rentalMenu(Scanner scanner) {
        // 1. SIMULATE API DATA
        String[] apiResults = {"The Dark Knight", "Oldboy", "Parasite"};
        double[] apiRatings = {9.2, 8.4, 8.6};

        // 2. DISPLAY OPTIONS
        System.out.println("\n--- MOVIE SEARCH RESULTS ---");
        for (int i = 0; i < apiResults.length; i++) {
            // (i+1) converts index 0 to Human Number 1
            System.out.println((i + 1) + ". " + apiResults[i] + " (IMDb: " + apiRatings[i] + ")");
        }

        // 3. GET USER CHOICE
        System.out.print("Select movie number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // FIX: Consume the newline left by nextInt()

        // VALIDATION: Ensure user picks a valid number (1 to 3)
        if (choice > 0 && choice <= apiResults.length) {
            
            // MAP: Convert User Input (1) to Array Index (0)
            String selectedMovie = apiResults[choice - 1];
            double selectedRating = apiRatings[choice - 1];

            // 4. POLYMORPHISM SETUP
            // We create a "Container" of type Rental.
            // It doesn't know if it's Physical or Online yet.
            Rental myRental; 

            System.out.println("Select Type: (1) Online Stream  (2) Physical DVD");
            int typeChoice = scanner.nextInt();
            scanner.nextLine(); // FIX: Consume newline

            // 5. OBJECT CREATION (The Fork in the Road)
            if (typeChoice == 1) {
                System.out.print("Enter Quality (e.g., 1080): ");
                int quality = scanner.nextInt();
                scanner.nextLine(); 
                
                // The "Rental" container now holds an Online object
                myRental = new OnlineRental(selectedMovie, selectedRating, "Stream", quality);
            
            } else {
                System.out.print("Enter Shipping Address: ");
                String address = scanner.nextLine();
                
                // The "Rental" container now holds a Physical object
                myRental = new PhysicalRental(selectedMovie, selectedRating, "DVD", address);
            }

            // 6. EXECUTION
            // Java automatically checks which object is inside 'myRental'
            // and runs the correct showDetails() method.
            myRental.showDetails();
        
        } else {
            System.out.println("Invalid Movie Selection.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean appRunning = true;

        System.out.println("=== WELCOME TO MOVIE MARKETPLACE ===");

        while (appRunning) {
            System.out.println("\n1. Signup\n2. Login\n3. Exit");
            System.out.print("Choose option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Fix newline bug

            switch (option) {
                case 1:
                    signup();
                    break;
                case 2:
                    // LOGIC FLOW: Only open rental menu IF login is true
                    if (login()) {
                        rentalMenu(scanner);
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    appRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}