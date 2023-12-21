package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Tile;
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
     * This method is used to perform castling. A king can be castled if and only if it hasn't
     * moved and the rook from the given direction hasn't moved. In order for this move to be
     * valid, both pieces have to be able to "see" each other, meaning that there isn't a piece
     * between them.
     *
     * @param direction tells which direction king is going to be castled, where direction being
     *                  false means that the king will be castled left and vice versa.
     * @return Returns true if castling operation was done successfully, otherwise returns false.
     */
    protected Boolean performCastling(boolean direction) {
        // determines the location of rook to be castled
        int locationX = color     ? 7 : 0;
        int locationY = direction ? 7 : 0;

        // checks to see if the rook is in its home squares and performs the operations
        // based on the check
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

                // tells Piece.MovePiece that King has been castled
                return true;
            }
        }

        // failed to castle king / conditions have not been met
        return false;
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
    private Boolean checkKingMove(int locationX, int locationY) {
        // returns true if all conditions are met
        if(locationX >= 0 && locationX <= 7 && locationY >= 0 && locationY <= 7) {
            Piece piece = board[locationX][locationY].getPiece();
            return piece == null || piece.getColor() != color;
        }

        // otherwise returns false
        return false;
    }

    /**
     * This method produces the legal moves of a king given its location and its neighbor.
     * A king, like every other piece can move capture enemy pieces as well as move to legal
     * tiles that aren't occupied.
     *
     * @return Returns an array list of tiles containing the legal tiles in which the king can move.
     */
    @Override
    public ArrayList<Tile> legalMoves() {
        // potential locationX and locationY for the legal tiles
        Integer[] candidates = {locationX - 1, locationX + 1, locationY - 1, locationY + 1};
        moves = new ArrayList<>();

        // for loop to evaluate four move types
        for(int move_type = 0; move_type < 4; move_type++) {

            // this evaluates the first 6 potential moves
            if(move_type < 2 && checkKingMove(candidates[move_type], locationY)) {
                moves.add(board[candidates[move_type]][locationY]);
                if(checkKingMove(candidates[move_type], candidates[2])) {
                    moves.add(board[candidates[move_type]][candidates[2]]);
                }
                if(checkKingMove(candidates[move_type], candidates[3])) {
                    moves.add(board[candidates[move_type]][candidates[3]]);
                }
            }

            // this evaluates the remaining 2 potential moves
            else if(checkKingMove(locationX, candidates[move_type])) {
                moves.add(board[locationX][move_type]);
            }
        }

        // returns the legal moves
        return moves;
    }

}
