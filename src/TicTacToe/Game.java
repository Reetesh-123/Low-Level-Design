package TicTacToe;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import TicTacToe.Model.Board;
import TicTacToe.Model.PieceO;
import TicTacToe.Model.PieceX;
import TicTacToe.Model.Player;

public class Game {
    Deque<Player>list;
    Board board;

    public Game(){
        intialise();
    }
    public void intialise(){
        list=new ArrayDeque<>();
        board=new Board(3);

        Player player1= new Player("Player1",new PieceX());
        Player player2= new Player("Player2",new PieceO());

        list.addLast(player1);
        list.addLast(player2);
    }
    
    public void startGame(){

        while(true){
            board.printBoard();
            System.out.println("");

            Player player=list.getFirst();
            list.removeFirst();
            System.err.print(player.name + " Enter row,col: ");
            Scanner inputScanner=new Scanner(System.in);
            String s=inputScanner.nextLine();
            // inputScanner.close();

            String values[]=s.split(",");
            int row=Integer.valueOf(values[0]);
            int col=Integer.valueOf(values[1]);

            if(board.addPiece(row,col,player.piece)){
                list.addLast(player);
            }
            else
            {
                System.out.println("\nInvalid movie please move again");
                list.addFirst(player);
            }
            if(board.checkGame(row, col,player.piece)){
                board.printBoard();
                System.out.println("\n"+player.name + " is winner");
                break;
            }
            if(board.remainingPieces==0){
                board.printBoard();
                System.out.println("\nDraw");
                break;
            }
            System.out.println("#####################");
        }
    }
}
