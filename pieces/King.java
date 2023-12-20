package pieces;

import graphic.Board;
import graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class King extends Piece {
    public King(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    /**
     * This method is used to perform castling. A king can be castled if and only if it hasn't moved and
     * the rook hasn't moved. In order for this move to be valid, both pieces have to be able to "see"
     * each other, meaning that there isn't a piece between them.
     *
     * @param direction tells which direction king is going to be castled, where direction being false means
     *                  that the king will be castled left and vice versa.
     * @return Returns true if castling operation was done successfully, otherwise returns false.
     */
    protected Boolean performCastling(boolean direction) {
        // calls getBoard to get our chess board
        Tile[][] board = Board.getBoard();

        // determines the location of rook to be castled
        int locationX = color ? 7 : 0;
        int locationY = direction ? 7 : 0;

        // checks to see if the rook is in its home squares and performs the operations based on the check
        if(board[locationX][locationY].getPiece() != null) {
            Piece rook = board[locationX][locationY].getPiece();

            // checks to see if the rook has moved and if it's able to "see" the king
            if(rook instanceof Rook && rook.numMoves == 0 && rook.getColor() == color) {
                int start = direction ? 5 : 1;
                int end   = direction ? 6 : 3;

                for(int localY = start; localY <= end; localY++) {
                    if(board[locationX][localY].getPiece() != null) {
                        return false;
                    }
                }

                // all the conditions are met, performs castling
                board[locationX][4].removePiece();
                board[locationX][locationY].removePiece();

                if(direction) {
                    board[locationX][5].addPiece(rook);
                    board[locationX][6].addPiece(this);
                } else {
                    board[locationX][2].addPiece(this);
                    board[locationX][3].addPiece(rook);
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public ArrayList<Tile> legalMoves() {
        Tile[][] board = Board.getBoard();
        moves = new ArrayList<>();


        if(locationX - 1 >= 0) {
            if((board[locationX - 1][locationY].getPiece() == null) ||
                board[locationX - 1][locationY].getPiece().getColor() != color) {
                moves.add(board[locationX - 1][locationY]);
            }

            if((locationY - 1 >= 0) && (board[locationX - 1][locationY - 1].getPiece() == null ||
                board[locationX - 1][locationY - 1].getPiece().getColor() != color)) {
                moves.add(board[locationX - 1][locationY - 1]);
            }

            if((locationY + 1 <= 7) && (board[locationX - 1][locationY + 1].getPiece() == null ||
                board[locationX - 1][locationY + 1].getPiece().getColor() != color)) {
                moves.add(board[locationX - 1][locationY + 1]);
            }
        }

        if(locationX + 1 <= 7) {
            if((board[locationX + 1][locationY].getPiece() == null) ||
                board[locationX + 1][locationY].getPiece().getColor() != color) {
                moves.add(board[locationX + 1][locationY]);
            }

            if((locationY - 1 >= 0) && (board[locationX + 1][locationY - 1].getPiece() == null ||
                board[locationX + 1][locationY - 1].getPiece().getColor() != color)) {
                moves.add(board[locationX + 1][locationY - 1]);
            }

            if((locationY + 1 <= 7) && (board[locationX + 1][locationY + 1].getPiece() == null ||
                board[locationX + 1][locationY + 1].getPiece().getColor() != color)) {
                moves.add(board[locationX + 1][locationY + 1]);
            }
        }

        if((locationY - 1 >= 0) && (board[locationX][locationY - 1].getPiece() == null ||
            board[locationX][locationY - 1].getPiece().getColor() != color)) {
            moves.add(board[locationX][locationY - 1]);
        }

        if((locationY + 1 <= 7) && (board[locationX][locationY + 1].getPiece() == null ||
                board[locationX][locationY + 1].getPiece().getColor() != color)) {
            moves.add(board[locationX][locationY + 1]);
        }

        return moves;
    }
}
