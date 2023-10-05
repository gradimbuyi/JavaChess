package Pieces;

import Graphic.Board;
import Graphic.Tile;

import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Knight extends Piece {
    public Knight(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public ArrayList<Tile> legalMoves(Board board) {
        return null;
    }

}
