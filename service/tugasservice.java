package service;

import model.Tugas;
import java.util.List;

public interface Tugasservice {
    void simpanTugas(Tugas tugas);
    void hapusTugas(int index);
    List<Tugas> getDaftarTugas();
}
