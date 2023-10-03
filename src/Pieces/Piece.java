package Pieces;
import Graphic.Board;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public abstract class Piece extends JLabel {
    private final String type;
    private final Boolean color;
    private Integer locationX;
    private Integer locationY;

    public Piece(String type, boolean color, int locationX, int locationY) {
        this.type = type;
        this.color = color;
        this.locationX = locationX;
        this.locationY = locationY;
        if(this.color) setImage("./images/white_" + this.type + ".png");
        else setImage("./images/black_" + this.type + ".png");
    }

    public abstract Integer[] legalMoves();

    public void setImage(String fileName) {
        this.setIcon(new ImageIcon(new ImageIcon(fileName).getImage().
                getScaledInstance(105, 105, Image.SCALE_SMOOTH)));
    }

    public Piece getPiece() {
        return this;
    }

    public void updateLocation(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public Boolean getColor() {
        return color;
    }
}
