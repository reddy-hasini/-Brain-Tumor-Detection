import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class ReportGenerator {

    // ===== TUMOR DETAILS =====
    private static String getTumorDetails(String result) {

        switch (result.toLowerCase()) {

            case "glioma":
                return "Glioma is a type of brain tumor that originates in the glial cells. "
                     + "It can be aggressive and may grow rapidly.\n\n"
                     + "Symptoms:\n- Headaches\n- Seizures\n- Vision problems\n\n"
                     + "Treatment:\n- Surgery\n- Radiation therapy\n- Chemotherapy\n\n"
                     + "Note: Immediate medical consultation is highly recommended.";

            case "meningioma":
                return "Meningioma is a tumor that arises from the meninges (protective layers of the brain). "
                     + "It is usually benign and grows slowly.\n\n"
                     + "Symptoms:\n- Headaches\n- Memory issues\n- Vision disturbances\n\n"
                     + "Treatment:\n- Observation (if small)\n- Surgery (if large)\n\n"
                     + "Note: Regular monitoring is advised.";

            case "pituitary":
                return "Pituitary tumors occur in the pituitary gland and may affect hormone levels.\n\n"
                     + "Symptoms:\n- Hormonal imbalance\n- Vision problems\n- Fatigue\n\n"
                     + "Treatment:\n- Medication\n- Surgery\n\n"
                     + "Note: Early diagnosis helps in effective treatment.";

            case "notumor":
                return "No tumor detected in the MRI scan.\n\n"
                     + "Status:\n- Brain appears normal\n\n"
                     + "Recommendation:\n- Maintain regular health checkups.";

            default:
                return "No additional medical information available.";
        }
    }

    // ===== GENERATE REPORT =====
    public static void generate(String prediction, String imagePath) {

        try {
            System.out.println("INSIDE REPORT GENERATOR...");

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("MRI_Report.pdf"));

            document.open();

            // ===== TITLE =====
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("MRI Tumor Detection Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));

            // ===== PREDICTION =====
            document.add(new Paragraph("Prediction: " + prediction));

            document.add(new Paragraph("\n"));

            // ===== DETAILS =====
            String tumorName = prediction.split(" ")[0];
            String details = getTumorDetails(tumorName);

            document.add(new Paragraph("Tumor Details:"));
            document.add(new Paragraph(details));

            document.add(new Paragraph("\n"));

            // ===== IMAGE =====
            Image img = Image.getInstance(imagePath);
            img.scaleToFit(300, 300);
            document.add(img);

            document.close();

            System.out.println("PDF CREATED SUCCESSFULLY!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}