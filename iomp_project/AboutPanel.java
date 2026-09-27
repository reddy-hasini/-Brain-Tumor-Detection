import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

    public AboutPanel() {

        setLayout(new BorderLayout());

        // ===== BACKGROUND =====
        BackgroundPanel bg = new BackgroundPanel("bg.jpg");
        add(bg, BorderLayout.CENTER);

        // ===== MAIN PANEL =====
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);
        main.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 0));

        // ===== TITLE =====
        JLabel title = new JLabel("About Us");
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== MISSION =====
        JLabel mission = new JLabel(
                "<html><div style='width:700px;'>"
                        + "<span style='font-size:20px; font-weight:bold;'>Our Mission:</span><br><br>"
                        + "Our goal is to support early detection of brain tumors using advanced technology and user-friendly tools.<br><br>"
                        + "We aim to provide accurate and fast analysis to help patients and doctors take timely decisions.<br><br>"
                        + "By combining medical knowledge with modern software solutions, we strive to create awareness and make healthcare accessible.<br><br>"
                        + "Early diagnosis can save lives, and our platform is built to assist in that journey."
                        + "</div></html>"
        );
        mission.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mission.setForeground(Color.WHITE);
        mission.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== IMAGES =====
        JPanel missionImages = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        missionImages.setOpaque(false);
        missionImages.setAlignmentX(Component.LEFT_ALIGNMENT);

        missionImages.add(createImage("mission1.jpeg"));
        missionImages.add(createImage("mission2.jpeg"));

        // ===== RECOVERY =====
        JLabel recovery = new JLabel(
                "<html><div style='width:700px;'>"
                        + "<span style='font-size:20px; font-weight:bold;'>Stories of Hope & Recovery:</span><br><br>"
                        + "Many people diagnosed with brain tumors have successfully undergone treatment and are living healthy lives today.<br><br>"
                        + "Their journeys inspire strength, courage, and hope for others facing similar challenges.<br><br>"
                        + "With early detection, proper treatment, and support, recovery is possible.<br><br>"
                        + "You are not alone in this journey — hope and healing are always within reach."
                        + "</div></html>"
        );
        recovery.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        recovery.setForeground(Color.WHITE);
        recovery.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== SURVIVOR =====
        JPanel survivorPanel = new JPanel();
        survivorPanel.setLayout(new BoxLayout(survivorPanel, BoxLayout.Y_AXIS));
        survivorPanel.setOpaque(false);
        survivorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel img = createLargeImage("sabrina.png");
        img.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel quote = new JLabel(
                "<html><div style='width:600px;'>"
                        + "<span style='font-size:22px; font-style:italic;'>"
                        + "\"It is possible to find light at the end of the tunnel.\""
                        + "</span><br><br>"
                        + "<span style='font-size:20px; font-weight:bold;'>— Sabrina</span>"
                        + "</div></html>"
        );
        quote.setForeground(Color.WHITE);
        quote.setAlignmentX(Component.LEFT_ALIGNMENT);

        survivorPanel.add(img);
        survivorPanel.add(Box.createVerticalStrut(15));
        survivorPanel.add(quote);

        // ===== CONTACT US =====
        JLabel contact = new JLabel(
                "<html><div style='width:700px;'>"
                        + "<span style='font-size:20px; font-weight:bold;'>Contact Us:</span><br><br>"
                        + "📧 Email: support@braintumorapp.com<br><br>"
                        + "📞 Phone: +91 98765 43210<br><br>"
                        + "📍 Location: Chennai, India<br><br>"
                        + "Feel free to reach out for support, queries, or collaboration."
                        + "</div></html>"
        );
        contact.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contact.setForeground(Color.WHITE);
        contact.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== ADD =====
        main.add(title);
        main.add(Box.createVerticalStrut(30));

        main.add(mission);
        main.add(Box.createVerticalStrut(15));
        main.add(missionImages);

        main.add(Box.createVerticalStrut(30));

        main.add(recovery);
        main.add(Box.createVerticalStrut(20));
        main.add(survivorPanel);

        main.add(Box.createVerticalStrut(30));
        main.add(contact);

        // ===== WRAPPER =====
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(main, BorderLayout.WEST);

        // ===== SCROLL =====
        JScrollPane scroll = new JScrollPane(wrapper);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        bg.add(scroll, BorderLayout.CENTER);
    }

    private JLabel createImage(String path) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() == -1) {
            JLabel err = new JLabel("Image not found");
            err.setForeground(Color.WHITE);
            return err;
        }

        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    private JLabel createLargeImage(String path) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() == -1) {
            JLabel err = new JLabel("Image not found");
            err.setForeground(Color.WHITE);
            return err;
        }

        Image img = icon.getImage().getScaledInstance(500, 350, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }
}