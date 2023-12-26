package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Knight extends Piece {
    // SEE DEFINITION @ Piece.java
    public Knight(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    // SEE DEFINITION @ Piece.java
    @Override
    public ArrayList<Tile> legalMoves() {
        Integer[] candidates = {locationX - 2, locationX + 2, locationY - 2, locationY + 2};
        moves = new ArrayList<>();

        for(int move_type = 0; move_type < 4; move_type++) {
            int temp_one = move_type < 2 ? locationY - 1 : locationX - 1;
            int temp_two = move_type < 2 ? locationY + 1 : locationX + 1;

            for(int move = 0; move < 2; move++) {
                int potential_x = move_type < 2 ? candidates[move_type] : move == 0 ? temp_one : temp_two;
                int potential_y = move_type > 1 ? candidates[move_type] : move == 0 ? temp_one : temp_two;
                if(isTileAvailable(potential_x, potential_y)) {
                    moves.add(board[potential_x][potential_y]);
                }
            }
        }

        return moves;
    }
}
