package Pieces;

import Graphic.Board;
import Graphic.Tile;

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
    public ArrayList<Tile> legalMoves(Board board) {
        Tile[][] squares = board.getTiles();
        ArrayList<Tile> moves = new ArrayList<>();

        return new ArrayList<>();
    }
}
