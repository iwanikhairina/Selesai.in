package service;

import model.Tugas;
import java.util.ArrayList;
import java.util.List;

public class TugasServiceImpl implements Tugasservice {
    private List<Tugas> daftarTugas = new ArrayList<>();

    @Override
    public void simpanTugas(Tugas tugas) {
        daftarTugas.add(tugas);
    }

    @Override
    public void hapusTugas(int index) {
        if (index >= 0 && index < daftarTugas.size()) {
            daftarTugas.remove(index);
        }
    }

    @Override
    public List<Tugas> getDaftarTugas() {
        return daftarTugas;
    }
}
