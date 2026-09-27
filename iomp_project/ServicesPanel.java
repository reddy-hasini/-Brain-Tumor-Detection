import javax.swing.*;
import java.awt.*;

public class ServicesPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public ServicesPanel(CardLayout cardLayout, JPanel contentPanel) {

        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;

        setLayout(new BorderLayout());

        BackgroundPanel bg = new BackgroundPanel("servicesbg.png");
        bg.setLayout(new BorderLayout()); // 🔥 change from GridBagLayout
        add(bg, BorderLayout.CENTER);

        // ===== MAIN CONTAINER =====
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);
        main.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));

        // ===== TITLE (TOP CENTER) =====
        JLabel title = new JLabel("Our Services");
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(title);
        main.add(Box.createVerticalStrut(60)); // space below title

        // ===== BUTTON ROW (CENTERED) =====
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        centerWrapper.setOpaque(false);

        JPanel row = new JPanel(new GridLayout(1, 4, 30, 30));
        row.setOpaque(false);

        row.add(createImageButton("Tumor Detection", "upload", "mri.jpeg"));
        row.add(createImageButton("Report Generation", "report", "report.jpg"));
        row.add(createImageButton("User Friendly UI", "ui", "ui.png"));
        row.add(createImageButton("Q & A", "qa", "qa.png"));

        centerWrapper.add(row);

        main.add(centerWrapper);

        // ===== ADD TO TOP =====
        bg.add(main, BorderLayout.NORTH); // 🔥 key change
    }

    // ===== IMAGE BUTTON =====
    private JButton createImageButton(String text, String page, String imgPath) {

        JButton btn = new JButton();
        btn.setLayout(new BorderLayout());

        btn.setPreferredSize(new Dimension(260, 220)); // big buttons

        ImageIcon icon = new ImageIcon(imgPath);

        JLabel imageLabel;

        if (icon.getIconWidth() == -1) {
            imageLabel = new JLabel("No Image", SwingConstants.CENTER);
            imageLabel.setForeground(Color.WHITE);
        } else {
            Image img = icon.getImage().getScaledInstance(260, 160, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(img));
        }

        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btn.setBackground(new Color(0, 0, 0, 180));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        btn.add(imageLabel, BorderLayout.CENTER);
        btn.add(textLabel, BorderLayout.SOUTH);

        btn.addActionListener(e -> cardLayout.show(contentPanel, page));

        return btn;
    }
}