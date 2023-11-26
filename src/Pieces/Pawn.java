package Pieces;

import Graphic.Board;
import Graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Pawn extends Piece {
    private Integer[] potentialX;
    private Integer[] potentialY;

    public Pawn(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public ArrayList<Tile> legalMoves() {
        Tile[][] board = Board.getBoard();

        Piece destinationPiece;

        moves = new ArrayList<>();

        if(color) {
            if(locationX - 1 < 0) return moves;
            destinationPiece = board[locationX - 1][locationY].getPiece();
            if(destinationPiece == null) {
                moves.add(board[locationX - 1][locationY]);
            }

            if(locationY - 1 >= 0) {
                destinationPiece = board[locationX - 1][locationY - 1].getPiece();
                if(destinationPiece != null && !destinationPiece.color) {
                    moves.add(board[locationX - 1][locationY - 1]);
                }
            }
            if(locationY + 1 <= 7) {
                destinationPiece = board[locationX - 1][locationY + 1].getPiece();
                if(destinationPiece != null && !destinationPiece.color) {
                    moves.add(board[locationX - 1][locationY + 1]);
                }
            }
            if(numMoves == 0 && locationX - 2 < 0) return moves;
            destinationPiece = board[locationX - 2][locationY].getPiece();
            if(destinationPiece == null) {
                moves.add(board[locationX - 2][locationY]);
            }

        } else {
            if(locationX + 1 > 7) return moves;
            destinationPiece = board[locationX + 1][locationY].getPiece();
            if(destinationPiece == null) {
                moves.add(board[locationX + 1][locationY]);
            }

            if(locationY - 1 >= 0) {
                destinationPiece = board[locationX + 1][locationY - 1].getPiece();
                if(destinationPiece != null && destinationPiece.color) {
                    moves.add(board[locationX + 1][locationY - 1]);
                }
            }
            if(locationY + 1 <= 7) {
                destinationPiece = board[locationX + 1][locationY + 1].getPiece();
                if(destinationPiece != null && destinationPiece.color) {
                    moves.add(board[locationX + 1][locationY + 1]);
                }
            }
            if(numMoves == 0 && locationX + 2 > 7) return moves;
            destinationPiece = board[locationX + 2][locationY].getPiece();
            if(destinationPiece == null) {
                moves.add(board[locationX + 2][locationY]);
            }
        }
        return moves;
    }
}
