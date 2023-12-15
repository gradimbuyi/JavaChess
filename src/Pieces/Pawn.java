package Pieces;

import Graphic.Board;
import Graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Pawn extends Piece {
    public Pawn(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    private Boolean isValidMove(Tile[][] board, int destination_x, int destination_y) {
        if(destination_x <= 7 && destination_x >= 0 && destination_y <= 7 && destination_y >= 0) {
            return (board[destination_x][destination_y].getPiece() == null && locationY == destination_y) ||
                   (board[destination_x][destination_y].getPiece() != null && locationY != destination_y  &&
                   (board[destination_x][destination_y].getPiece().getColor() != color));
        }
        return false;
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
