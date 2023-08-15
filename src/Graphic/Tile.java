package Graphic;

import Pieces.Piece;
import javax.swing.JPanel;
import java.awt.Color;

public class Tile extends JPanel {
    private final Integer locationX;
    private final Integer locationY;
    private final Color color;
    private Piece piece;
    public Boolean isOccupied = false;
    public Boolean isSelected = false;
    private static final Color LIGHT = Color.decode("#f0f0f0");
    private static final Color DARK = Color.decode("#5b80ba");

    public Tile(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.piece = null;

        if((locationX % 2) == (locationY % 2)) color = LIGHT;
        else color = DARK;

        setBackground(color);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;

        if(piece != null) {
            this.isOccupied = true;
            this.add(piece.getImage());
        }
    }

    public void changeColor() {
        if(isSelected == false) {
            isSelected = true;
            setBackground(Color.decode("#f25c5c"));

        } else {
            isSelected = false;
            setBackground(color);
        }
    }

    public void removePiece() {
        if(this.piece == null) return ;
        this.isOccupied = false;
        this.remove(piece.getImage());
        this.piece = null;
        repaint();
    }

    public Piece getPiece() {
        return piece;
    }
}
