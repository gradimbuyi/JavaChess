package com.github.grxdiii.chess.graphic;

import com.github.grxdiii.chess.main.Game;
import com.github.grxdiii.chess.pieces.Piece;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;


/**
 * The Tile class defines the properties that makes up a single chess tile within the Board.
 * Each tile has location based off the x-y axis. THe
 *
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Tile extends JPanel {
    private static final Integer[]   TILE_RANK = {8, 7, 6, 5, 4, 3, 2, 1};
    private static final Character[] TILE_FILE = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private static final Color LIGHT = Color.decode("#b5d3ff");
    private static final Color DARK  = Color.decode("#4c8aed");
    private final Integer location_x;
    private final Integer location_y;
    private final Color color;
    private Piece piece;
    private Boolean hasPiece;

    /**
     * The TIle constructor takes in two parameters: locationX and locationY.
     *
     * @param location_x
     * @param location_y
     */
    public Tile(int location_x, int location_y, Color color) {
        this.location_x = location_x;
        this.location_y = location_y;
        this.color = color;
        this.piece = null;
        this.hasPiece = false;
        this.setBackground(color);
    }

    /**
     *
     */
    public void removePiece() {
        if(this.piece == null) return;
        hasPiece = false;
        remove(piece);
        piece = null;
        repaint();
    }

    /**
     *
     * @param piece
     * @return
     */
    public boolean addPiece(Piece piece) {
        boolean hasCaptured = hasPiece;
        removePiece();
        this.piece = piece;
        this.hasPiece = true;
        this.piece.changeLocation(location_x, location_y);
        this.add(piece);
        repaint();
        return hasCaptured;
    }

    /**
     *
     * @param graphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        Piece selected_piece = Game.getSelectedPiece();
        Color graphics_light = Board.getLightTileColor();
        Color graphics_dark  = Board.getDarkTileColor();

        super.paintComponent(graphics);

        if(location_x == 7 || location_y == 7) {
            Color graphics_color = color.equals(graphics_light) ? graphics_dark : graphics_light;
            Font  graphics_font  = new Font("Arial", Font.PLAIN, 15);

            // Set graphics font and color
            graphics.setFont(graphics_font);
            graphics.setColor(graphics_color);

            // Draw rank and file
            if(location_y == 7) {
                String rank = TILE_RANK[location_x].toString();
                graphics.drawString(rank, 102, 20);
            }

            if(location_x == 7) {
                String file = TILE_FILE[location_y].toString();
                graphics.drawString(file, 5, 110);
            }
        }

        // Board.updateBoard(selected_piece, graphics);
    }

    public Piece getPiece() {
        return piece;
    }

    public Integer getLocationX() {
        return location_x;
    }

    public Integer getLocationY() {
        return location_y;
    }

    public Boolean isOccupied() {
        return hasPiece;
    }

    public Color getColor() {
        return color;
    }

    public Color getNewColor() {
        Color light = Board.getLightTileColor();
        return color == light ? LIGHT : DARK;
    }

    public void resetColor() {
        setBackground(color);
    }
}
