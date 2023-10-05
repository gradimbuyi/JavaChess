package Graphic;

import Main.GameUtils;
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
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

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
    private static final GridLayout GRID_LAYOUT = new GridLayout(8, 8);
    private final Tile[][] TILE_MATRIX;

    /**
     * The constructor for the Board class produces a JFrame with a width of 920 pixels and a height of
     * 948 pixels, as well as a Panel and LayerPane of 920x920 pixels. It takes as parameter an array list
     * of pieces, alongside a mouse listener and a mouse motion listener. The array list allows us to keep
     * track of which pieces are present on the board, whereas both listener allows the user to interact
     * with those pieces.
     *
     * The chess board is a 2D array made up of 64 tiles, where each tile has different color than its adjacent tile.
     *
     * @param mouseListener added to layered pane to allow user to click and drop pieces
     * @param mouseMotionListener added to layered pane to allow user to press and drag pieces
     * @param pieces array list of pieces
     */
    public Board(MouseListener mouseListener, MouseMotionListener mouseMotionListener, ArrayList<Piece> pieces) {
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
        TILE_MATRIX = new Tile[8][8];
        for(int i = 0; i < 8; i++) for(int y = 0; y < 8; y++) {
            BOARD_PANEL.add(TILE_MATRIX[i][y] = new Tile(i, y));
        }
        addPieces(pieces);

        GameUtils.printOCCUPATION(TILE_MATRIX);
        GameUtils.printBOARD(TILE_MATRIX);
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

    /**
     * This method is called within the constructor of the Board class. It generates all the chess pieces
     * in their initially position and save each pieces in an array list.
     *
     * @param pieces an array list which keeps track of how many pieces are on the board
     */
    private void addPieces(ArrayList<Piece> pieces) {
        for(int i = 0; i < 8; i++) {
            pieces.add(generatePiece('p', true, 6, i));
            pieces.add(generatePiece('p', false, 1, i));
        }

        for(int i = 0; i < 2; i++) {
            pieces.add(generatePiece('r', i != 0, i * 7, 0));
            pieces.add(generatePiece('n', i != 0, i * 7, 1));
            pieces.add(generatePiece('b', i != 0, i * 7, 2));
            pieces.add(generatePiece('q', i != 0, i * 7, 3));
            pieces.add(generatePiece('k', i != 0, i * 7, 4));
            pieces.add(generatePiece('b', i != 0, i * 7, 5));
            pieces.add(generatePiece('n', i != 0, i * 7, 6));
            pieces.add(generatePiece('r', i != 0, i * 7, 7));
        }
    }

    /** GETTERS AND SETTERS **/
    public Tile[][] getTiles() {
        return TILE_MATRIX;
    }

    public void setVisible() {
        BOARD_FRAME.setVisible(true);
    }

    public JPanel getBoardPanel(){
        return BOARD_PANEL;
    }

    public JLayeredPane getLayeredPane() {
        return LAYERED_PANE;
    }
}
