import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image backgroundImage;

    public BackgroundPanel(String path) {
        ImageIcon icon = new ImageIcon(path);

        // Handle missing image
        if (icon.getIconWidth() == -1) {
            System.out.println("Background image not found: " + path);
            backgroundImage = null;
        } else {
            backgroundImage = icon.getImage();
        }

        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        // Draw background image
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // fallback color if image not found
            g2d.setColor(new Color(33, 102, 172));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // ===== DARK OVERLAY =====
        g2d.setColor(new Color(0, 0, 0, 120)); // adjust transparency here
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}