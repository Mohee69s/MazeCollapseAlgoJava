import Maze.MazeLoader;
import Maze.MazeStruct;
import Movement.Movement;

void main() throws IOException {
    MazeStruct mazeStruct = MazeLoader.loadFromFile("test1.txt");
    IO.println(mazeStruct);
//    for (MazeStruct maze : mazeStruct.generateNextStates()){
//        System.out.println(maze);
//        System.out.println('\n');
//    }
    while (true) {
        if(mazeStruct.CheckWinCondition()!=null && mazeStruct.CheckWinCondition()==true){
            System.out.println("\uD83D\uDC51 You've won! \uD83D\uDC51 ");
            break;
        }
        else if(mazeStruct.CheckWinCondition()==null) {
            Scanner scanner = new Scanner(System.in);
            char choice = scanner.next().charAt(0);
            if(choice=='x'){
                for (MazeStruct maze : mazeStruct.generateNextStates()){
                    System.out.println(maze);
                }
            }
            else if (choice == 'w') {
                if (!Movement.CanMoveUp(mazeStruct)) {
                    IO.println("You can't move up");
                    IO.println(mazeStruct);
                } else {
                    mazeStruct = Movement.MoveUp(mazeStruct);
                    IO.println(mazeStruct);
                }
            } else if (choice == 's') {
                if (!Movement.CanMoveDown(mazeStruct)) {
                    IO.println("You can't move down");
                    IO.println(mazeStruct);
                } else {
                    mazeStruct = Movement.MoveDown(mazeStruct);
                    IO.println(mazeStruct);
                }
            } else if (choice == 'a') {
                if (!Movement.CanMoveLeft(mazeStruct)) {
                    IO.println("You can't move left");
                    IO.println(mazeStruct);
                } else {
                    mazeStruct = Movement.MoveLeft(mazeStruct);
                    IO.println(mazeStruct);
                }
            } else if (choice == 'd') {
                if (!Movement.CanMoveRight(mazeStruct)) {
                    IO.println("You can't move right");
                    IO.println(mazeStruct);
                } else {
                    mazeStruct = Movement.MoveRight(mazeStruct);
                    IO.println(mazeStruct);
                }
            }
            else if (choice =='q') {
                break;
            } else {
                System.out.println("Invalid choice");
                System.out.println(mazeStruct);
            }
        }
        else {
            System.out.println("You've Lost!");
            break;
        }
    }


}