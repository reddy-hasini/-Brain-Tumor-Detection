import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UploadPanel extends JPanel {

    private JLabel resultLabel;
    private File selectedFile;

    public UploadPanel() {

        setLayout(new BorderLayout());

        // ===== BACKGROUND =====
        BackgroundPanel bg = new BackgroundPanel("bg1.jpeg");
        bg.setLayout(new BorderLayout());
        add(bg, BorderLayout.CENTER);

        // ================= MAIN CONTAINER (SCROLLABLE) =================
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);

        // ===================== TOP CENTER =====================
        JPanel topWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topWrapper.setOpaque(false);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        JLabel instruction = new JLabel("Upload the MRI scan by clicking on Upload MRI");
        instruction.setFont(new Font("Segoe UI", Font.BOLD, 20));
        instruction.setForeground(Color.WHITE);
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton upload = new JButton("Upload MRI");
        JButton predict = new JButton("Predict");

        upload.setPreferredSize(new Dimension(200, 40));
        predict.setPreferredSize(new Dimension(200, 40));

        upload.setAlignmentX(Component.CENTER_ALIGNMENT);
        predict.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultLabel = new JLabel("Result:");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ===== ACTIONS =====
        upload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                resultLabel.setText("Selected: " + selectedFile.getName());
            }
        });

       predict.addActionListener(e -> {
    if (selectedFile == null) {
        resultLabel.setText("⚠ Please upload an MRI image first!");
    } else {
        try {
            String path = selectedFile.getAbsolutePath();

            ProcessBuilder pb = new ProcessBuilder(
                    "C:\\Users\\GAYATHRI\\Downloads\\mini\\venv\\Scripts\\python.exe",
                    "../predict.py",
                    path
            );

            pb.redirectErrorStream(true);
            Process process = pb.start();

            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream())
            );

            String line;
            String output = "";

            while ((line = reader.readLine()) != null) {
                output += line;
            }

           resultLabel.setText("Prediction: " + output);
           System.out.println("CALLING REPORT...");
           ReportGenerator.generate(output, path);
        } catch (Exception ex) {
            ex.printStackTrace();
            resultLabel.setText("❌ Error running prediction!");
        }
    }
});

        topPanel.add(instruction);
        topPanel.add(Box.createVerticalStrut(15));
        topPanel.add(upload);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(predict);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(resultLabel);

        topWrapper.add(topPanel);

        // ===================== STEPS =====================
        JPanel stepsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        stepsWrapper.setOpaque(false);

        JPanel stepsPanel = new JPanel();
        stepsPanel.setLayout(new BoxLayout(stepsPanel, BoxLayout.Y_AXIS));
        stepsPanel.setOpaque(false);

        JLabel helpHeading = new JLabel("Need to help to upload");
        helpHeading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        helpHeading.setForeground(new Color(0, 51, 153));
        helpHeading.setAlignmentX(Component.LEFT_ALIGNMENT);

        stepsPanel.add(helpHeading);
        stepsPanel.add(Box.createVerticalStrut(20));

        stepsPanel.add(createStep("1. Click on Upload",
                "Click the Upload MRI button to select your scan.",
                "step1.png"));

        stepsPanel.add(createStep("2. Choose the Image",
                "Select the MRI image file from your system.",
                "step2.png"));

        stepsPanel.add(createStep("3. Click on Predict",
                "Press Predict to analyze the uploaded image.",
                "step3.jpeg"));

        stepsPanel.add(createStep("4. HeatMap",
                "View the highlighted tumor area in the result.",
                "step4.jpeg"));

        stepsWrapper.add(stepsPanel);

        // ===== ADD BOTH TO MAIN =====
        main.add(Box.createVerticalStrut(20));
        main.add(topWrapper);
        main.add(Box.createVerticalStrut(40));
        main.add(stepsWrapper);
        main.add(Box.createVerticalStrut(50));

        // ===================== SCROLL =====================
        JScrollPane scroll = new JScrollPane(main);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll

        bg.add(scroll, BorderLayout.CENTER);
    }

    // ================= STEP =================
    private JPanel createStep(String title, String desc, String imgPath) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel stepTitle = new JLabel(title);
        stepTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        stepTitle.setForeground(Color.WHITE);

        JLabel stepDesc = new JLabel(desc);
        stepDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        stepDesc.setForeground(Color.WHITE);

        JLabel img = createImage(imgPath);

        panel.add(stepTitle);
        panel.add(stepDesc);
        panel.add(Box.createVerticalStrut(5));
        panel.add(img);
        panel.add(Box.createVerticalStrut(20));

        return panel;
    }

    private JLabel createImage(String path) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() == -1) {
            JLabel err = new JLabel("Image not found");
            err.setForeground(Color.WHITE);
            return err;
        }

        Image img = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }
}