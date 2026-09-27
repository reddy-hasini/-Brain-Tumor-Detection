import javax.swing.*;
import java.awt.*;

public class BrainTumorUI {

    JFrame frame;
    JPanel contentPanel;
    CardLayout cardLayout;

    public BrainTumorUI() {

        frame = new JFrame("Brain Tumor Detection System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // ===== NAVBAR =====
        JPanel nav = new JPanel();
        nav.setBackground(new Color(33, 102, 172));

        JButton home = createNavButton("Home");
        JButton about = createNavButton("About");
        JButton services = createNavButton("Services");
        JButton upload = createNavButton("Upload");

        nav.add(home);
        nav.add(about);
        nav.add(services);
        nav.add(upload);

        frame.add(nav, BorderLayout.NORTH);

        // ===== CARD LAYOUT =====
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // ===== MAIN PAGES =====
        contentPanel.add(new LandingPanel(), "landing");   // ✅ FIXED
        contentPanel.add(new HeroPanel(), "home");
        contentPanel.add(new AboutPanel(), "about");
        contentPanel.add(new ServicesPanel(cardLayout, contentPanel), "services");
        contentPanel.add(new UploadPanel(), "upload");     // ✅ FIXED

        // ===== SERVICE SUB PAGES =====
        contentPanel.add(createPage("Tumor Detection Page"), "detect");
        contentPanel.add(createPage("Report Generation Page"), "report");
        contentPanel.add(new UIPagePanel(), "ui");
        contentPanel.add(new QAPanel(), "qa");

        frame.add(contentPanel, BorderLayout.CENTER);

        // ===== NAVIGATION =====
        home.addActionListener(e -> cardLayout.show(contentPanel, "home"));
        about.addActionListener(e -> cardLayout.show(contentPanel, "about"));
        services.addActionListener(e -> cardLayout.show(contentPanel, "services"));
        upload.addActionListener(e -> cardLayout.show(contentPanel, "upload"));

        // 👇 START WITH LANDING PAGE
        cardLayout.show(contentPanel, "landing");

        frame.setVisible(true);
    }

    // ===== NAV BUTTON =====
    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 70, 140));
        btn.setFocusPainted(false);
        return btn;
    }

    // ===== SIMPLE PAGE =====
    private JPanel createPage(String text) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(33, 102, 172));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 30));
        label.setForeground(Color.WHITE);

        panel.add(label);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BrainTumorUI::new);
    }
}