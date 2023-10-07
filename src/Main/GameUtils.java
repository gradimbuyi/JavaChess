package Main;

import Graphic.Tile;
import Pieces.Piece;

import java.awt.Color;

public class GameUtils {
    public static final Color LIGHT = Color.decode("#f0f0f0");
    public static final Color DARK = Color.decode("#5b80ba");
    private static Integer NUM_MOVES = 0;
    private static final String[][] TILE_NAMES = {
            {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"},
            {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
            {"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
            {"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
            {"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
            {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
            {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
            {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"},
    };

    public static void printBOARD(Tile[][]board) {
        for(int i = 0; i < 8; i++) {    for(int j = 0; j < 8; j++) {
            if(board[i][j].isOccupied) {
                String type = board[i][j].getPiece().getType();
                Boolean color = board[i][j].getPiece().getColor();

                if(type.equals("knight") && color) System.out.print("n ");
                else if(type.equals("knight")) System.out.print("N ");
                else if(type.equals("king") && color) System.out.print("k ");
                else if(type.equals("king")) System.out.print("K ");
                else if(color) System.out.print(type.charAt(0) + " ");
                else System.out.print(type.toUpperCase().charAt(0) + " ");
            }   else System.out.print("- ");    }   System.out.println(" ");
        }

        System.out.println(" ");
    }


    public static void printMoves(String type, int locationX, int locationY, boolean color, boolean hasCaptured) {
        if(color) { NUM_MOVES++; System.out.print(NUM_MOVES + ". "); }

        if(type.equals("pawn")) System.out.print(TILE_NAMES[locationX][locationY] + "\t");

        // knight goes L
        else if(type.equals("knight") && color && hasCaptured) System.out.print("nx" + TILE_NAMES[locationX][locationY] + "\t");
        else if(type.equals("knight") && color) System.out.print("n" + TILE_NAMES[locationX][locationY] + "\t");
        else if(type.equals("knight") && hasCaptured) System.out.print("Nx" + TILE_NAMES[locationX][locationY] + "\t");
        else if(type.equals("knight")) System.out.print("N" + TILE_NAMES[locationX][locationY] + "\t");

        // king goes []
        else if(type.equals("king") && color && hasCaptured) System.out.print("kx" + TILE_NAMES[locationX][locationY] + "\t");
        else if(type.equals("king") && color) System.out.print("k" + TILE_NAMES[locationX][locationY] + "\t");
        else if(type.equals("king") && hasCaptured) System.out.print("Kx" + TILE_NAMES[locationX][locationY] + "\t");
        else if(type.equals("king")) System.out.print("K" + TILE_NAMES[locationX][locationY] + "\t");

        // other piece goes brrrrrrr
        else if(color && hasCaptured) System.out.print(type.charAt(0) + "x" + TILE_NAMES[locationX][locationY] + "\t");
        else if(color) System.out.print(type.charAt(0) + TILE_NAMES[locationX][locationY] + "\t");
        else if(hasCaptured) System.out.print(type.toUpperCase().charAt(0) + "x" + TILE_NAMES[locationX][locationY] + "\t");
        else System.out.print(type.toUpperCase().charAt(0) + TILE_NAMES[locationX][locationY] + "\t");

        if(!color) System.out.println(" ");

    }

    public static void produceFEN(Tile[][]board) {
        String fenNotation = new String();

        System.out.print("Fen Notation: ");

        for(int i = 7; i >= 0; i--) {
            int counter = 0;

            for(int j = 0; j < 8; j++) {
                if (board[i][j].isOccupied) {
                    String type = board[i][j].getPiece().getType();
                    Boolean color = board[i][j].getPiece().getColor();

                    if(counter != 0) fenNotation = fenNotation.concat(Integer.toString(counter));

                    if (type.equals("pawn") && color) fenNotation = fenNotation.concat("p");
                    else if (type.equals("pawn")) fenNotation = fenNotation.concat("P");
                    else if (type.equals("knight") && color) fenNotation = fenNotation.concat("n");
                    else if (type.equals("knight")) fenNotation = fenNotation.concat("N");
                    else if (type.equals("king") && color) fenNotation = fenNotation.concat("k");
                    else if (type.equals("king")) fenNotation = fenNotation.concat("K");
                    else if (color) fenNotation = fenNotation.concat(type.substring(0, 1));
                    else fenNotation = fenNotation.concat(type.toUpperCase().substring(0, 1));

                    counter = 0;

                }
                else counter++;
            }

            if(counter != 0) fenNotation = fenNotation.concat(Integer.toString(counter));
            if(i != 0) fenNotation = fenNotation.concat("/");
        }

        System.out.println(fenNotation);
    }

}
