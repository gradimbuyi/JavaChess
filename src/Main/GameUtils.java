package Main;

import Graphic.Board;
import Graphic.Tile;
import java.awt.Color;

public class GameUtils {
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

    /**
     *
     * @param board
     */
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
            }   else System.out.print("- ");    } System.out.println(" ");
        }

        System.out.println(" ");
    }

    /**
     *
     * @param type
     * @param locationX
     * @param locationY
     * @param color
     * @param hasCaptured
     */
    public static void printMoves(String type, int locationX, int locationY, boolean color, boolean hasCaptured) {
        if(color) { NUM_MOVES++; System.out.print(NUM_MOVES + ". "); }

        if(type.equals("pawn") && color && hasCaptured) System.out.print("Px" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("pawn") && !color && hasCaptured) System.out.print("px" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("pawn")) System.out.print(TILE_NAMES[locationX][locationY] + "\t");
        else if(type.equals("knight") && color && hasCaptured) System.out.print("Nx" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("knight") && color) System.out.print("N" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("knight") && hasCaptured) System.out.print("nx" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("knight")) System.out.print("n" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("king") && color && hasCaptured) System.out.print("Kx" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("king") && color) System.out.print("K" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("king") && hasCaptured) System.out.print("kx" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(type.equals("king")) System.out.print("k" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(color && hasCaptured) System.out.print(type.toUpperCase().charAt(0) + "x" + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(color) System.out.print(type.toUpperCase().charAt(0) + TILE_NAMES[locationX][locationY] + "\t\t");
        else if(hasCaptured) System.out.print(type.charAt(0) + "x" + TILE_NAMES[locationX][locationY] + "\t\t");
        else System.out.print(type.charAt(0) + TILE_NAMES[locationX][locationY] + "\t\t");
        if(!color) System.out.println(" ");
    }

    /**
     * Reads
     * @param board
     */
    public static void produceFEN(Tile[][]board) {
        String fenNotation = "";
        System.out.print("Fen Notation: ");

        for(int i = 7; i >= 0; i--) {
            int counter = 0;

            for(int j = 0; j < 8; j++) {
                if (board[i][j].isOccupied) {
                    String type = board[i][j].getPiece().getType();
                    Boolean color = board[i][j].getPiece().getColor();

                         if(counter != 0) fenNotation = fenNotation.concat(Integer.toString(counter));
                         if (type.equals("pawn") && color) fenNotation = fenNotation.concat("P");
                    else if (type.equals("pawn")) fenNotation = fenNotation.concat("p");
                    else if (type.equals("knight") && color) fenNotation = fenNotation.concat("N");
                    else if (type.equals("knight")) fenNotation = fenNotation.concat("n");
                    else if (type.equals("king") && color) fenNotation = fenNotation.concat("K");
                    else if (type.equals("king")) fenNotation = fenNotation.concat("k");
                    else if (color) fenNotation = fenNotation.concat(type.toUpperCase().substring(0, 1));
                    else fenNotation = fenNotation.concat(type.substring(0, 1));

                    counter = 0;
                }   else counter++;
            }

            if(counter != 0) fenNotation = fenNotation.concat(Integer.toString(counter));
            if(i != 0) fenNotation = fenNotation.concat("/");
        }

        System.out.println(fenNotation);
    }

    public static void TestFenNotation(Tile[][] board) throws InterruptedException {
        String [] fenNotations = {
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
                "8/5k2/3p4/1p1Pp2p/pP2Pp1P/P4P1K/8/8",
                "rnb1kbnr/pp1ppQ1p/8/q1p3p1/2B1P3/8/PPPP1PPP/RNB1K1NR b KQkq - 0 4",
                "rnb1k1nr/ppp2ppp/3p4/4N3/4P2P/P2P4/P1P3P1/qNBK1B1R b kq h3 0 9",
                "r3kbnr/1pp1qppp/1p6/8/2P5/3bB3/P4PPP/R2QKBNR b KQkq - 2 12",
                "r4r2/1pp1k1Np/p1n5/3B4/1b5Q/4p3/PPP2PPP/R1B2RK1 b - - 0 18",
                "rn2k2r/pp3ppp/4b3/6N1/4P3/5P2/P3K1PP/R1BQ1B1R b kq - 0 14",
                "1r6/5p2/p7/3k1b2/7B/5r2/2nK4/8 b - - 7 34",
        };

        for(int fen_num = 0; fen_num < 8; fen_num++) {
            Board.generatePieces(fenNotations[fen_num]);
            produceFEN(board);
            Thread.sleep(1500);
        }

    }
}
