package src.main.java.bookmark_manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SavedDataParser {
    public static List<User> readFromFile(File file) {}
    
    public static void saveToFile(File file, List<User> data) throws IOException {
        var writer = new BufferedWriter(new FileWriter(file));
        writer.append("dimityr 123");
        writer.append('\n');
        writer.
        writer.close();
    }
}
