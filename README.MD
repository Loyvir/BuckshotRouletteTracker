# Buckshot Roulette Tracker

Buckshot Roulette Tracker is a JavaFX-based application designed to track and simulate a roulette-style game involving live and blank rounds. The program provides an intuitive user interface to select live and blank rounds, calculate the probability of outcomes, and display results interactively.

## Features

- **Interactive UI:** A clean and responsive UI built with JavaFX.
- **Live & Blank Rounds Tracking:** Allows users to configure the number of live and blank rounds.
- **Probability Display:** Dynamically calculates and displays the probability of a live round occurring.
- **Round Selection:** Users can interactively select the outcome (live or blank) for each round.

## Project Structure

The project is structured using a Model-View-Controller (MVC) architecture to ensure modularity and maintainability:

- **App:** The main entry point of the application (`com.buckshot.App`).
- **Controller:** Manages the application's logic and interaction between the view and the underlying data (`com.buckshot.Controller.PrimaryController`).
- **View:** Handles the user interface and user interaction (`com.buckshot.View.PrimaryView`).

### Key Classes

#### 1. `App`

- The `App` class initializes the JavaFX application.
- Sets the stage and scene using a `PrimaryController` instance.

#### 2. `PrimaryController`

- Handles user interactions and updates the total rounds based on live and blank selections.
- Communicates with `PrimaryView` to display results.
- Methods include:
  - `build()`: Builds the UI.
  - `handleLiveButtonClick()`: Updates live rounds and recalculates totals.
  - `handleBlankButtonClick()`: Updates blank rounds and recalculates totals.

#### 3. `PrimaryView`

- Responsible for building the user interface.
- Provides options for selecting live and blank rounds.
- Dynamically updates the display as selections are made.
- Key UI components:
  - `selectLiveRounds()`: Allows users to select the number of live rounds.
  - `selectBlankRounds()`: Allows users to select the number of blank rounds.
  - `trackerDiaplay()`: Displays the outcome of rounds with their respective probabilities.

## Installation and Usage

### Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher.
- **JavaFX SDK**: Ensure JavaFX is properly set up in your environment.

### Running the Application

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/buckshot-roulette-tracker.git
   ```
2. Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).
3. Ensure the JavaFX library is added to your project dependencies.
4. Run the `App` class to launch the application.

### Example Workflow

1. Launch the application.
2. Select the number of live and blank rounds using the provided options.
3. View the calculated total rounds and probabilities for live outcomes.
4. Select live or blank for individual rounds, and the probability dynamically updates.

## Technologies Used

- **Java**: Core language for application logic.
- **JavaFX**: Framework for building the user interface.
- **CSS**: Used for styling the application.

## File Structure

```
src
├── com/buckshot
│   └── App.java
├── com/buckshot/Controller
│   └── PrimaryController.java
├── com/buckshot/View
│   └── PrimaryView.java
└── resources
    └── styles.css
```

## Future Enhancements

- Add persistent storage to save and load previous configurations.
- Expand round selection options beyond five rounds.
- Add more advanced statistical analysis of outcomes.

## License

This project is licensed under the [MIT License](LICENSE).

---

## Author

**Your Name**  
[Your Email] | [GitHub Profile] | [LinkedIn Profile]
