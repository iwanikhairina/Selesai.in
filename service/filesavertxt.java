package service;

import java.io.FileWriter;
import java.io.IOException;

public class FileSaverTxt implements filesaver {
    @Override
    public void simpan(String content, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        }
    }
}
