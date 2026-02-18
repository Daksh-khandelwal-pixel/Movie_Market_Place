# Movie-Marketplace

# 🎬 Movie Marketplace

A feature-rich **Console-Based Movie Rental System** built entirely in **Core Java**. 

This project simulates a real-world e-commerce platform where users can sign up, search for real movies (using live API data), and rent them in different formats. It was designed as a "Gym" to master advanced Object-Oriented Programming (OOP) concepts and software architecture.

## 🚀 Key Features

* **Live Movie Data:** Integrates with the **OMDb API** using Java 11 `HttpClient` to fetch real-time movie titles, ratings, and details.
* **User Authentication:** Secure Signup and Login system with data persistence (users are saved to local files so data isn't lost on exit).
* **Smart Pricing Engine:** Dynamic cost calculation based on IMDb ratings (e.g., highly-rated movies cost more).
* **Polymorphic Rentals:**
    * **Physical Rentals:** Handles shipping addresses and physical inventory logic.
    * **Online Rentals:** Handles video quality (720p/1080p/4K) and streaming logic.
* **Clean Architecture:** strictly separated logic using **Encapsulation**, **Abstraction**, and **Inheritance**.

## 🛠️ Tech Stack

* **Language:** Java (JDK 11+)
* **Networking:** `java.net.http.HttpClient` (No external libraries used)
* **Data Persistence:** Java Serialization (`java.io.Serializable`)
* **Architecture:** OOP (Object-Oriented Programming)

## 🧩 Concepts Applied

This project serves as a practical implementation of:
* **Polymorphism:** Handling different rental types (`Physical` vs `Online`) through a unified `Rental` abstract class.
* **Abstraction:** Enforcing business logic contracts (e.g., every rental *must* have a receipt method).
* **Encapsulation:** Protecting user data through private fields and getters/setters.
* **File I/O:** Saving and loading the user database to/from disk.
* **REST API Consumption:** Parsing JSON responses manually to understand data structures.

## 🏃‍♂️ How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YourUsername/Movie-Marketplace.git](https://github.com/YourUsername/Movie-Marketplace.git)
    ```
2.  **Compile the code:**
    ```bash
    javac Movie_Market_Place/*.java
    ```
3.  **Run the application:**
    ```bash
    java Movie_Market_Place.MarketplaceApp
    ```

## 🔮 Future Roadmap

* [ ] **Subscription Models:** Implementing tiered access (Basic vs. Premium) for exclusive content.
* [ ] **Admin Dashboard:** Special login for managing inventory.
* [ ] **Recommendation Engine:** Suggesting movies based on previous rental history.

---
*Built with ☕ and Java by [Your Name]*
