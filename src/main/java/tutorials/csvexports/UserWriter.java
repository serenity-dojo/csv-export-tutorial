package tutorials.csvexports;

import io.restassured.RestAssured;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class UserWriter {

    public void saveUsersTo(File storedUsers) throws IOException {

        var userData = (List<Map<String,Object>>) RestAssured.given()
                .queryParam("page","1")
                .get("https://reqres.in/api/users")
                .jsonPath().get("data");

        try(Writer writer = new FileWriter(storedUsers)) {
            writeHeader(writer);
            for (Map<String, Object> user : userData) {
                writeRow(writer, user);
            }
        }
    }

    private void writeRow(Writer writer, Map<String, Object> user) throws IOException {
        writer.write(user.get("id").toString());
        writer.write(",");
        writer.write(user.get("email").toString());
        writer.write(",");
        writer.write(user.get("first_name").toString());
        writer.write(",");
        writer.write(user.get("last_name").toString());
        writer.write(System.lineSeparator());
    }

    private void writeHeader(Writer writer) throws IOException {
        writer.write("ID,EMAIL,FIRST_NAME,LAST_NAME");
        writer.write(System.lineSeparator());
    }
}
