package com.github.grxdiii.chess.pieces;

// Personal libraries
import com.github.grxdiii.chess.graphic.Board;
import com.github.grxdiii.chess.graphic.Tile;
import com.github.grxdiii.chess.main.Game;

// Java Libraries
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public abstract class Piece extends JLabel {
    // PROPERTIES OF CHESS PIECES
    protected String type;
    protected final Boolean color;
    protected Integer rank;
    protected Integer file;
    protected Integer numMoves;
    protected ArrayList<Tile> moves;
    protected static Tile[][] board = Board.getBoard();
    protected static final String[] PIECE_TYPE = {"pawn", "knight", "bishop", "rook", "queen", "king"};
    protected static final Character[] PIECE_TYPE_LOWER_CHAR = {'p', 'n', 'b', 'r', 'q', 'k'};
    protected static final Character[] PIECE_TYPE_UPPER_CHAR = {'P', 'N', 'B', 'R', 'Q', 'K'};

    /**
     * The Piece constructors takes in four parameters which allows it to take on different properties.
     * Based on the parameters, the piece is generated with a specific name, color, and location
     * within the chess board.
     *
     * @param type the name (type) of the chess piece
     * @param color the color of the chess piece
     * @param rank the x location where the chess piece is generated
     * @param file the y location where the chess piece is generated
     */
    public Piece(String type, boolean color, int rank, int file) {
        // Store the given parameters in their appropriate properties
        this.type = type;
        this.color = color;
        this.rank = rank;
        this.file = file;
        this.numMoves = 0;

        // Builds the name of piece image file based off it type and color
        String image_file_name = color ? "./images/white_" + this.type + ".png":
                                         "./images/black_" + this.type + ".png";

        // Creates an image icon for the piece - which is displayed on the chess board
        Image image = new ImageIcon(image_file_name).getImage();
              image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);

        // Displays the chess piece on a given tile
        this.setIcon(new ImageIcon(image));
    }

    /**
     * This method checks to see if a tile, given a rank and file is occupied. In
     * the case where it is, the method returns true if the tile is occupied by an enemy piece.
     * The method also returns true if the tile is unoccupied.
     *
     * @param rank the x location of a potential tile
     * @param file the y location of a potential tile
     * @return Returns true if the tiles is unoccupied or is occupied by enemy piece, otherwise
     *         returns false
     */
    protected Boolean isTileAvailable(int rank, int file) {
        // returns true if all conditions are met
        if(rank >= 0 && rank <= 7 && file >= 0 && file <= 7) {
            Piece piece = board[rank][file].getPiece();
            return piece == null || piece.getColor() != color;
        }

        // otherwise returns false
        return false;
    }

    /**
     * This abstract method is defined by every class that extends the Piece class. Every chess
     * piece has a way in which it moves and the legalMoves() method is in charge of
     * making sure that the moves made by the players are legal.
     *
     * @return Returns array list of legal moves of a given chess piece
     */
    public abstract ArrayList<Tile> legalMoves();

    /**
     * This method calculates the legal moves of a given piece, and move the piece if the given
     * destination tile is one of the
     *
     * @param source the source where the piece resides
     * @param destination the destination where the piece would like to move
     * @return Returns true if move has lead to a capture, otherwise returns false
     */
    public Boolean movePiece(Tile source, Tile destination) {
        // Helps show moves
        if(source == destination) {
            if(moves == null) moves = legalMoves();

            source.addPiece(this);
            return false;
        }

        // Checks to see if all the conditions for castling are met. In the case where they
        // are, the method performCastling() is called and further conditions are checked
        if(this instanceof King && numMoves == 0) {
            int source_rank      = source.getRank();
            int destination_rank = destination.getRank();

            if(source_rank == destination_rank) {
                int file = destination.getFile();

                // If castling was successfully performed, the method returns false, otherwise,
                // we continue and calculate the king's legal moves
                if(file <= 2 || file >= 6) {
                    boolean hasCastled = ((King) this).performCastling(file);

                    if(hasCastled) {
                        Game.shouldWhiteMove = !Game.shouldWhiteMove;
                        moves = null;

                        // No pieces were captured
                        return false;
                    }
                }
            }
        }

        if(moves == null) moves = legalMoves();

        // This part of the code goes through the array list of the legal moves, comparing
        // each legal move with the destination tile. In the case where a move is equal
        // to the destination tile, this method allows the user to make the move.
        for(Tile move : moves) {
            if (move == destination) {
                source.removePiece();
                Game.shouldWhiteMove = !Game.shouldWhiteMove;
                numMoves++;

                // UPDATES THE BOARD COLOR
                int source_rank = source.getRank();
                int source_file = source.getFile();
                int destination_rank = destination.getRank();
                int destination_file = destination.getFile();
                Board.updateBoard(source_rank, source_file, destination_rank, destination_file);

                // adds piece to destination tile
                moves = null;
                return destination.addPiece(this);
            }
        }

        // No legal moves were found
        source.addPiece(this);
        return false;
    }


    // HELPER METHOD FOR PIECE - ALLOWS US TO CHANE THE PIECE LOCATION X and Y
    public void changeLocation(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    // GETTER METHODS
    public Boolean getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getFile() {
        return file;
    }

    public ArrayList<Tile> getMoves() {
        return moves;
    }
}
