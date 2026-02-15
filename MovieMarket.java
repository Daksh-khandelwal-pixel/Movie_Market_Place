package Movie_Market_Place;
import java.util.*;

// --- 1. THE BLUEPRINT (Class) ---
// Why? Without this, a 'User' is just loose variables. 
// This groups 'name' and 'pass' into a single object.
class Users {
    String username;
    String password;

    // --- THE CONSTRUCTOR ---
    // Why? It forces us to give data immediately when creating a User.
    // It prevents "empty" users from existing.
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

// --- 2. THE SYSTEM (Main) ---
public class MovieMarket {
    
    // --- THE DATABASE (Static List) ---
    // Why Static? So 'main', 'signup', and 'login' all share the SAME list.
    // If it wasn't static, every method would see a different, empty list.
    static ArrayList<Users> usersDatabase = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in); // Shared scanner

    public static void main(String[] args) {
        while (true) { // Keeps the app running forever until Exit
            System.out.println("\n=== MOVIE MARKETPLACE ===");
            System.out.println("1. Sign-Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print(">> Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Fixes Java's "skip line" bug

            switch (choice) {
                case 1: signup(); break;
                case 2: 
                    if (login()) {
                        System.out.println(">> [SUCCESS] Logged in!");
                        // Future: movieMenu(); 
                    }
                    break;
                case 3: System.exit(0);
                default: System.out.println("Invalid option.");
            }
        }
    }

    // --- LOGIC: SIGN UP ---
    public static void signup() {
        System.out.print("Set Username: ");
        String name = scanner.nextLine();

        // [SEARCH LOGIC]
        // Why loop? We must check every single user in the list 
        // to make sure this name isn't already taken.
        for (Users u : usersDatabase) {
            if (u.username.equals(name)) {
                System.out.println(">> [ERROR] Username taken!");
                return; // Stops the method immediately
            }
        }

        System.out.print("Set Password: ");
        String pass = scanner.nextLine();

        // [ACTION] Create the Object and add to Database
        usersDatabase.add(new Users(name, pass));
        System.out.println(">> [SUCCESS] Account created.");
    }

    // --- LOGIC: LOGIN ---
    public static boolean login() {
        System.out.print("Username: ");
        String name = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        // [SEARCH LOGIC]
        // Iterate through the database to find a match.
        // We need BOTH name AND password to match.
        for (Users u : usersDatabase) {
            if (u.username.equals(name) && u.password.equals(pass)) {
                return true; // Match found!
            }
        }
        
        System.out.println(">> [ERROR] Wrong credentials.");
        return false; // Loop finished, no match found.
    }
}