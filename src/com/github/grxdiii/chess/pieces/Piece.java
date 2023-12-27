package com.github.grxdiii.chess.pieces;

// Personal libraries
import com.github.grxdiii.chess.graphic.Board;
import com.github.grxdiii.chess.graphic.Tile;
import com.github.grxdiii.chess.main.Game;

// Java Libraries
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public abstract class Piece extends JLabel {
    // PROPERTIES OF CHESS PIECES
    protected String type;
    protected final Boolean color;
    protected Integer locationX;
    protected Integer locationY;
    protected Integer numMoves;
    protected ArrayList<Tile> moves;

    // Static variable to access the Chess Board
    protected static Tile[][] board = Board.getBoard();

    /**
     * The Piece constructors takes in four parameters which allows it to take on different properties.
     * Based on the parameters, the piece is generated with a specific name, color, and location
     * within the chess board.
     *
     * @param type the name (type) of the chess piece
     * @param color the color of the chess piece
     * @param locationX the x location where the chess piece is generated
     * @param locationY the y location where the chess piece is generated
     */
    public Piece(String type, boolean color, int locationX, int locationY) {
        // Store the given parameters in their appropriate properties
        this.type = type;
        this.color = color;
        this.locationX = locationX;
        this.locationY = locationY;
        this.numMoves = 0;

        // Builds the name of piece image file based off it type and color
        String imageFileName = color ? "./images/white_" + this.type + ".png":
                                       "./images/black_" + this.type + ".png";

        // Creates an image icon for the piece - which is displayed on the chess board
        Image image = new ImageIcon(imageFileName).getImage();
              image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);

        // Displays the chess piece on a given tile
        this.setIcon(new ImageIcon(image));
    }

    /**
     * This method checks to see if a tile, given a locationX and locationY is occupied. In
     * the case where it is, the method returns true if the tile is occupied by an enemy piece.
     * The method also returns true if the tile is unoccupied.
     *
     * @param locationX the x location of a potential tile
     * @param locationY the y location of a potential tile
     * @return Returns true if the tiles is unoccupied or is occupied by enemy piece, otherwise
     *         returns false
     */
    protected Boolean isTileAvailable(int locationX, int locationY) {
        // returns true if all conditions are met
        if(locationX >= 0 && locationX <= 7 && locationY >= 0 && locationY <= 7) {
            Piece piece = board[locationX][locationY].getPiece();
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
     * @param sourceTile the source where the piece resides
     * @param destinationTile the destination where the piece would like to move
     * @return Returns true if move has lead to a capture, otherwise returns false
     */
    public Boolean movePiece(Tile sourceTile, Tile destinationTile) {
        // Checks to see if all the conditions for castling are met. In the case where they
        // are, the method performCastling() is called and further conditions are checked
        if(this instanceof King && numMoves == 0) {
            int source = sourceTile.getLocationX();
            int destination = destinationTile.getLocationX();

            if(source == destination) {
                int locationY = destinationTile.getLocationY();

                // If castling was successfully performed, the method returns false, otherwise,
                // we continue and calculate the king's legal moves
                if(locationY <= 2 || locationY >= 6) {
                    boolean castle = ((King) this).performCastling(locationY);
                    if(castle) {
                        Game.shouldWhiteMove = !Game.shouldWhiteMove;
                        return false;
                    }
                }
            }
        }

        // This part of the code goes through the array list of the legal moves, comparing
        // each legal move with the destination tile. In the case where a move is equal
        // to the destination tile, this method allows the user to make the move.
        for(Tile move : legalMoves()) {
            if (move == destinationTile) {
                sourceTile.removePiece();
                Game.shouldWhiteMove = !Game.shouldWhiteMove;
                numMoves++;

                // UPDATES THE BOARD COLOR
                int source_x = sourceTile.getLocationX();
                int source_y = sourceTile.getLocationY();
                int destination_x = destinationTile.getLocationX();
                int destination_y = destinationTile.getLocationY();

                Board.updateBoard(source_x, source_y, destination_x, destination_y);

                // adds piece to destination tile
                return destinationTile.addPiece(this);
            }
        }

        // No legal moves were found
        sourceTile.addPiece(this);
        return false;
    }

    // HELPER METHOD FOR PIECE - ALLOWS US TO CHANE THE PIECE LOCATION X and Y
    public void changeLocation(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    // GETTER METHODS
    public Boolean getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public Integer getLocationX() {
        return locationX;
    }

    public Integer getLocationY() {
        return locationY;
    }
}
