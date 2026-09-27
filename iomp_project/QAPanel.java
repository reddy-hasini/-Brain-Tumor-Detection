import javax.swing.*;
import java.awt.*;

public class QAPanel extends JPanel {

    public QAPanel() {

        setLayout(new BorderLayout());

        // ===== BACKGROUND =====
        BackgroundPanel bg = new BackgroundPanel("qa.jpeg"); // your background
        bg.setLayout(new BorderLayout());
        add(bg, BorderLayout.CENTER);

        // ===== MAIN CONTENT =====
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);
        main.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        // ===== TITLE =====
        JLabel title = new JLabel("Brain Tumor Detection - FAQs");
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        main.add(title);
        main.add(Box.createVerticalStrut(30));

        // ===== ADD QUESTIONS =====
        main.add(createQA("1. What is a brain tumor?",
                "A brain tumor is an abnormal growth of cells in the brain. It can be benign (non-cancerous) or malignant (cancerous)."));

        main.add(createQA("2. What are the common symptoms?",
                "Persistent headaches, nausea, blurred vision, seizures, memory problems, and difficulty speaking or walking."));

        main.add(createQA("3. How is a brain tumor detected?",
                "MRI scans, CT scans, biopsy, and neurological exams are used for detection."));

        main.add(createQA("4. Best imaging technique?",
                "MRI is the most accurate as it provides detailed images of brain tissues."));

        main.add(createQA("5. Can tumors be detected early?",
                "Yes, early detection is possible with timely scans and symptom awareness."));

        main.add(createQA("6. Role of AI?",
                "AI helps analyze MRI images, detect tumors faster, and assist doctors."));

        main.add(createQA("7. Is detection painful?",
                "No, MRI and CT scans are painless. Biopsy may cause minor discomfort."));

        main.add(createQA("8. Accuracy?",
                "MRI: Highly accurate, CT: Moderate, AI: Often above 90% accuracy."));

        main.add(createQA("9. Types of tumors?",
                "Gliomas, Meningiomas, Pituitary tumors, Metastatic tumors."));

        main.add(createQA("10. Can it be mistaken?",
                "Yes, stroke, infections, and multiple sclerosis may appear similar."));

        main.add(createQA("11. Detection time?",
                "Scan: 15–60 mins. Diagnosis: hours to days."));

        main.add(createQA("12. Who is at risk?",
                "Genetics, radiation exposure, family history, and age."));

        main.add(createQA("13. Without symptoms?",
                "Yes, tumors can be found accidentally during other scans."));

        main.add(createQA("14. After detection?",
                "Tests, surgery, radiation, or chemotherapy."));

        main.add(createQA("15. Can it be cured?",
                "Benign tumors can often be treated successfully if detected early."));

        // ===== SCROLL =====
        JScrollPane scroll = new JScrollPane(main);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        bg.add(scroll, BorderLayout.CENTER);
    }

    // ===== QUESTION BLOCK =====
    private JPanel createQA(String question, String answer) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel q = new JLabel(question);
        q.setFont(new Font("Segoe UI", Font.BOLD, 20));
        q.setForeground(new Color(173, 216, 230)); // light blue

        JLabel a = new JLabel("<html><div style='width:700px;'>" + answer + "</div></html>");
        a.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        a.setForeground(Color.WHITE);

        panel.add(q);
        panel.add(Box.createVerticalStrut(5));
        panel.add(a);
        panel.add(Box.createVerticalStrut(20));

        return panel;
    }
}
