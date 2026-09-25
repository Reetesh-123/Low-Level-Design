package InterviewQuestions.TicTacToe.Model;

import java.util.HashMap;

import javafx.util.Pair;

public class Board {
    int size;
    PlayingPiece board[][];

    HashMap<Pair<Integer, PieceType>, Integer> rowSum = new HashMap<>();
    HashMap<Pair<Integer, PieceType>, Integer> colSum = new HashMap<>();
    HashMap<Pair<Integer, PieceType>, Integer> leftDiagonal = new HashMap<>();
    HashMap<Pair<Integer, PieceType>, Integer> rightDiagonal = new HashMap<>();
    
   public int remainingPieces=0;
    public Board(int size){
        this.size=size;
        board = new PlayingPiece[size][size];
        remainingPieces=size*size;
    }

    public boolean addPiece(Integer row,Integer col,PlayingPiece piece){
        
        if(board[row][col]!=null)
        return false;

        board[row][col]=piece;
        Pair<Integer,PieceType> pair1= new Pair<Integer,PieceType>(row, piece.type);
        Pair<Integer,PieceType> pair2= new Pair<Integer,PieceType>(col, piece.type);
        Pair<Integer,PieceType> pair3= new Pair<Integer,PieceType>(row+col, piece.type);
        Pair<Integer,PieceType> pair4= new Pair<Integer,PieceType>(row-col, piece.type);
        
        Integer newval1=rowSum.getOrDefault(pair1, 0)+1;
        Integer newval2=colSum.getOrDefault(pair2, 0)+1;
        Integer newval3=leftDiagonal.getOrDefault(pair3, 0)+1;
        Integer newval4=rightDiagonal.getOrDefault(pair4, 0)+1;

        rowSum.put(pair1,newval1);
        colSum.put(pair2,newval2);
        leftDiagonal.put(pair3,newval3);
        rightDiagonal.put(pair4,newval4);


        remainingPieces--;
        return true;
    }
    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
            {
                if(board[i][j]==null)
                System.out.print("null ");
                else
                System.out.print(board[i][j].type +" ");
            }
            System.out.println("");
        }
    }
    public boolean checkGame(Integer row,Integer col,PlayingPiece piece){
        Pair<Integer,PieceType> pair1= new Pair<Integer,PieceType>(row, piece.type);
        Pair<Integer,PieceType> pair2= new Pair<Integer,PieceType>(col, piece.type);
        Pair<Integer,PieceType> pair3= new Pair<Integer,PieceType>(row+col, piece.type);
        Pair<Integer,PieceType> pair4= new Pair<Integer,PieceType>(row-col, piece.type);

        if(rowSum.getOrDefault(pair1, 0)== size || colSum.getOrDefault(pair2,0)==size || leftDiagonal.getOrDefault(pair3,0)==size || rightDiagonal.getOrDefault(pair4,0)==size)
        {
            return true;
        }
        else
        return false;
    }
}
