import javax.swing.*;
import java.awt.*;

public class LandingPanel extends JPanel {

    public LandingPanel() {

        setLayout(new GridBagLayout());
        setBackground(new Color(33,102,172));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel title = new JLabel("Brain Tumor Detection System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));
        title.setForeground(Color.WHITE);

        gbc.gridy = 0;
        add(title, gbc);

        JLabel desc = new JLabel(
            "<html><div style='text-align:center; width:500px;'>"
            + "An intelligent system for detecting brain tumors using MRI images."
            + "</div></html>"
        );
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        desc.setForeground(Color.WHITE);

        gbc.gridy = 1;
        add(desc, gbc);

        ImageIcon icon = new ImageIcon("tumor.jpg");
        Image img = icon.getImage().getScaledInstance(400, 350, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(img));

        gbc.gridy = 2;
        add(image, gbc);
    }
}