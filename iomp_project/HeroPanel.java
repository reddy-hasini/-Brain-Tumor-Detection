import javax.swing.*;
import java.awt.*;

public class HeroPanel extends JPanel {

    public HeroPanel() {

        setLayout(new BorderLayout());

        // ===== BACKGROUND =====
        BackgroundPanel bg = new BackgroundPanel("bg.jpg");
        add(bg, BorderLayout.CENTER);

        // ===== MAIN CONTENT =====
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);
        main.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        // ===== TITLE =====
        JLabel title = new JLabel("What is Brain Tumor?");
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getPreferredSize().height));

        // ===== CONTENT =====
        JLabel content = new JLabel(
                "<html><div style='text-align:left;'>"
                        + "A brain tumor is an abnormal growth of cells within the brain or surrounding tissues.<br><br>"
                        + "These tumors can be classified as benign (non-cancerous) or malignant (cancerous).<br><br>"
                        + "They may originate in the brain (primary tumors) or spread from other parts of the body (secondary tumors).<br><br>"
                        + "Depending on their size and location, brain tumors can affect important functions such as memory, vision, speech, and movement.<br><br>"
                        + "Common symptoms include headaches, seizures, vision problems, and difficulty in thinking.<br><br>"
                        + "Early detection using MRI scans plays a crucial role in identifying tumors and starting treatment at the right time."
                        + "</div></html>"
        );
        content.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        content.setForeground(Color.WHITE);
        content.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.setMaximumSize(new Dimension(Integer.MAX_VALUE, content.getPreferredSize().height));

        // ===== TYPES TITLE =====
        JLabel typesTitle = new JLabel("Types of Brain Tumors");
        typesTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        typesTitle.setForeground(Color.WHITE);
        typesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        typesTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, typesTitle.getPreferredSize().height));

        // ===== ADD CONTENT =====
        main.add(title);
        main.add(Box.createVerticalStrut(20));
        main.add(content);

        main.add(Box.createVerticalStrut(40));
        main.add(typesTitle);
        main.add(Box.createVerticalStrut(20));

        // ===== TYPE 1 =====
        main.add(createTypeSection(
                "1. Glioma",
                "Gliomas are tumors that occur in the brain and spinal cord. They begin in glial cells that support nerve cells.",
                "glioma1.jpg",
                "glioma2.jpg"
        ));

        // ===== TYPE 2 =====
        main.add(createTypeSection(
                "2. Meningioma",
                "Meningiomas arise from the membranes surrounding the brain and spinal cord. Most are non-cancerous but can still cause serious problems.",
                "meningioma1.jpeg",
                "meningioma2.jpg"
        ));

        // ===== TYPE 3 =====
        main.add(createTypeSection(
                "3. Pituitary Tumor",
                "These tumors occur in the pituitary gland and may affect hormone levels in the body.",
                "pituitary1.jpeg",
                "pituitary2.jpg"
        ));

        // ===== SCROLL =====
        JScrollPane scroll = new JScrollPane(main);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        bg.add(scroll, BorderLayout.CENTER);
    }

    // ===== TYPE SECTION =====
    private JPanel createTypeSection(String titleText, String descText, String img1, String img2) {

        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setOpaque(false);
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel(titleText);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getPreferredSize().height));

        JLabel desc = new JLabel(
                "<html><div style='text-align:left;'>" + descText + "</div></html>"
        );
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        desc.setForeground(Color.WHITE);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        desc.setMaximumSize(new Dimension(Integer.MAX_VALUE, desc.getPreferredSize().height));

        JPanel imgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        imgPanel.setOpaque(false);
        imgPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        imgPanel.add(createImage(img1));
        imgPanel.add(createImage(img2));

        section.add(title);
        section.add(Box.createVerticalStrut(10));
        section.add(desc);
        section.add(Box.createVerticalStrut(15));
        section.add(imgPanel);
        section.add(Box.createVerticalStrut(30));

        return section;
    }

    // ===== IMAGE =====
    private JLabel createImage(String path) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() == -1) {
            JLabel error = new JLabel("Image not found");
            error.setForeground(Color.WHITE);
            return error;
        }

        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }
}