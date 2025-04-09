package service;

import java.io.IOException;

public interface filesaver {
    void simpan(String content, String path) throws IOException;
}
