package Pieces;

import Graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Pawn extends Piece {
    protected Boolean isFirstMove;

    public Pawn(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
        this.isFirstMove = true;
    }



    @Override
    public ArrayList<Tile> legalMoves() {
        moves = new ArrayList<>();
        isFirstMove = numMoves == 0;

        if(color) {
            boolean checkSecond = addMove(locationX - 1, locationY);
            if(checkSecond && isFirstMove) addMove(locationX - 2, locationY);

        } else {
            boolean checkSecond = addMove(locationX + 1, locationY);
            if(checkSecond && isFirstMove) addMove(locationX + 2, locationY);
        }

        addMove(locationX, locationY);
        return moves;
    }
}
