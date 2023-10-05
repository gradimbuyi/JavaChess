package Main;

import Graphic.Tile;

public class GameUtils {
    public static void printBOARD(Tile[][]board) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j].isOccupied) System.out.print(board[i][j].getPiece().getType() + " ");
                else System.out.print("--- ");
            }
            System.out.println(" ");
        }
    }

    public static void printOCCUPATION(Tile[][]board) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                System.out.print("[" + board[i][j].getLocationX() + " " + board[i][j].getLocationY() + " " + board[i][j].isOccupied + "]\t");
            }
            System.out.println("");
        }
    }

    public static String produceFEN(Tile[][]board) {
        return null;
    }

    public static void printFEN(Tile[][]board) {
        String fenNoation = produceFEN(board);
        System.out.println(fenNoation);
    }

    public static void printPieceandLocation(Tile[][]squares, int locationX, int locationY) {
        System.out.println("X: " + locationX + " Y: " + locationY);
        System.out.println("X Destination: " + squares[locationX - 1][locationY].getLocationX() + " Y Destination: " + squares[locationX -1][locationY].getLocationY());
    }
}
