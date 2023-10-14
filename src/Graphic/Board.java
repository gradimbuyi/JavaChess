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
import javax.swing.JLayeredPane;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The class Board is in charge of presenting the 64 tiles that make up a chess board, as well as
 * the pieces that are within
 *
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Board {
    private static JFrame BOARD_FRAME;
    private static JPanel BOARD_PANEL;
    private static JLayeredPane LAYERED_PANE;
    private static final Integer FRAME_WIDTH = 920;
    private static final Integer FRAME_HEIGHT = 948;
    private static final Integer PANEL_WIDTH = 920;
    private static final Integer PANEL_HEIGHT = 920;
    private static final Color LIGHT = Color.decode("#f0f0f0");
    private static final Color DARK = Color.decode("#5b80ba");
    private static final GridLayout GRID_LAYOUT = new GridLayout(8, 8);
    private static final Tile[][] TILE_MATRIX = new Tile[8][8];
    private static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    /**
     * The constructor for the Board class produces a JFrame with a width of 920 pixels and a height of
     * 948 pixels, as well as a Panel and LayerPane of 920x920 pixels. It takes as parameter an array list
     * of pieces, alongside a mouse listener and a mouse motion listener. The array list allows us to keep
     * track of which pieces are present on the board, whereas both listener allows the user to interact
     * with those pieces.
     *
     * @param mouseListener added to layered pane to allow user to click and drop pieces
     * @param mouseMotionListener added to layered pane to allow user to press and drag pieces
     */
    public Board(MouseListener mouseListener, MouseMotionListener mouseMotionListener) {
        BOARD_FRAME = new JFrame();
        LAYERED_PANE = new JLayeredPane();
        LAYERED_PANE.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        LAYERED_PANE.addMouseListener(mouseListener);
        LAYERED_PANE.addMouseMotionListener(mouseMotionListener);
        BOARD_FRAME.getContentPane().add(LAYERED_PANE);
        BOARD_FRAME.setTitle("Chess");
        BOARD_FRAME.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        BOARD_FRAME.setResizable(false);
        BOARD_FRAME.setLayout(null);
        BOARD_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BOARD_FRAME.setLocationRelativeTo(null);
        BOARD_PANEL = new JPanel(GRID_LAYOUT);
        BOARD_PANEL.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        BOARD_PANEL.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        LAYERED_PANE.add(BOARD_PANEL, JLayeredPane.DEFAULT_LAYER);

        for(int locationX = 0; locationX < 8; locationX++) {
            for(int locationY = 0; locationY < 8; locationY++) {
                TILE_MATRIX[locationX][locationY] = new Tile(locationX, locationY);
                TILE_MATRIX[locationX][locationY].setBackground(locationX % 2 == locationY % 2 ? LIGHT : DARK);
                BOARD_PANEL.add(TILE_MATRIX[locationX][locationY]);
            }
        }

        generatePieces(STARTING_POSITION);
        BOARD_FRAME.setVisible(true);
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
        Piece piece = null;
        name = Character.toLowerCase(name);

        switch (name) {
            case 'k': piece = new King("king", color, locationX, locationY);      break;
            case 'q': piece = new Queen("queen", color, locationX, locationY);    break;
            case 'r': piece = new Rook("rook", color, locationX, locationY);      break;
            case 'n': piece = new Knight("knight", color, locationX, locationY);  break;
            case 'b': piece = new Bishop("bishop", color, locationX, locationY);  break;
            case 'p': piece = new Pawn("pawn", color, locationX, locationY);      break;
            default: return null;
        }

        TILE_MATRIX[locationX][locationY].addPiece(piece);
        return piece;
    }

    public static void generatePieces(String fenNotation) {
        int x = 0; int y = 0; char buffer;

        for(int locationX = 0; locationX < 8; locationX++) {
            for(int locationY = 0; locationY < 8; locationY++) {
                TILE_MATRIX[locationX][locationY].removePiece();
            }
        }

        for(int i = 0; i < fenNotation.length(); i++) {
            buffer = fenNotation.charAt(i);

            if(Character.isAlphabetic(buffer)) {
                if(buffer == 'p') TILE_MATRIX[x][y].addPiece(new Pawn("pawn", false, x, y));
                else if(buffer == 'n') TILE_MATRIX[x][y].addPiece(new Knight("knight", false, x, y));
                else if(buffer == 'b') TILE_MATRIX[x][y].addPiece(new Bishop("bishop", false, x, y));
                else if(buffer == 'r') TILE_MATRIX[x][y].addPiece(new Rook("rook", false, x, y));
                else if(buffer == 'q') TILE_MATRIX[x][y].addPiece(new Queen("queen", false, x, y));
                else if(buffer == 'k') TILE_MATRIX[x][y].addPiece(new King("king", false, x, y));
                else if(buffer == 'P') TILE_MATRIX[x][y].addPiece(new Pawn("pawn", true, x, y));
                else if(buffer == 'N') TILE_MATRIX[x][y].addPiece(new Knight("knight", true, x, y));
                else if(buffer == 'B') TILE_MATRIX[x][y].addPiece(new Bishop("bishop", true, x, y));
                else if(buffer == 'R') TILE_MATRIX[x][y].addPiece(new Rook("rook", true, x, y));
                else if(buffer == 'Q') TILE_MATRIX[x][y].addPiece(new Queen("queen", true, x, y));
                else if(buffer == 'K') TILE_MATRIX[x][y].addPiece(new King("king", true, x, y));  y++;
            }   else if(Character.isDigit(buffer)) y += Character.getNumericValue(buffer);

            else if(buffer == '/') { x++; y = 0; }
            else i = fenNotation.length();
        }
    }

    /** GETTERS AND SETTERS **/
    public static Tile[][] getBoard() {
        return TILE_MATRIX;
    }

    public JPanel getBoardPanel(){
        return BOARD_PANEL;
    }

    public JLayeredPane getLayeredPane() {
        return LAYERED_PANE;
    }
}
