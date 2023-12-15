package Pieces;

import Graphic.Board;
import Graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Pawn extends Piece {
    /**
     * The Pawn constructor calls the Piece constructor to create a piece with the type Pawn, giving
     * it the different properties of every chess pieces, including a color and a location on the Board. After calling
     * super, the constructor checks to see if the piece is generated in the correct square - which helps determine whether
     * its first move allows the pawn to move twice forward.
     *
     * @param type the name of the chess piece
     * @param color the color of the chess piece
     * @param locationX the location x of the chess piece (horizontal)
     * @param locationY the location y of the chess piece (vertical)
     */
    public Pawn(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
        if((locationX != 1 && color) || (locationX != 6 && !color)) numMoves = 1;
    }

    /**
     * This helper method allows us to evaluate whether a potential Pawn move is valid. Based on the chess rules,
     * a Pawn can move forward one square at a time depending on whether its destination is occupied. However,
     * during the first move, a pawn is allowed to move two square under the same conditions.
     *
     * It's also important to note that Pawns are only allowed to capture pieces that resides diagonally from them
     * (in the forward directions).
     *
     * This method checks to see if all these conditions are met, and based off its valuation returns true if the
     * move is valid or false if it's not.
     *
     * @param board the chess board
     * @param destination_x the piece's potential destination x
     * @param destination_y the piece's potential destination y
     * @return Returns true if move is valid, otherwise returns false
     */
    private Boolean isValidMove(Tile[][] board, int destination_x, int destination_y) {
        if(destination_x <= 7 && destination_x >= 0 && destination_y <= 7 && destination_y >= 0) {
            return (board[destination_x][destination_y].getPiece() == null && locationY == destination_y) ||
                   (board[destination_x][destination_y].getPiece() != null && locationY != destination_y  &&
                   (board[destination_x][destination_y].getPiece().getColor() != color));
        }
        return false;
    }

    /**
     * This method is in charge checking whether a piece has reached the edge of the board (in the forward direction).
     * Based on chess rules, Pawns are allowed to promote to Queen, Rook, Bishop, or Knight upon reaching the
     * edge of a chess board.
     *
     * @return True if the piece can be promoted, otherwise returns false.
     */
    public Boolean canPromote() {
        return (locationX == 7 && color) || (locationY == 0 && !color);
    }

    /**
     * This method is in charge checking whether a piece has reached the edge of the board (in the forward direction).
     * Based on chess rules, Pawns are allowed to promote to Queen, Rook, Bishop, or Knight upon reaching the
     * edge of a chess board.
     *
     * This method overload canPromote() to allow the user to enter the potential destination of the Pawn and evaluate
     * whether the move could lead to a promotion.
     *
     * @param locationX the piece's potential destination x
     * @param locationY the piece's potential destination y
     * @return True if the piece can be promoted, otherwise returns false.
     */
    public Boolean canPromote(int locationX, int locationY) {
        return (locationX == 7 && color) || (locationY == 0 && !color);
    }

    /**
     * This method allows us to promote the current Pawn to a Queen, Rook, Bishop, or a Knight. It evaluates
     * whether the type parameter includes a valid type and calls the canPromote method to see if the promotion
     * is valid.
     *
     * @param type name of the chess piece
     * @return Returns the promoted piece
     */
    public Piece promotePawn(String type) {
        if(canPromote() && (!type.equals("pawn") || !type.equals("king"))) {
            this.type = type;
            if(this.color) setImage("./images/white_" + this.type + ".png");
            else setImage("./images/black_" + this.type + ".png");
            repaint();
        }

        return this;
    }

    @Override
    public ArrayList<Tile> legalMoves() {
        Tile[][] board = Board.getBoard();
        moves = new ArrayList<>();

        for(int move_type = 1; move_type < 3; move_type++) {
            int which_color = color ? -1 : 1;

            if(move_type % 2 == 0) {
                Tile move = isValidMove(board, locationX + which_color, locationY - 1) ? board[locationX + which_color][locationY - 1] : null;
                Tile second_move = isValidMove(board, locationX + which_color, locationY + 1) ? board[locationX + which_color][locationY + 1] : null;
                if(move != null) moves.add(move);
                if(second_move != null) moves.add(second_move);
            }

            else if(isValidMove(board, locationX + which_color,locationY)) {
                moves.add(board[locationX + which_color][locationY]);
                which_color = color ? -2 : 2;
                if(numMoves == 0 && isValidMove(board, locationX + which_color, locationY)) {
                    moves.add(board[locationX + which_color][locationY]);
                }
            }
        }

        return moves;
    }
}
