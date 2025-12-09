# Java CLI Chess Engine

A command-line chess engine written in **Java** from scratch, powered by the **Minimax algorithm**. Play chess directly in your terminal against an AI opponent with configurable depth and see the AI's decision-making in action.

## Features

* Fully playable chess in the terminal.
* AI opponent using **Minimax algorithm** with optional depth settings.
* Implements **piece-square tables** for better positional evaluation (sourced from popular chess engine resources).
* Move validation and standard chess rules enforced.
* Clean and minimal command-line interface, easy to extend.

## Installation

1. Clone the repository:

```bash
git clone https://github.com/realdanvanth/chess-cli.git
cd chess-cli/src
```

2. Compile the project:

```bash
javac *.java
```

3. Run the game:

```bash
java chess
```

## Usage

* Moves are entered in **algebraic notation** (e.g., `e2 e4`).
* The AI will calculate its move after you make yours.
* The board is displayed in a simple text-based format.

### Example:

```
Enter your move: e2 e4
output:
<img width="287" height="435" alt="image" src="https://github.com/user-attachments/assets/c82bd9ae-7c8c-443d-8ab2-df8c9b3d32f2" />
```

* To quit the game at any time, type `exit`.

## Minimax AI

The engine uses the **Minimax algorithm** for decision-making. Key points:

* Evaluates board states recursively up to a certain **depth**.
* Utilizes **piece-square tables** to assign positional value to each piece — these tables were obtained from popular chess AI resources and adapted for this engine.
* Depth can be adjusted in `Play.java` for stronger or faster AI:

```java
int depth = 3; // Increase for stronger AI, decrease for faster response
```

### Time Complexity

* The worst-case time complexity of Minimax is **O(b^d)**, where:

  * `b` = average branching factor (approx. 35 for chess)
  * `d` = search depth
* This engine runs in reasonable time for depths 3-4, with higher depths requiring optimizations like **alpha-beta pruning**.

## Project Structure

```
├── Play.java       # Entry point of the program
├── Chess.java      # Represents the chessboard and game state
├── Item.java       # Represents a move
├── Piece.java      # Base class for chess pieces and evaluation logic
```

## Future Improvements

* Implement **alpha-beta pruning** for faster AI computations.
* Support **castling, en passant, and pawn promotion**.
* Add **save/load game functionality**.
* Improve CLI with colors, ASCII graphics, or board snapshots.
* Potentially add **opening book** support to mimic stronger human play.

## Contributing

Contributions are welcome! Fork the repository and submit a pull request with your enhancements.

## License

This project is licensed under the **MIT License**.
