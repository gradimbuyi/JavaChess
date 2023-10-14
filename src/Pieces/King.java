package Pieces;

import Graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class King extends Piece {
    private Boolean canCastle;

    public King(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public ArrayList<Tile> legalMoves() {
        moves = new ArrayList<>();

        addMove(locationX + 1, locationY);
        addMove(locationX, locationY + 1);
        addMove(locationX + 1, locationY + 1);
        addMove(locationX + 1, locationY - 1);
        addMove(locationX - 1, locationY);
        addMove(locationX, locationY - 1);
        addMove(locationX - 1, locationY + 1);
        addMove(locationX - 1, locationY - 1);

        return moves;
    }
}
