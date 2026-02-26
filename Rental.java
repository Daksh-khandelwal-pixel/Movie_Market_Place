class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

// ABSTRACTION: "Rental" is a concept, not a real object.
// We make it abstract so you MUST choose a specific type (Online or Physical).
abstract class Rental {
    String movieTitle;
    double imdbRating;
    String type; // e.g., "DVD", "Stream"

    public Rental(String movieTitle, double imdbRating, String type) {
        this.movieTitle = movieTitle;
        this.imdbRating = imdbRating;
        this.type = type;
    }

    // SHARED LOGIC: All rentals calculate cost the same way based on popularity.
    public double calculateCost() {
        double baseCost = 4.0;
        if (imdbRating >= 8.0) {
            return baseCost + 3.0; // Hit movies cost more
        } else if (imdbRating > 6.0) {
            return baseCost + 2.0; // Average movies
        } else {
            return baseCost; // Flops are cheap
        }
    }

    // THE CONTRACT: Every child class MUST explain how to show its receipt.
    public abstract void showDetails();
}

// INHERITANCE: Physical Rental IS-A Rental, but with an address.
class PhysicalRental extends Rental {
    String shippingAddress;

    public PhysicalRental(String movieTitle, double imdbRating, String type, String shippingAddress) {
        super(movieTitle, imdbRating, type); // Pass core data up to the Parent
        this.shippingAddress = shippingAddress;
    }

    @Override
    public void showDetails() {
        System.out.println("\n--- PHYSICAL RECEIPT ---");
        System.out.println("Movie: " + movieTitle);
        System.out.println("Format: " + type);
        System.out.println("Cost: $" + calculateCost());
        System.out.println("Shipping To: " + shippingAddress);
        System.out.println("------------------------");
    }
}

// INHERITANCE: Online Rental IS-A Rental, but with video quality.
class OnlineRental extends Rental {
    int videoQuality; // e.g., 1080, 720

    public OnlineRental(String movieTitle, double imdbRating, String type, int videoQuality) {
        super(movieTitle, imdbRating, type);
        this.videoQuality = videoQuality;
    }

    @Override
    public void showDetails() {
        System.out.println("\n--- DIGITAL RECEIPT ---");
        System.out.println("Movie: " + movieTitle);
        System.out.println("Format: " + type);
        System.out.println("Cost: $" + calculateCost());
        System.out.println("Quality: " + videoQuality + "p");
        System.out.println("-----------------------");
    }
}
