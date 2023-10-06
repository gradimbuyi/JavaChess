package Main;

import Graphic.Board;
import Graphic.Tile;
import Pieces.Piece;

import java.util.Objects;

public class GameUtils {
    public static void printBOARD(Tile[][]board) {
        for(int i = 0; i < 8; i++) { for(int j = 0; j < 8; j++) {
                if(board[i][j].isOccupied) {
                    if(Objects.equals(board[i][j].getPiece().getType(), "knight")) System.out.print("n ");
                    else if(Objects.equals(board[i][j].getPiece().getType(), "king")) System.out.print("k ");
                    else System.out.print(board[i][j].getPiece().getType().charAt(0) + " ");
                }
                else System.out.print("- ");
            }   System.out.println(" ");
        }
    }

    public static void printBOARD(Tile[][]board, int x) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j].isOccupied) System.out.print(board[i][j].getPiece().getType() + " ");
                else System.out.print("--- ");
            }
            System.out.println(" ");
        }
    }

    public static void printOCCUPATION(Tile[][]board) {
        for(int i = 0; i < 8; i++) { for(int j = 0; j < 8; j++) {
                System.out.print("[" + board[i][j].getLocationX() + " " + board[i][j].getLocationY() + " " + board[i][j].isOccupied + "]\t");
            }
            System.out.println("");
        }
    }

    public static String produceFEN(Tile[][]board) {
        return new String();
    }

    public static void printFEN(Tile[][]board) {
        String fenNotation = produceFEN(board);
        System.out.println(fenNotation);
    }

    public static void printPieceAndLocation(Tile[][]squares, int locationX, int locationY) {
        System.out.println("X: " + locationX + " Y: " + locationY);
        System.out.println("X Destination: " + squares[locationX][locationY].getLocationX() + " Y Destination: " + squares[locationX][locationY].getLocationY());
    }
}
