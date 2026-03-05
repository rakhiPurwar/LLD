package FactoryMethod;

import java.util.List;
import java.util.Arrays;

// ============================
// 1) PRODUCT INTERFACE
// ============================
interface ReportGenerator {
    void generate(String fileName, List<String> rows);
}

// ============================
// 2) CONCRETE PRODUCTS
// ============================
class PdfReportGenerator implements ReportGenerator {
    @Override
    public void generate(String fileName, List<String> rows) {
        // In real life: use iText / OpenPDF / Apache PDFBox, etc.
        System.out.println("Generating PDF: " + fileName + ".pdf");
        for (String row : rows) {
            System.out.println("[PDF] " + row);
        }
        System.out.println("PDF generated ✅");
    }
}

class ExcelReportGenerator implements ReportGenerator {
    @Override
    public void generate(String fileName, List<String> rows) {
        // In real life: use Apache POI
        System.out.println("Generating Excel: " + fileName + ".xlsx");
        int r = 1;
        for (String row : rows) {
            System.out.println("[EXCEL] Row " + r + " -> " + row);
            r++;
        }
        System.out.println("Excel generated ✅");
    }
}

// ============================
// 3) CREATOR (FACTORY METHOD)
// ============================
// The factory method is createGenerator().
// Subclasses decide which generator to create.
abstract class ReportCreator {

    // ✅ Factory Method (GoF)
    protected abstract ReportGenerator createGenerator();

    // Common workflow stays here (template method style)
    public final void export(String fileName, List<String> rows) {
        // Common steps can be added here: validate, log, audit, etc.
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("fileName cannot be empty");
        }
        if (rows == null) {
            throw new IllegalArgumentException("rows cannot be null");
        }

        ReportGenerator generator = createGenerator();
        generator.generate(fileName, rows);
    }
}

// ============================
// 4) CONCRETE CREATORS
// ============================
class PdfReportCreator extends ReportCreator {
    @Override
    protected ReportGenerator createGenerator() {
        return new PdfReportGenerator();
    }
}

class ExcelReportCreator extends ReportCreator {
    @Override
    protected ReportGenerator createGenerator() {
        return new ExcelReportGenerator();
    }
}

// ============================
// 5) CLIENT / MAIN
// ============================
public class FactoryMethodExcelPdfGenerator {

    public static void main(String[] args) {

        List<String> data = List.of(
                "TxnId=101, Amount=200",
                "TxnId=102, Amount=450",
                "TxnId=103, Amount=999"
        );

        // Choose creator at runtime
        ReportCreator creator = new PdfReportCreator();
        creator.export("settlement_report", data);

        System.out.println("------------");

        creator = new ExcelReportCreator();
        creator.export("settlement_report", data);
    }
}