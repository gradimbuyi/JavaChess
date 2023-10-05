package Pieces;
import Graphic.Board;
import Graphic.Tile;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public abstract class Piece extends JLabel {
    protected final String type;
    protected final Boolean color;
    protected Integer locationX;
    protected Integer locationY;

    public Piece(String type, boolean color, int locationX, int locationY) {
        this.type = type;
        this.color = color;
        this.locationX = locationX;
        this.locationY = locationY;
        if(this.color) setImage("./images/white_" + this.type + ".png");
        else setImage("./images/black_" + this.type + ".png");
    }

    public abstract ArrayList<Tile> legalMoves(Board board);

    public Boolean checkIfValid(ArrayList<Tile> moves, Tile destinationTile) {
        for(Tile move : moves) {

            System.out.println("Destination Tile X: " + destinationTile.getLocationX() + " Destination Tile Y: " + destinationTile.getLocationY());
            System.out.println("Move Tile X: " + move.getLocationX() + "Move Tile Y: " + move.getLocationY());
            System.out.println("");
            //System.out.println(move.equals(destinationTile));
            if(move == destinationTile) return true;
        }
        return false;
    }

    public void setImage(String fileName) {
        this.setIcon(new ImageIcon(new ImageIcon(fileName).getImage().
                getScaledInstance(105, 105, Image.SCALE_SMOOTH)));
    }

    public Boolean getColor() {
        return color;
    }
}
