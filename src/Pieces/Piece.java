package Pieces;

import Graphic.Tile;
import Main.Game;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public abstract class Piece {
    private final String type;
    private final Boolean color;

    private Integer locationX;
    private Integer locationY;
    private JLabel image;

    private static String FEN_STRING = Game.getFenNotation();

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


    public abstract Integer[] legalMoves();

    public void setImage(String fileName) {
        ImageIcon finalImage;
        Image temporary = new ImageIcon(fileName).getImage();
        temporary = temporary.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
        finalImage = new ImageIcon(temporary);
        image = new JLabel(finalImage);
    }

    public void updateFenString(Tile[][] tiles) {
        for(int locationX = 0; locationX < 8; locationX++) {
            for(int locationY = 0; locationY < 8; locationY++) {

            }
        }

    }

    public JLabel getImage() {
        return image;
    }

    public String getType() {
        return type;
    }
}
