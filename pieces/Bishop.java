package pieces;

import graphic.Board;
import graphic.Tile;

import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Bishop extends Piece {
    public Bishop(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public ArrayList<Tile> legalMoves() {
        Tile[][] board = Board.getBoard();
        moves = new ArrayList<>();
        int move = 1;

        while(locationX - move >= 0 && locationY - move >= 0) {
            if(board[locationX - move][locationY - move].getPiece() == null ||
               board[locationX - move][locationY - move].getPiece().getColor() != color) {
               moves.add(board[locationX - move][locationY - move]);
               move++;
            }  else break;
        }      move = 1;


        while(locationX - move >= 0 && locationY + move <= 7) {
            if(board[locationX - move][locationY + move].getPiece() == null ||
               board[locationX - move][locationY + move].getPiece().getColor() != color) {
               moves.add(board[locationX - move][locationY + move]);
               move++;
            }  else break;
        }      move = 1;

        while(locationX + move <= 7 && locationY - move >= 0) {
            if(board[locationX + move][locationY - move].getPiece() == null ||
               board[locationX + move][locationY - move].getPiece().getColor() != color) {
               moves.add(board[locationX + move][locationY - move]);
               move++;
            }  else break;
        }      move = 1;


        while(locationX + move <= 7 && locationY + move <= 7) {
            if(board[locationX + move][locationY + move].getPiece() == null ||
               board[locationX + move][locationY + move].getPiece().getColor() != color) {
               moves.add(board[locationX + move][locationY + move]);
               move++;
            }  else break;
        }

        return moves;
    }
}
