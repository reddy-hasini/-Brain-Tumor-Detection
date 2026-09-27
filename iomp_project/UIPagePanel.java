import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UIPagePanel extends JPanel {

    public UIPagePanel() {

        setLayout(new BorderLayout());

        // ===== BACKGROUND =====
        BackgroundPanel bg = new BackgroundPanel("images/ui.png");
        bg.setLayout(new BorderLayout());
        add(bg, BorderLayout.CENTER);

        // ===== MAIN CONTENT =====
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);
        main.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        // ===== TITLE =====
        JLabel title = new JLabel("User Friendly Interface");
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== CONTENT =====
        JLabel content = new JLabel(
                "<html><div style='width:700px;'>"
                        + "<b>Simple & Clean Design:</b><br>"
                        + "Our application is designed with a clean and simple interface so users can easily navigate.<br><br>"

                        + "<b>Easy Navigation:</b><br>"
                        + "Clear menu options like Home, About, Services, and Upload help users quickly access features.<br><br>"

                        + "<b>Fast Interaction:</b><br>"
                        + "Users can upload MRI images and get results with minimal steps.<br><br>"

                        + "<b>Readable Content:</b><br>"
                        + "Text is aligned properly with good spacing and contrast for better readability.<br><br>"

                        + "<b>Responsive Layout:</b><br>"
                        + "The UI adjusts smoothly to full screen for a better experience.<br><br>"

                        + "<b>Interactive Buttons:</b><br>"
                        + "Buttons are designed clearly so users know exactly where to click."
                        + "</div></html>"
        );

        content.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        content.setForeground(Color.WHITE);
        content.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== UPLOAD BUTTON =====
        JButton uploadBtn = new JButton("Upload MRI Image");
        uploadBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== PREVIEW =====
        JLabel previewLabel = new JLabel();
        previewLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== BUTTON ACTION =====
        uploadBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                String path = fileChooser.getSelectedFile().getAbsolutePath();

                // ===== SHOW IMAGE =====
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                previewLabel.setIcon(new ImageIcon(img));

                // ===== CALL PYTHON =====
                try {
                    ProcessBuilder pb = new ProcessBuilder(
                            "python",
                            "../predict.py",
                            path
                    );

                    pb.redirectErrorStream(true);
                    Process process = pb.start();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(process.getInputStream())
                    );

                    String line;
                    String output = "";

                    while ((line = reader.readLine()) != null) {
                        output += line;
                    }

                    JOptionPane.showMessageDialog(null, "Result: " + output);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error running Python!");
                }
            }
        });

        // ===== ADD TEXT =====
        main.add(title);
        main.add(Box.createVerticalStrut(30));
        main.add(content);

        // ===== ADD UPLOAD UI =====
        main.add(Box.createVerticalStrut(30));
        main.add(uploadBtn);
        main.add(Box.createVerticalStrut(20));
        main.add(previewLabel);

        // ================= NEW SECTION =================
        main.add(Box.createVerticalStrut(50));

        JLabel sectionTitle = new JLabel("Application Screens");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        sectionTitle.setForeground(Color.WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        main.add(sectionTitle);
        main.add(Box.createVerticalStrut(30));

        // ===== GRID =====
        JPanel grid = new JPanel(new GridLayout(2, 2, 40, 40));
        grid.setOpaque(false);

        grid.add(createFeature("Home", "images/home.png"));
        grid.add(createFeature("About", "images/about.png"));
        grid.add(createFeature("Services", "images/services.png"));
        grid.add(createFeature("Upload", "images/upload.png"));

        main.add(grid);

        // ===== SCROLL =====
        JScrollPane scroll = new JScrollPane(main);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        bg.add(scroll, BorderLayout.CENTER);
    }

    // ===== FEATURE BLOCK =====
    private JPanel createFeature(String title, String imgPath) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heading.setForeground(Color.WHITE);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel img = createImage(imgPath);
        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(heading);
        panel.add(Box.createVerticalStrut(10));
        panel.add(img);

        return panel;
    }

    // ===== IMAGE =====
    private JLabel createImage(String path) {

        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() == -1) {
            JLabel err = new JLabel("Image not found: " + path);
            err.setForeground(Color.WHITE);
            return err;
        }

        Image img = icon.getImage().getScaledInstance(250, 160, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }
}