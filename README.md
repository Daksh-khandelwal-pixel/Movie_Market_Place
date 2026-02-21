🎬 Movie Marketplace
A feature-rich Console-Based Movie Commerce Platform built entirely in Core Java.
This project simulates a real-world entertainment ecosystem where users can:

rent movies
subscribe to bundled services
participate in movie production roles
buy and sell movies within the platform

It was designed as a hands-on environment to master advanced OOP concepts, system architecture, and real-world platform modeling.

🚀 System Overview
Movie Marketplace models a multi-service entertainment economy built around three core operational layers:
🎟 Rental System
Temporary access to movies through physical or online delivery.
📦 Subscription System
Bundled and synchronized service plans offering grouped entertainment access.
🎥 Buy / Sell & Production System
Users can participate in movie creation and ownership workflows by selecting creative or production roles (e.g., producer, director, actor, music director).
This combines content consumption + membership models + production participation into a single unified simulation.

✨ Key Features
🌐 Live Movie Data
Integrates with the OMDb API using Java 11 HttpClient to fetch real-time movie titles, ratings, and details.
🔐 User Authentication
Secure signup and login system with persistent storage using Java serialization.
💰 Smart Pricing Engine
Dynamic cost calculation based on IMDb ratings.
🔄 Polymorphic Rentals

Physical rentals with inventory and shipping logic
Online rentals with streaming quality control

📦 Subscription Bundles
Grouped service plans with expandable feature architecture and synchronization concepts.
🎬 Role-Based Movie Creation
Users participate in production by selecting industry roles that trigger different system workflows.
🧱 Modular Architecture
Strict separation of responsibilities using encapsulation, abstraction, and inheritance.

🛠️ Tech Stack

Language: Java (JDK 11+)
Networking: java.net.http.HttpClient
Data Persistence: Java Serialization
API Integration: OMDb REST API
Architecture: Object-Oriented Programming


🧩 Concepts Applied
Object-Oriented Design

Encapsulation of user and movie data
Inheritance across system components
Polymorphic rental behavior
Abstraction of business logic

System Modeling

Multi-service platform simulation
Role-based workflow triggering
Economic interaction modeling

Data & Networking

REST API consumption
Manual JSON parsing
File I/O persistence


📂 Project Structure
Movie_Market_Place/
│
├── MarketplaceApp.java
├── Subscription.java
├── Concept_Rental.java
├── basecase.java
├── Why_How_Explaining/
├── README.md
└── .gitignore


▶️ How to Run
Clone repository:
git clone https://github.com/YourUsername/Movie-Marketplace.git

Compile:
javac Movie_Market_Place/*.java

Run:
java Movie_Market_Place.MarketplaceApp


🔮 Future Roadmap

Advanced subscription tiers
GUI interface (JavaFX / Swing)
Admin dashboard for inventory control
Recommendation engine based on user behavior
Database integration
Payment simulation
Multi-user environment
Advanced production pipeline modeling


🎯 Purpose of the Project
This project serves as a structured environment for learning and demonstrating:

Object-oriented system design
Real-world platform simulation
Modular architecture thinking
Software scalability concepts

It functions as both:

a learning laboratory
a functional system prototype


👤 Author
Daksh Khandelwal

