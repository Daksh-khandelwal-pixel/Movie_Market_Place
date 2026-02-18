package Movie_Market_Place.Why_How_Explaining;
import java.util.*;

/*
// ==========================================
// ==========================================

🧠 Deep Dive: The Logic Behind "The Magic Box"
The most complex part of this code is inside rentalMenu, specifically where we define Rental myRental. Here is the deeper logic of why we do it that way.
1. The Problem: Tight Coupling
Without polymorphism, your code would look like a branching tree that never reconnects. You would need separate logic for everything:
If Physical -> Ask Address -> Print Physical Receipt
If Online -> Ask Quality -> Print Online Receipt
If you added a third type (e.g., "Cinema Ticket"), you would have to write a third branch. This makes code messy and hard to maintain.

2. The Solution: Loose Coupling (Polymorphism)
By creating the variable Rental myRental, we create a Unified Interface.

The Container: Rental myRental is a generic container. It doesn't care what is inside, as long as it follows the rules of being a "Rental".
The Switch: When we say myRental = new Online_Rentals(...), we are putting a specific engine into that generic car frame.
The Execution: When we call myRental.showdetails(), we don't need to know what engine is inside. We just hit the gas pedal. The Object itself decides how to react (whether to show an address or a video quality).

This is why Rental had to be Abstract. It forced both children to have a showdetails() button, guaranteeing that myRental.showdetails() will always work, no matter what object is inside.

// ==========================================
// ==========================================
*/



// ==========================================
// CONCEPT 1: ENCAPSULATION (The Blueprint)
// ==========================================
// We wrap data (username/password) into a single unit called 'Users'.
// This keeps our data organized. Instead of having two separate lists 
// for names and passwords, we just have one list of 'User' objects.
class Users{
    String username;
    String password;

    // CONSTRUCTOR: The Initialization Logic
    // This runs automatically when you use 'new Users()'.
    public Users(String username, String password){
        // 'this' keyword fixes the Scope Issue. 
        // Java is confused: "Do you mean the variable inside the class or the one in the brackets?"
        // 'this.username' points to the CLASS variable.
        this.username = username;
        this.password = password;
    }
}


// ==========================================
// CONCEPT 2: ABSTRACTION (The Concept)
// ==========================================
// LOGIC: You can't just buy a "Rental". That's vague.
// You need to specify: Is it a DVD? Is it a Stream?
// By making this 'abstract', we FORCE the programmer to be specific later.
// Writing 'new Rental()' is now illegal code, which prevents logical errors.
abstract class Rental{
    
    // INHERITANCE PREP: These variables are 'protected' by default in the package.
    // This allows child classes (Physical/Online) to access them directly.
    String Movie_Title;
    double Imdb;
    String type;

    public Rental(String Movie_Title,double Imdb,String type){
        this.Movie_Title = Movie_Title; 
        this.Imdb = Imdb;
        this.type = type;
    }

    // SHARED BEHAVIOR (DRY Principle - Don't Repeat Yourself)
    // Since math is the same for both CD and Online, we write it once here.
    // Both subclasses will magically have this method without typing it again.
    public double calculateCost(){
        double basecost = 4.0;
        
        // LOGIC: Price discrimination based on demand (IMDb rating)
        if(Imdb>=8){
            return basecost+3.0;
        }else if(Imdb>6.0 && Imdb<8.0){
            return basecost+2.0;
        }else{
            return basecost;
        }
    }

    // THE CONTRACT (Abstract Method)
    // This is a rule for the future. We are telling Java:
    // "I don't know how to show details yet, but any class that extends me MUST define this."
    public abstract void showdetails();

}

// ==========================================
// CONCEPT 3: INHERITANCE (The "Is-A" Rule)
// ==========================================
// Physical_Rentals "IS A" Rental.
// It inherits all fields (Title, Imdb) but ADDS a specific field: 'shipping_Add'.
class Physical_Rentals extends Rental{
    String shipping_Add; 

    public Physical_Rentals(String Movie_Title,double Imdb,String type, String shipping_Add){
        // 'super()' is the bridge to the parent. 
        // We pass the basic info up to the 'Rental' class to handle, 
        // and we handle the new info (address) right here.
        super(Movie_Title, Imdb,type);
        this.shipping_Add = shipping_Add;
    }

    // CONCEPT 4: POLYMORPHISM (Method Overriding)
    // LOGIC: The parent had an empty method 'showdetails()'. 
    // We are now filling in the blanks.
    // When the program runs, if the object is Physical, THIS specific code runs.
    @Override 
    public void showdetails(){
        System.out.println("Renting " + type + ": " + Movie_Title);
        System.out.println("Cost: $" + calculateCost()); 
        System.out.println("Shipping to: " + shipping_Add); // <--- The unique part
    }
}

// Another "Sibling" Class. 
// It also extends Rental but solves the "Unique Property" problem differently (Video Quality).
class Online_Rentals extends Rental{
    int video_quality; 
    
    public Online_Rentals(String Movie_Title,double Imdb,String type, int video_quality){
        super(Movie_Title, Imdb,type);
        this.video_quality = video_quality;
    }
    
    // POLYMORPHISM IN ACTION:
    // This method has the SAME NAME as the one in Physical_Rentals.
    // But it behaves differently (prints quality instead of address).
    @Override
    public void showdetails(){
        System.out.println("Renting " + type + ": " + Movie_Title);
        System.out.println("Cost: $" + calculateCost());
        System.out.println("Video quality: " + video_quality);
    }

}

public class Concept_Rental{
    // STATIC MEMORY: 
    // This list lives outside of any individual object. 
    // It is the "Brain" of the application that remembers users as long as the app runs.
    static ArrayList<Users> userdatabase =new ArrayList<>();

    public static void signup(){
        Scanner userScanner =new Scanner(System.in);

        System.out.println("Enter your username");
        String newName = userScanner.nextLine();
        
        // LOGIC: Linear Search for Duplicates
        for (Users user : userdatabase) {
            if(user.username.equals(newName)){
                System.out.println("User name already exist");
                return; // [FIX 1] STOP execution. Don't create the duplicate!
            }
        }
        System.out.println("Enter your password");
        String newPass = userScanner.nextLine();

        userdatabase.add(new Users(newName,newPass));
        System.out.println(">> [SUCCESS] Account created.");
    }
    
    public static boolean login(){
        Scanner userScanner =new Scanner(System.in);
        String name;
        String pass;

        System.out.println("Enter your Username");
        name = userScanner.nextLine();

        System.out.println("Enter your password");
        pass = userScanner.nextLine();

        // LOGIC: Authentication Check
        for (Users user : userdatabase){
            // Checking content equality (.equals), not memory location (==)
            if(user.username.equals(name) && user.password.equals(pass)){
                System.out.println("Logged in succesfully");
                return true; // [FIX 2] Signal "Success" to the main method
            }
        }
        System.out.println(">> [ERROR] Wrong credentials."); 
        return false; // Signal "Failure"
    }

    // DEPENDENCY INJECTION (Passing the Scanner)
    // We pass the scanner to avoid opening multiple streams to the keyboard (System.in).
    public static void rentalMenu(Scanner scanner){ 
        String[] apiResults = {"The Dark Knight", "Oldboy", "Parasite"};
        double[] apiRatings = {9.2, 8.4, 8.6}; 
        
        for (int i = 0; i < apiResults.length; i++) {
            System.out.println((i+1) + ". " + apiResults[i]);
        }

        System.out.println("Choose your options from 1-3");
        int choice = scanner.nextInt(); 
        scanner.nextLine(); // [FIX 3] Consumes the "Enter" key left over by nextInt()

        String selectedMovie = ""; 
        double selectedRating = 0.0;
        
        // [FIX 4] Index Logic: Arrays are 0-based.
        // If length is 3, indices are 0,1,2.
        // If user picks 3, we need (choice <= length).
        if(choice > 0 && choice <= apiResults.length){ 
            selectedMovie = apiResults[choice - 1];
            selectedRating = apiRatings[choice - 1];
            
            // ======================================================
            // CONCEPT 7: THE POLYMORPHIC VARIABLE (The Magic Box)
            // ======================================================
            // We create a variable of type 'Rental' (Parent).
            // This variable is a "Shape Shifter".
            // At this line, it is null. It doesn't know what it is yet.
            Rental myRental; 

            System.out.println("select the type of rental you need (online-1, ohyscial-2)");
            int choice_type = scanner.nextInt(); 
            scanner.nextLine(); // Fix newline
            
            // DYNAMIC BINDING (Runtime Decision)
            if(choice_type == 1){ 
                System.out.println("You have choosed online rental");
                System.out.println("Enter the quality you want it in");
                int quality = scanner.nextInt();
                scanner.nextLine();
                
                // The 'Rental' variable now morphs into an 'Online_Rentals' object
                myRental = new Online_Rentals(selectedMovie, selectedRating, "Online", quality);
            } else { 
                System.out.println("You have choose physical rental");
                System.out.println("Your shipping address");
                String address = scanner.nextLine();
                
                // The SAME variable now morphs into a 'Physical_Rentals' object
                myRental = new Physical_Rentals(selectedMovie, selectedRating, "CD/DVD", address);
            }

            // THE RESULT:
            // We call the method on the generic 'Rental' variable.
            // Java looks INSIDE the variable, sees which specific object is there,
            // and runs the correct version of showdetails().
            myRental.showdetails();
        } else {
            System.out.println("Invalid Selection");
        }
    }

    public static void main(String[] args){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Welcome to market plcae");
        boolean ison = true;

        System.out.println("Search for a movie");
        String search_movie = scanner.nextLine(); 

        while (ison) {
            System.out.println("1. signup");
            System.out.println("2. login");
            System.out.println("3. exit");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    signup();
                    break;
                case 2:
                    // [FIX 5] THE GATEKEEPER
                    // We check the boolean result of login().
                    // Only if it returns TRUE do we allow access to rentalMenu().
                    if(login()){
                        rentalMenu(scanner);
                    }
                    break;
                case 3:
                    System.out.println("Program exited");
                    ison = false; 
                    break;
            
                default:
                    System.out.println("Thx for enrolling");
                    break;
            }
        }
    }
}