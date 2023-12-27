package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Board;
import com.github.grxdiii.chess.graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class King extends Piece {
    // SEE DEFINITION @ Piece.java
    public King(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    /**
     * This method is used to perform castling. A king can be castled if and only if it hasn't
     * moved and the rook from the given direction hasn't moved. In order for this move to be
     * valid, both pieces have to be able to "see" each other, meaning that there isn't a piece
     * between them.
     *
     * @param destination_locationY the locationY of the destination tile
     * @return Returns true if castling operation was done successfully, otherwise returns false.
     */
    protected Boolean performCastling(int destination_locationY) {
        boolean direction = destination_locationY >= 6;

        // Determines the location of rook to be castled
        int locationX = color     ? 7 : 0;
        int locationY = direction ? 7 : 0;

        // Checks to see if the rook is in its home squares and performs the operations
        // based on the check
        if(board[locationX][locationY].getPiece() != null) {
            Piece rook = board[locationX][locationY].getPiece();

            // Checks to see if the rook has moved and if it's able to "see" the king
            if(rook instanceof Rook && rook.numMoves == 0 && rook.getColor() == color) {
                int start = direction ? 5 : 1;
                int end   = direction ? 6 : 3;

                for(int localY = start; localY <= end; localY++) {
                    if(board[locationX][localY].getPiece() != null) {
                        return false;
                    }
                }

                // All the conditions are met, performs castling
                board[locationX][4].removePiece();
                board[locationX][locationY].removePiece();

                if(direction) {
                    board[locationX][5].addPiece(rook);
                    board[locationX][6].addPiece(this);
                } else {
                    board[locationX][2].addPiece(this);
                    board[locationX][3].addPiece(rook);
                }

                // UPDATES THE BOARD COLOR
                Board.updateBoard(locationX, 4, locationX, direction ? 6 : 2);

                // Tells Piece.MovePiece that King has been castled
                return true;
            }
        }

        // Failed to castle king / conditions have not been met
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
        // Potential locationX and locationY for the legal tiles
        Integer[] candidates = {locationX - 1, locationX + 1, locationY - 1, locationY + 1, locationY};
        moves = new ArrayList<>();

        // For loop to evaluate four move types
        for(int move_type = 0; move_type < 4; move_type++) {

            // Evaluates and calculates first 6 moves.
            // According to classical Chess rules, the King can move one square at a time from
            // its current tiles to any of its neighbouring tiles, provided that its destination is
            // not occupied by a friendly piece. This part of the code tests to see if the forward,
            // backward, and diagonal moves are valid.
            if(move_type < 2) {
                for(int potential_y = 2; potential_y < 5; potential_y++) {
                    boolean addMove = isTileAvailable(candidates[move_type], candidates[potential_y]);
                    if(addMove) moves.add(board[candidates[move_type]][candidates[potential_y]]);
                }
            }

            // Evaluates and calculates the last 2 moves.
            // This part of the code tests to see if the sideways moves are valid.
            else if(isTileAvailable(locationX, candidates[move_type])) {
                moves.add(board[locationX][candidates[move_type]]);
            }
        }

        return moves;
    }

}
