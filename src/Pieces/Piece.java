package Pieces;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public abstract class Piece {
    private Integer locationX;
    private Integer locationY;
    private String type;
    private Boolean color;
    private JLabel image;

    /**
     *
     * @param type
     * @param color
     * @param locationX
     * @param locationY
     */
    public Piece(String type, boolean color, int locationX, int locationY) {
        this.type = type;
        this.color = color;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     *
     * @return
     */
    public abstract Integer[] legalMoves();

    public void setImage(String fileName) {
        ImageIcon finalImage;
        Image temporary = new ImageIcon(fileName).getImage();
        temporary = temporary.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
        finalImage = new ImageIcon(temporary);
        image = new JLabel(finalImage);
    }

    public JLabel getImage() {
        return image;
    }

    public String getType() {
        return type;
    }
}
