package Pieces;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Bishop extends Piece {
    public Bishop(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public Integer[] legalMoves() {
        return new Integer[0];
    }
}