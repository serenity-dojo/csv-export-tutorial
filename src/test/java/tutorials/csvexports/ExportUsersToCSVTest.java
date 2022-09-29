package tutorials.csvexports;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ExportUsersToCSVTest {

    @Test
    public void should_export_all_users_to_a_csv_file() throws IOException {

        // Create a temp file
        var storedUsers = File.createTempFile("users",".csv");
        storedUsers.deleteOnExit();

        // Save users in the API to the temp file
        var userWriter = new UserWriter();
        userWriter.saveUsersTo(storedUsers);

        List<Map<String,String>> loadedUsers = UserReader.readUsersFrom(storedUsers);

        assertThat(loadedUsers).hasSize(6);
        assertThat(loadedUsers).allMatch( userData -> !userData.get("id").isEmpty());
        assertThat(loadedUsers).allMatch( userData -> !userData.get("email").isEmpty());
        assertThat(loadedUsers).allMatch( userData -> !userData.get("first_name").isEmpty());
        assertThat(loadedUsers).allMatch( userData -> !userData.get("last_name").isEmpty());
    }
}
