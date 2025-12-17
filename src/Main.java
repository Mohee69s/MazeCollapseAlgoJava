import Algorithm.AStarSolver;
import Algorithm.MazeSolver;
import Maze.MazeLoader;
import Maze.MazeStruct;
import Movement.Movement;
import java.util.List;

void main() throws IOException {
    MazeStruct mazeStruct = MazeLoader.loadFromFile("anotherround2.txt");
    IO.println("Initial Maze:");
    IO.println(mazeStruct);



    AStarSolver solver = new AStarSolver();
    MazeStruct goal = solver.aStarSearch(mazeStruct);

    if (goal != null) {
        List<MazeStruct> path = solver.reconstructPath(goal);

        System.out.println("\n A* Path Taken ===");
        for (int i = 0; i < path.size(); i++) {
            System.out.println("Step " + i + ":");
            System.out.println(path.get(i));
            System.out.println();
        }
        System.out.println(" Final Cost (A*): " + goal.player.currentCost + " ===");
        System.out.println("Total generated states: " + solver.getGeneratedStates());
        System.out.println("Total visited states: " + solver.getVisitedStates());
    } else {
        System.out.println("No solution found by A*!");
    }

}

//
//   MazeSolver mazeSolver = new MazeSolver();
//   MazeStruct solution = mazeSolver.uniformCostSearch(mazeStruct);
//
//   if (solution != null) {
//       List<MazeStruct> path = mazeSolver.reconstructPath(solution);
//
//       System.out.println("\n=== Path Taken ===");
//       for (int i = 0; i < path.size(); i++) {
//           System.out.println("Step " + i + ":");
//           System.out.println(path.get(i));
//           System.out.println();
//       }
//
//       System.out.println("=== Final Cost: " + (solution.player.currentCost-1) + " ===");
//   } else {
//       System.out.println("No solution found!");
//   }


// System.out.println("\n=== Maze Solver ===");
// System.out.println("(p) Play manually");
// System.out.print("Choose: ");

// Scanner menuScanner = new Scanner(System.in);
// char menuChoice = menuScanner.next().charAt(0);
//     while (true) {

//         if (mazeStruct.CheckWinCondition() != null && mazeStruct.CheckWinCondition() == true) {
//             System.out.println("\uD83D\uDC51 You've won! \uD83D\uDC51 ");
//             break;
//         } else if (mazeStruct.CheckWinCondition() == null) {
//             Scanner scanner = new Scanner(System.in);
//             char choice = scanner.next().charAt(0);
//             if (choice == 'x') {
//                 for (MazeStruct maze : mazeStruct.generateNextStates()) {
//                     System.out.println(maze);
//                 }
//             } else if (choice == 'w') {
//                 if (!Movement.CanMoveUp(mazeStruct)) {
//                     IO.println("You can't move up");
//                     IO.println(mazeStruct);
//                 } else {
//                     mazeStruct = Movement.MoveUp(mazeStruct);
//                     IO.println(mazeStruct);
//                 }
//             } else if (choice == 's') {
//                 if (!Movement.CanMoveDown(mazeStruct)) {
//                     IO.println("You can't move down");
//                     IO.println(mazeStruct);
//                 } else {
//                     mazeStruct = Movement.MoveDown(mazeStruct);
//                     IO.println(mazeStruct);
//                 }
//             } else if (choice == 'a') {
//                 if (!Movement.CanMoveLeft(mazeStruct)) {
//                     IO.println("You can't move left");
//                     IO.println(mazeStruct);
//                 } else {
//                     mazeStruct = Movement.MoveLeft(mazeStruct);
//                     IO.println(mazeStruct);
//                 }
//             } else if (choice == 'd') {
//                 if (!Movement.CanMoveRight(mazeStruct)) {
//                     IO.println("You can't move right");
//                     IO.println(mazeStruct);
//                 } else {
//                     mazeStruct = Movement.MoveRight(mazeStruct);
//                     IO.println(mazeStruct);
//                 }
//             } else if (choice == 'q') {
//                 break;
//             } else {
//                 System.out.println("Invalid choice");
//                 System.out.println(mazeStruct);
//             }
//         } else {
//             System.out.println("You've Lost!");
//             break;
//         }
//     }
