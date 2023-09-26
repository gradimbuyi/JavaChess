package Graphic;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.Piece;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Board {
    private static JFrame BOARD_FRAME;
    private static JPanel BOARD_PANEL;
    private static final Integer FRAME_WIDTH = 920;
    private static final Integer FRAME_HEIGHT = 948;
    private static final Integer PANEL_WIDTH = 920;
    private static final Integer PANEL_HEIGHT = 920;
    private static final GridLayout GRID_LAYOUT = new GridLayout(8, 8);
    private final Tile[][] TILE_MATRIX;

    /**
     *
     * @param mouseListener
     */
    public Board(MouseListener mouseListener) {
        BOARD_FRAME = new JFrame();
        BOARD_FRAME.setTitle("Chess");
        BOARD_FRAME.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        BOARD_FRAME.setResizable(false);
        BOARD_FRAME.addMouseListener(mouseListener);
        BOARD_FRAME.setLayout(null);
        BOARD_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BOARD_FRAME.setLocationRelativeTo(null);
        BOARD_PANEL = new JPanel(GRID_LAYOUT);
        BOARD_PANEL.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        BOARD_PANEL.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        BOARD_FRAME.getContentPane().add(BOARD_PANEL);
        TILE_MATRIX = new Tile[8][8];
        drawBoard();
    }

    private void drawBoard() {
        for(int locationX = 0; locationX < 8; locationX++) {
            for(int locationY = 0; locationY < 8; locationY++) {
                Tile tile = new Tile(7 - locationX, locationY);

                TILE_MATRIX[7 - locationX][locationY] = tile;
                BOARD_PANEL.add(tile);
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
    private Piece generatePiece(char name, boolean color, int locationX, int locationY) {
        Piece piece = null; name = Character.toLowerCase(name);

        if(name == 'k') piece = new King("king", color, locationX, locationY);
        else if(name == 'q') piece = new Queen("queen", color, locationX, locationY);
        else if(name == 'r') piece = new Rook("rook", color, locationX, locationY);
        else if(name == 'n') piece = new Knight("knight", color, locationX, locationY);
        else if(name == 'b') piece = new Bishop("bishop", color, locationX, locationY);
        else if(name == 'p') piece = new Pawn("pawn", color, locationX, locationY);
        else return null;

        if(color) piece.setImage("./images/white_" + piece.getType() + ".png");
        else piece.setImage("./images/black_" + piece.getType() + ".png");

        TILE_MATRIX[locationY][locationX].setPiece(piece);
        return piece;
    }

    public Piece[][] generatePieces(String fenNotation) {
        Piece[][] pieces = new Piece[8][8];
        Piece piece = null;

        int locationX = 0;
        int locationY = 7;

        for(int i = 0; i < fenNotation.length(); i++) {
            char buffer = fenNotation.charAt(i);

            if(Character.isAlphabetic(buffer) && Character.isLowerCase(buffer)) {
                piece = generatePiece(buffer, false, locationX, locationY);
                pieces[locationX][locationY] = piece;
                locationX++;

            } else if(Character.isAlphabetic(buffer)) {
                piece = generatePiece(buffer, true, locationX, locationY);
                pieces[locationX][locationY] = piece;
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

    public Tile[][] getTiles() {
        return TILE_MATRIX;
    }

    public void setVisible() {
        BOARD_FRAME.setVisible(true);
    }
}
