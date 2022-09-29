package tutorials.csvexports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserReader {
    public static List<Map<String, String>> readUsersFrom(File storedUsers) throws IOException {
        List<Map<String, String>> users;
        try (BufferedReader reader = new BufferedReader(new FileReader(storedUsers))) {
            users = reader.lines()
                    .skip(1)
                    .map(line -> userDataFrom(line))
                    .collect(Collectors.toList());
        }
        return users;
    }

    private static Map<String, String> userDataFrom(String line) {
        String[] cells = line.split(",");
        Map<String, String> userData = new HashMap<>();
        userData.put("id", cells[0]);
        userData.put("email", cells[1]);
        userData.put("first_name", cells[2]);
        userData.put("last_name", cells[3]);
        return userData;
    }
}
