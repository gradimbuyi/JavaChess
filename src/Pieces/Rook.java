package Pieces;

import Graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Rook extends Piece {
    private Boolean canCastle;

    public Rook(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public ArrayList<Tile> legalMoves() {
        moves = new ArrayList<>();

        int temp = 1;
        while(locationX + temp <= 7) {
            boolean shouldContinue = addMove(locationX + temp, locationY);
            if(!shouldContinue) { temp = 1; break; }
            temp++;
        }

        while(locationX - temp >= 0) {
            boolean shouldContinue = addMove(locationX - temp, locationY);
            if(!shouldContinue) { temp = 1; break; }
            temp++;
        }

        while(locationY + temp <= 7) {
            boolean shouldContinue = addMove(locationX, locationY + temp);
            if(!shouldContinue) { temp = 1; break; }
            temp++;
        }

        while(locationY - temp >= 0) {
            boolean shouldContinue = addMove(locationX, locationY - temp);
            if(!shouldContinue) break;
            temp++;
        }

        return moves;
    }
}
