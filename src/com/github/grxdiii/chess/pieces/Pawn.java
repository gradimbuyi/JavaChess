package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Pawn extends Piece {
    protected Boolean enPassant;
    protected Boolean enPassantChecked;

    /**
     * The Pawn constructor calls the Piece constructor in order to create a piece with the type Pawn,
     * giving it the different properties of every chess pieces, including a color and a location
     * on the Board. After calling super, the constructor checks to see if the piece is generated in the
     * correct square - which helps determine whether its first move allows the pawn to move twice forward.
     *
     * @param type the name of the chess piece
     * @param color the color of the chess piece
     * @param locationX the location x of the chess piece (horizontal)
     * @param locationY the location y of the chess piece (vertical)
     */
    public Pawn(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
        if((locationX != 6 && color) || (locationX != 1 && !color)) numMoves = 1;
        enPassant = false;
        enPassantChecked = false;
    }

    /**
     * This helper method allows us to evaluate whether a potential Pawn move is valid. Based on the
     * chess rules, a Pawn can move forward one square at a time depending on whether its destination is
     * occupied. However, during the first move, a pawn is allowed to move two square under the same conditions.
     *
     * It's also important to note that Pawns are only allowed to capture pieces that resides diagonally
     * from them (in the forward directions).
     *
     * This method checks to see if all these conditions are met, and based off its valuation returns true
     * if the move is valid or false if it's not.
     *
     * @param destination_x the piece's potential destination x
     * @param destination_y the piece's potential destination y
     * @return Returns true if move is valid, otherwise returns false
     */
    private Boolean isValidMove(int destination_x, int destination_y) {
        if(destination_x <= 7 && destination_x >= 0 && destination_y <= 7 && destination_y >= 0) {
            return (board[destination_x][destination_y].getPiece() == null && locationY == destination_y) ||
                   (board[destination_x][destination_y].getPiece() != null && locationY != destination_y  &&
                   (board[destination_x][destination_y].getPiece().getColor() != color));
        }
        return false;
    }

    /**
     * This method is in charge checking whether a piece has reached the edge of the board (in the forward
     * direction). Based on chess rules, Pawns are allowed to promote to Queen, Rook, Bishop, or Knight
     * upon reaching the edge of a chess board.
     *
     * @return True if the piece can be promoted, otherwise returns false.
     */
    public Boolean canPromote() {
        return (locationX == 7 && color) || (locationY == 0 && !color);
    }

    /**
     * This method is in charge checking whether a piece has reached the edge of the board (in the forward
     * direction). Based on chess rules, Pawns are allowed to promote to Queen, Rook, Bishop, or Knight upon
     * reaching the edge of a chess board.
     *
     * This method overload canPromote() to allow the user to enter the potential destination of the Pawn
     * and evaluate whether the move could lead to a promotion.
     *
     * @param locationX the piece's potential destination x
     * @param locationY the piece's potential destination y
     * @return True if the piece can be promoted, otherwise returns false.
     */
    public Boolean canPromote(int locationX, int locationY) {
        return (locationX == 7 && color) || (locationY == 0 && !color);
    }

    /**
     * This method allows us to promote the current Pawn to a Queen, Rook, Bishop, or a Knight.
     * It evaluates whether the type parameter includes a valid type and calls the canPromote
     * method to see if the promotion is valid.
     *
     * @param type name of the chess piece
     * @return Returns the promoted piece if promotion is available, otherwise returns the current
     *         piece.
     */
    public Piece promotePawn(String type) {
        Piece piece = null;

        if((locationX == 7 && color) || (locationY == 0 && !color)) {
            // Checks to see if the give type is a legal promotional piece type
            switch (type) {
                case "queen"  -> piece = new Queen(type, color, locationX, locationY);
                case "rook"   -> piece = new Rook(type, color, locationX, locationY);
                case "bishop" -> piece = new Bishop(type, color, locationX, locationY);
                case "knight" -> piece = new Knight(type, color, locationX, locationY);
            }

            // Adds new piece to the chess board
            if(piece != null) {
                piece.numMoves = this.numMoves;
                board[locationX][locationY].addPiece(piece);
                return piece;
            }
        }

        // Returns current piece if all the conditions are not met
        return this;
    }

    // SEE DEFINITION @ Piece.java
    @Override
    public ArrayList<Tile> legalMoves() {
        moves = new ArrayList<>();

        // Goes through the two move types and performs calculations
        for(int move_type = 1; move_type < 3; move_type++) {
            int which_color   = color ? -1 : 1;
            int destination_x = locationX + which_color;

            // Calculate capture moves.
            // Pawns are allowed to capture enemy pieces that reside diagonally from them
            if(move_type % 2 == 0) {
                boolean canAddFirstMove = isValidMove(destination_x, locationY - 1);
                boolean canAddSecondMove = isValidMove(destination_x, locationY + 1);
                if(canAddFirstMove) moves.add(board[destination_x][locationY - 1]);
                if(canAddSecondMove) moves.add(board[destination_x][locationY + 1]);
            }

            // Calculate forward moves.
            // Based on classical Chess, a pawn is only allowed to move forward twice during its first
            // move and one time during its subsequent moves. Unlike other pieces, pawns are not allowed to
            // capture in the direction in which they can move, meaning that their forward movement is
            // dependent on whether the potential legal square is occupied by an enemy or friendly piece
            else if(isValidMove(destination_x, locationY)) {
                moves.add(board[destination_x][locationY]);
                which_color   = color ? -2 : 2;
                destination_x = locationX + which_color;
                if(numMoves == 0 && isValidMove(destination_x, locationY)) {
                    moves.add(board[destination_x][locationY]);
                }
            }
        }

        return moves;
    }
}
