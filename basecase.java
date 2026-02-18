package Movie_Market_Place;
import java.util.*;

class Users{
    String username;
    String password;

    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }
}


abstract class Rental{
    String Movie_Title;
    double Imdb;
    String type;
    public Rental(String Movie_Title,double Imdb,String type){
        this.Movie_Title = Movie_Title; // "I am talking about the Object's field, not the parameter."
        this.Imdb = Imdb;
        this.type = type;
    }

    // Cost calculating it is a behaviour so let's create a method for it
    public double calculateCost(){
        double basecost = 4.0;
        if(Imdb>=8){
            return basecost+3.0;
        }else if(Imdb>6.0 && Imdb<8.0){
            return basecost+2.0;
        }else{
            return basecost;
        }
    }

    // The Abstract Method: Forces children to define their own display logic
    public abstract void showdetails();

}

// Now let's use subclass for online and physcial rentals because they have similar properties to parent class with just dditional info
// For physical rental we need a shipping address as an extra property 
class Physical_Rentals extends Rental{
    String shipping_Add;
    public Physical_Rentals(String Movie_Title,double Imdb,String type, String shipping_Add){
        super(Movie_Title, Imdb,type);
        this.shipping_Add = shipping_Add;
    }

    @Override // Overide is kinda of a safety feature through which we can acces a paraent class method without creating a different method by mistake
    public void showdetails(){
        System.out.println("Renting " + type + ": " + Movie_Title);
        System.out.println("Cost: $" + calculateCost());
        System.out.println("Shipping to: " + shipping_Add);
    }
}

// For online rentals we need a quality as an extra property
class Online_Rentals extends Rental{
    int video_quality;
    public Online_Rentals(String Movie_Title,double Imdb,String type, int video_quality){
        super(Movie_Title, Imdb,type);
        this.video_quality = video_quality;
    }
    
    @Override
    public void showdetails(){
        System.out.println("Renting " + type + ": " + Movie_Title);
        System.out.println("Cost: $" + calculateCost());
        System.out.println("Video quality: " + video_quality);
    }

}

public class basecase{
    static ArrayList<Users> userdatabase =new ArrayList<>();

    // Signup method
    public static void signup(){
        Scanner userScanner =new Scanner(System.in);

        System.out.println("Enter your username");
        String newName = userScanner.nextLine();
        for (Users user : userdatabase) {
            if(user.username.equals(newName)){
                System.out.println("User name already exist");

            }
        }
        System.out.println("Enter your password");
        String newPass = userScanner.nextLine();

        userdatabase.add(new Users(newName,newPass));
        System.out.println(">> [SUCCESS] Account created.");
    }
    
    // Login method
    public static boolean login(){
        Scanner userScanner =new Scanner(System.in);
        String name;
        String pass;

        System.out.println("Enter your Username");
        name = userScanner.nextLine();

        System.out.println("Enter your password");
        pass = userScanner.nextLine();

        for (Users user : userdatabase){
            if(user.username.equals(name) && user.password.equals(pass)){
                // user.username.equal() --> checks the database from class user
                System.out.println("Logged in succesfully");
                return true; // The reason we have written true is beacuse while loop shall still run
            }
        }
        System.out.println(">> [ERROR] Wrong credentials."); // Even after looping nothing found then prints this
        return false;
    }

        public static void rentalMenu(Scanner scanner){ // The scanenr parameter ask for the movie chocie
            // Simulation of API Search Results
            String[] apiResults = {"The Dark Knight", "Oldboy", "Parasite"};
            double[] apiRatings = {9.2, 8.4, 8.6}; // Simulated ratings corresponding to titles
            for (int i = 0; i < apiResults.length; i++) {
                System.out.println((i+1) + apiResults[i]);
            }

            System.out.println("Choose your options from 1-3");
            int choice = scanner.nextInt(); // this scanner is used for choice of the api result showed
            scanner.nextInt();

            String selectedMovie = ""; // inilisation super important
            double selectedRating = 0.0;
            if(choice>0 && choice <apiResults.length){
                // pass on this variables name inside
                selectedMovie = apiResults[choice - 1];
                selectedRating = apiRatings[choice - 1];
                Rental myRental; // Polymorphism began from here --> THE SCOPE NEEDS TO BE IN A PLACE WHERE THIS CAN BE ACCESS ACROSSED ONPINE AN DPHYSICAL RENTALS

                System.out.println("select the type of rental you need (online-1, ohyscial-2)");
                int choice_type = scanner.nextInt(); // this scanner is for online and physical renatl type
                scanner.nextInt();
                
                if(choice_type ==1){ // ONLINE
                    System.out.println("You have choosed online rental");
                    System.out.println("Enter the quality you want it in");
                    int quality = scanner.nextInt();
                    myRental = new Online_Rentals(selectedMovie, selectedRating, "Online",quality );
                }else{ // PHYSICAL
                    System.out.println("You have choose physical rental");
                    System.out.println("Your shipping address");
                    String address = scanner.nextLine();
                    myRental = new Physical_Rentals(selectedMovie, selectedRating, "CD/DVD", address);
                }

                // Calling the polymorphic cell
                myRental.showdetails();
            }
        }

    public static void main(String[] args){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Welcome to market plcae");
        boolean ison = true;

        System.out.println("Search for a movie");
        String search_movie = scanner.nextLine(); // This is scanning for our movie

        /*
        Logic for api is that the sreaching begans in the api itself and those related searches are then stored in a list then that list is gone through a for loop
        for now let's just create a normal list for testing
        */


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
                    login();
                    rentalMenu(scanner);
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
