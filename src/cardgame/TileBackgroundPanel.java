package cardgame;

import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class TileBackgroundPanel extends JPanel {
  private ImageIcon tileIcon = null;
  private boolean tileImage = true;

  public TileBackgroundPanel() {
  }

  public TileBackgroundPanel(ImageIcon newValue) {
    tileIcon = newValue;
  }

  public TileBackgroundPanel(LayoutManager layout) {
    super(layout);
  }

  public TileBackgroundPanel(LayoutManager layout, boolean isDoubleBuffered) {
    super(layout, isDoubleBuffered);
  }

  public TileBackgroundPanel(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
  }

  public ImageIcon getTileIcon() {
    return tileIcon;
  }

  public void setTileIcon(ImageIcon newValue) {
    this.tileIcon = newValue;
    repaint();
  }

  public void setTileImage(boolean newValue) {
    tileImage = newValue;
    repaint();
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (tileIcon != null) {
      Insets insets = getInsets();

      int width = getWidth() - insets.left - insets.right;
      int height = getHeight() - insets.top - insets.bottom;

      g.translate(insets.left, insets.top);
      g.setClip(0, 0, width, height);

      if (tileImage) {
        int w = tileIcon.getIconWidth();
        int h = tileIcon.getIconHeight();

        for (int y = 0; y < height; y += h) {
          for (int x = 0; x < width; x += w) {
            tileIcon.paintIcon(this, g, x, y);
          }
        }
      } else {
        g.drawImage(tileIcon.getImage(), 0, 0, width, height, this);
      }
    }
  }
}
