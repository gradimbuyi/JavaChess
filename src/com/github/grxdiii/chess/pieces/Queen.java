package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Queen extends Piece {
    // SEE DEFINITION @ Piece.java
    public Queen(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    // SEE DEFINITION @ Piece.java
    @Override
    public ArrayList<Tile> legalMoves() {
        moves = new ArrayList<>();

        for(int move_type = 0; move_type < 8; move_type++) {
            int potential_x = move_type < 4 ?
                (move_type < 2 ? (locationX + (move_type % 2 == 0 ? -1 : 1)) : locationX) :
                (move_type < 6 ? (locationX - 1) : locationX + 1);
            int potential_y = move_type < 4 ?
                (move_type > 1 ? (locationY + (move_type % 2 == 0 ? -1 : 1)) : locationY) :
                (move_type % 2 == 0 ? locationY - 1 : locationY + 1);

            while(isTileAvailable(potential_x, potential_y)) {
                moves.add(board[potential_x][potential_y]);
                if(board[potential_x][potential_y].isOccupied) break;
                potential_x = move_type < 4 ?
                        (move_type < 2 ? (potential_x + (move_type % 2 == 0 ? -1 : 1)) : potential_x) :
                        (move_type < 6 ? (potential_x - 1) : potential_x + 1);
                potential_y = move_type < 4 ?
                        (move_type > 1 ? (potential_y + (move_type % 2 == 0 ? -1 : 1)) : potential_y) :
                        (move_type % 2 == 0 ? potential_y - 1 : potential_y + 1);
            }
        }

        return moves;
    }
}
