package tutorials.csvexports;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class DownloadingPDFTest {

    @TempDir
    Path tempDir;

    @Test
    public void should_download_a_pdf_file() throws IOException, InterruptedException {

        // Should be able to download a PDF file from https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf

        Path downloadedPDF = PDFDownloader.downloadPDFFrom(tempDir, "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");

        // The downloaded file should be a PDF file
        String mimeType = Files.probeContentType(downloadedPDF);
        assertThat(mimeType).isEqualTo("application/pdf");

        // The downloaded file should exist
        assertThat(downloadedPDF).exists();
    }

    @Test
    public void should_read_the_contents_of_the_pdf_file() throws IOException, InterruptedException {

        String pdfToDownload = "https://freetestdata.com/wp-content/uploads/2022/11/Free_Test_Data_10.5MB_PDF.pdf";
        // Should be able to read the contents of the PDF file
        Path downloadedPDF = PDFDownloader.downloadPDFFrom(tempDir, pdfToDownload);

        // The downloaded file should contain the text "Dummy PDF file"
        try(PDDocument document = PDDocument.load(downloadedPDF.toFile())) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String textContent = pdfTextStripper.getText(document);
            assertThat(textContent).contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
            assertThat(textContent).contains("Donec semper fermentum libero eu tempor");
        }
    }
}
