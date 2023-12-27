package com.github.grxdiii.chess.graphic;

import com.github.grxdiii.chess.pieces.Bishop;
import com.github.grxdiii.chess.pieces.King;
import com.github.grxdiii.chess.pieces.Knight;
import com.github.grxdiii.chess.pieces.Pawn;
import com.github.grxdiii.chess.pieces.Queen;
import com.github.grxdiii.chess.pieces.Rook;
import com.github.grxdiii.chess.pieces.Piece;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The Board class implements much of the GUI component of the chess game. Within its
 * constructs we define the many properties of a chess board, which includes its
 * frame width and height, as well as the panel in which the game each piece will reside.
 *
 * IMPORTANT CONSTANTS:
 * BOARD_FRAME: A JFrame which holds the GUI of the chess board
 * BOARD_PANEL: A JPanel which holds the chess pieces
 * LAYERED_PANE: Allows the users to drag and drop pieces from one location of the JPanel to another
 * FRAME_WIDTH: The width of the BOARD_FRAME constant
 * FRAME_HEIGHT: The height of the BOARD_FRAME constant
 * PANEL_WIDTH: The width of the BOARD_PANEL constant
 * PANEL_HEIGHT: The height of the BOARD_PANEL constant
 * LIGHT: Color of the light chess tiles
 * DARK: Color of the dark chess tiles
 * GRID_LAYOUT: Defines the relationship between each tile and the BOARD_PANEL constant
 * TILE_MATRiX: A two-dimensional array storing each tiles within the BOARD_PANEL
 * STARTING_POSITION: FEN notation of the initial state of the game
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
    private static Tile source = null;
    private static Tile destination = null;
    /**
     * The constructor for the Board class produces a JFrame with a width of 920 pixels and a
     * height of 948 pixels, as well as a Panel and LayerPane of 920x920 pixels. It takes as
     * parameter an array list of pieces, alongside a mouse listener and a mouse motion listener.
     * The array list allows us to keep track of which pieces are present on the board, whereas
     * both listener allows the user to interact with those pieces.
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

        // Generates each chess tile, saving them in a 2d array named TILE_MATRIX
        for(int locationX = 0; locationX < 8; locationX++) {
            for(int locationY = 0; locationY < 8; locationY++) {
                // Evaluates tile background color
                Color background = locationX % 2 == locationY % 2 ? LIGHT : DARK;

                // Creates new tile
                TILE_MATRIX[locationX][locationY] = new Tile(locationX, locationY);
                TILE_MATRIX[locationX][locationY].setBackground(background);
                BOARD_PANEL.add(TILE_MATRIX[locationX][locationY]);
            }
        }

        generatePieces(STARTING_POSITION);
        BOARD_FRAME.setVisible(true);
    }

    /**
     * This method creates a new instance of the piece class and allocates space for the appropriate
     * piece to be added to the chessboard depending on the given parameters. Each piece has a name,
     * color, and location, all of which are taken into consideration when generating the piece.
     * After generating, the instance of the piece is returned.
     *
     * @param name the type of piece to be generated.
     * @param color the color of the piece to be generated.
     * @param locationX the x-axis location of the piece to be generated.
     * @param locationY the y-axis location of the piece to be generated.
     */
    public static Piece generatePiece(char name, boolean color, int locationX, int locationY) {
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

        // Adds the piece on the board
        TILE_MATRIX[locationX][locationY].addPiece(piece);
        return piece;
    }

    /**
     * This method parses a FEN string and populates the 2D array representing each of the chess
     * tiles with the appropriate chess piece objects according to the given string. This is
     * accomplished by iterating through the FEN string, reading each character and making the
     * appropriate changes to the chess board.
     *
     * Conditions:
     * If the current character is an upper case alphabetic letter, we produce a white piece.
     * Else if the current character is a lower case alphabetic letter, we produce a black piece.
     * Else if the current character is a number, we skip x number of tiles (leaving them empty),
     * where n is the current character.
     * Else if the current character is a slash, we jump to the next row.
     *
     * Disclaimer:
     * This method is public, however, it should only be called during the beginning of the game,
     * or if the user change the state of the game.
     *
     * @param fenNotation given string to be parsed - the FEN string
     */
    public static void generatePieces(String fenNotation) {
        int x = 0; int y = 0; char buffer;

        // Iterates through the 2d array of tiles, and removes pieces. This is done to restart
        // the state of the game
        for(int locationX = 0; locationX < 8; locationX++) {
            for(int locationY = 0; locationY < 8; locationY++) {
                TILE_MATRIX[locationX][locationY].removePiece();
            }
        }

        /* Iterates through the FEN string */
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
            }

            else if(Character.isDigit(buffer)) y += Character.getNumericValue(buffer);
            else if(buffer == '/') { x++; y = 0; }
            else i = fenNotation.length();
        }
    }

    /**
     *
     * @param source_x
     * @param source_y
     * @param destination_x
     * @param destination_y
     */
    public static void updateBoard(int source_x, int source_y, int destination_x, int destination_y) {
        if(source != null && destination != null) {
            Color source_color = source.getLocationX() % 2 == source.getLocationY() % 2 ? LIGHT : DARK;
            Color destination_color = destination.getLocationX() % 2 == destination.getLocationY() % 2 ? LIGHT : DARK;
            source.setBackground(source_color);
            destination.setBackground(destination_color);
        }

        Color type_one = Color.decode("#b5d3ff");
        Color type_two = Color.decode("#4c8aed");
        Tile newSource = TILE_MATRIX[source_x][source_y];
        Tile newDestination = TILE_MATRIX[destination_x][destination_y];
        if(newSource.getBackground() == LIGHT) newSource.setBackground(type_one);
        else if(newSource.getBackground() == DARK) newSource.setBackground(type_two);
        if(newDestination.getBackground() == LIGHT) newDestination.setBackground(type_one);
        else if(newDestination.getBackground() == DARK) newDestination.setBackground(type_two);
        source = newSource; destination = newDestination;
        BOARD_PANEL.repaint();
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
