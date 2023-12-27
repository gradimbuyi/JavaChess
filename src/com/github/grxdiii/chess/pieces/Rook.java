package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Board;
import com.github.grxdiii.chess.graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Rook extends Piece {
    // SEE DEFINITION @ Piece.java
    public Rook(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    // SEE DEFINITION @ Piece.java
    @Override
    public ArrayList<Tile> legalMoves() {
        moves = new ArrayList<>();

        // Iterates through the four potential direction in which the rook can move to.
        // According to classical Chess rules, the Rook can move freely forward, backward, and sideways
        // if there are no friendly pieces blocking it. The direction in which it moves is also the
        // direction in which it captures enemy pieces.
        for(int move_type = 0; move_type < 4; move_type++) {
            int potential_x = move_type < 2 ? (locationX + (move_type % 2 == 0 ? -1 : 1)) : locationX;
            int potential_y = move_type > 1 ? (locationY + (move_type % 2 == 0 ? -1 : 1)) : locationY;

            while(isTileAvailable(potential_x, potential_y)) {
                moves.add(board[potential_x][potential_y]);
                if(board[potential_x][potential_y].isOccupied) break;
                potential_x = move_type < 2 ? (potential_x + (move_type % 2 == 0 ? -1 : 1)) : potential_x;
                potential_y = move_type > 1 ? (potential_y + (move_type % 2 == 0 ? -1 : 1)) : potential_y;
            }
        }

        return moves;
    }
}
