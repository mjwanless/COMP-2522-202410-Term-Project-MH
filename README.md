# Dice Adventure 2

**Dice Adventure 2** is a turn-based dice combat auto-battler game developed as a term project for COMP 2522 by Malcolm and Heraldo.

## Requirements

- JDK 18
- libGDX
- Gradle

## Setup and Running the Game

1. **Clone the Repository**: Use the following command to clone the repository:

   ```
   git clone https://github.com/mjwanless/COMP-2522-202410-Term-Project-MH.git
   ```

2. **Open in IntelliJ IDEA**: Open the cloned project in IntelliJ IDEA.

3. **Build the Project**: Click on the Gradle icon (an elephant) on the right-hand side of IntelliJ IDEA.

4. **Run the Game**: Navigate to the `other` task under Gradle and select `run` to start the game.

## Gameplay

- **Starting a New Game**: Launch the game and select "New Game" to begin your adventure.
- **Class Selection**: Choose two classes for your characters. Currently, all classes have identical stats, with health being the primary attribute.
- **Combat**: Engage in battles across various arenas against two enemies. Roll dice to perform attacks; enemies will counterattack. You have two additional attacks per combat to gain an advantage.

## Project Structure

The repository is organized as follows:

- **assets/**: Contains game assets such as images and sounds.
- **checkstyle/**: Includes configuration files for code style checks.
- **core/**: Houses the core game logic and source code.
- **desktop/**: Contains the desktop launcher for the game.
- **gradle/**: Includes Gradle wrapper files for build automation.
- **Mindmap.pdf**: A PDF document outlining the game's mindmap and design.
- **UML.pdf**: A PDF containing the Unified Modeling Language diagrams for the project.
- **build.gradle**: The Gradle build script for the project.
- **gradle.properties**: Properties file for Gradle configuration.
- **gradlew** and **gradlew.bat**: Scripts to run Gradle tasks on Unix and Windows systems, respectively.
- **settings.gradle**: Settings file for Gradle project configuration.

## Additional Resources

- **Mindmap**: Refer to `Mindmap.pdf` for a visual representation of the game's design and structure.
- **UML Diagrams**: Consult `UML.pdf` for detailed UML diagrams illustrating the system architecture.

