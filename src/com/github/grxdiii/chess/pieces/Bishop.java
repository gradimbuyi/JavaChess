package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Bishop extends Piece {
    // SEE DEFINITION @ Piece.java
    public Bishop(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    // SEE DEFINITION @ Piece.java
    @Override
    public ArrayList<Tile> legalMoves() {
        moves = new ArrayList<>();

        // Iterates through the four potential direction in which the bishop can move to.
        // According to classical Chess rules, the Bishop can move freely diagonally if there
        // are no frigidly pieces blocking it. The direction in which it move is also the
        // direction in which it captures enemy piece
        for(int move_type = 0; move_type < 4; move_type++) {
            int potential_x = move_type < 2 ? locationX - 1 : locationX + 1;
            int potential_y = move_type % 2 == 0 ? locationY - 1 : locationY + 1;

            while(isTileAvailable(potential_x, potential_y)) {
                moves.add(board[potential_x][potential_y]);
                if(board[potential_x][potential_y].isOccupied) break;
                potential_x = move_type < 2 ? potential_x - 1 : potential_x + 1;
                potential_y = move_type % 2 == 0 ? potential_y - 1 : potential_y + 1;
            }
        }

        return moves;
    }
}
