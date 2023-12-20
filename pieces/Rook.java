package pieces;

import graphic.Board;
import graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Rook extends Piece {
    private Boolean canCastle;

    public Rook(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public ArrayList<Tile> legalMoves() {
        Tile[][] board = Board.getBoard();
        moves = new ArrayList<>(); int move = 1;

        // VERTICAL MOVES //
        while(locationX + move <= 7) {
            if(board[locationX + move][locationY].getPiece() == null ||
               board[locationX + move][locationY].getPiece().getColor() != color) {
               moves.add(board[locationX + move][locationY]);
               move++;
            }  else break;
        }      move = 1;

        while(locationX - move >= 0) {
            if(board[locationX - move][locationY].getPiece() == null ||
               board[locationX - move][locationY].getPiece().getColor() != color) {
               moves.add(board[locationX - move][locationY]);
               move++;
            }  else break;
        }      move = 1;

        // HORIZONTAL MOVES //
        while(locationY + move <= 7) {
            if(board[locationX][locationY + move].getPiece() == null ||
               board[locationX][locationY + move].getPiece().getColor() != color) {
               moves.add(board[locationX][locationY + move]);
               move++;
            }  else break;
        }      move = 1;

        while(locationY - move >= 0) {
            if(board[locationX][locationY - move].getPiece() == null ||
               board[locationX][locationY - move].getPiece().getColor() != color) {
               moves.add(board[locationX][locationY - move]);
               move++;
            }  else break;
        }

        return moves;
    }
}
