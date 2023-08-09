package Graphic;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.Piece;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Board {
    private static JPanel panel;
    private static JPanel[][] squares;
    private static final Color LIGHT = Color.decode("#f0f0f0");
    private static final Color DARK = Color.decode("#5b80ba");

    /**
     * Constructor for the board class. A board in this instance is the panel in which all
     * the chessboard class
     * @param width the width of the panel.
     * @param height the height of the panel.
     */
    public Board(int width, int height) {
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(new GridLayout(8, 8));
        panel.setBounds(0,0, width, height);
        drawBoard();
    }

    /**
     *
     */
    private void drawBoard() {
        squares = new JPanel[8][8];

        for(int locationX = 0; locationX < 8; locationX++) {
            for(int locationY = 0; locationY < 8; locationY++) {
                JPanel square = new JPanel(new BorderLayout());

                if((locationX % 2) == (locationY % 2)) square.setBackground(LIGHT);
                else square.setBackground(DARK);

                squares[7 - locationX][locationY] = square;
                panel.add(square);
            }
        }
    }

    /**
     * This method creates a new instance of the piece class and allocates space for the appropriate piece
     * to be added to the chessboard depending on the given parameters. Each piece has a name, color, and
     * location, all of which are taken into consideration when generating the piece. After generating,
     * the instance of the piece is returned.
     *
     * @param name the type of piece to be generated.
     * @param color the color of the piece to be generated.
     * @param locationX the x-axis location of the piece to be generated.
     * @param locationY the y-axis location of the piece to be generated.
     * @return Returns the generated piece.
     */
    private static Piece generatePiece(char name, boolean color, int locationX, int locationY) {
        Piece piece = null;
        name = Character.toLowerCase(name);

        if(name == 'k') piece = new King("king", color, locationX, locationY);
        else if(name == 'q') piece = new Queen("queen", color, locationX, locationY);
        else if(name == 'r') piece = new Rook("rook", color, locationX, locationY);
        else if(name == 'n') piece = new Knight("knight", color, locationX, locationY);
        else if(name == 'b') piece = new Bishop("bishop", color, locationX, locationY);
        else if(name == 'p') piece = new Pawn("pawn", color, locationX, locationY);
        else return null;

        if(color == true) piece.setImage("./images/white_" + piece.getType() + ".png");
        else piece.setImage("./images/black_" + piece.getType() + ".png");

        squares[locationY][locationX].add(piece.getImage());

        return piece;
    }

    /**
     *
     * @param fenNotation
     * @return
     */
    public static Piece[][] generatePieces(String fenNotation) {
        char buffer; int locationX = 0; int locationY = 7;
        Piece[][] pieces = new Piece[8][8];

        for(int i = 0; i < fenNotation.length(); i++) {
            buffer = fenNotation.charAt(i);

            if(Character.isAlphabetic(buffer) && Character.isLowerCase(buffer)) {
                pieces[locationX][locationY] = generatePiece(buffer, false, locationX, locationY);
                locationX++;

            } else if(Character.isAlphabetic(buffer)) {
                pieces[locationX][locationY] = generatePiece(buffer, true, locationX, locationY);
                locationX++;

            } else if(Character.isDigit(buffer)) {
                locationX += Character.getNumericValue(buffer);

            } else if(buffer == '/') {
                locationX = 0;
                locationY--;

            } else {
                i = fenNotation.length();
            }
        }

        return pieces;
    }

    /**
     *
     * @return
     */
    public JPanel getPanel() {
        return panel;
    }
}
