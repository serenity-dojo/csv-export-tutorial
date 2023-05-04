package tutorials.csvexports;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PDFDownloader {
    public static Path downloadPDFFrom(Path tempDir, String url) throws IOException, InterruptedException {
        // Store the PDF file in a temporary file
        Path downloadedPDF = tempDir.resolve("downloaded.pdf");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(downloadedPDF));

        if (response.statusCode() != 200) {
            throw new IOException("Could not download PDF file");
        }

        return downloadedPDF;
    }
}
