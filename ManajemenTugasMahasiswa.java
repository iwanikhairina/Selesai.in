import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class ManajemenTugasMahasiswa extends JFrame {
    private JTextField namaField, deadlineField;
    private JTextArea deskripsiArea;
    private JComboBox<String> kategoriBox;
    private JTable tabelTugas;
    private DefaultTableModel tabelModel;
    private ArrayList<Tugas> daftarTugas = new ArrayList<>();

    public ManajemenTugasMahasiswa() {
        setTitle("Aplikasi Manajemen Tugas Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       // Ganti bagian di dalam constructor:
setLayout(new BorderLayout()); // Tetap pakai BorderLayout

// Panel Utama Vertikal
JPanel panelUtama = new JPanel();
panelUtama.setLayout(new BoxLayout(panelUtama, BoxLayout.Y_AXIS));

// Panel Input
JPanel panelInput = new JPanel(new GridLayout(5, 2, 10, 10));
panelInput.setBorder(BorderFactory.createTitledBorder("Input Tugas"));
panelInput.add(new JLabel("Nama Tugas:"));
namaField = new JTextField();
panelInput.add(namaField);
panelInput.add(new JLabel("Deskripsi:"));
deskripsiArea = new JTextArea(2, 20);
panelInput.add(new JScrollPane(deskripsiArea));
panelInput.add(new JLabel("Deadline:"));
deadlineField = new JTextField("YYYY-MM-DD");
panelInput.add(deadlineField);
panelInput.add(new JLabel("Kategori:"));
kategoriBox = new JComboBox<>(new String[]{"Kuliah", "Organisasi", "Pribadi"});
panelInput.add(kategoriBox);

// Panel Tombol
JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
JButton simpanBtn = new JButton("Simpan");
JButton tampilBtn = new JButton("Tampilkan");
JButton hapusBtn = new JButton("Hapus");
JButton saveToFileBtn = new JButton("Save ke File");

panelTombol.add(simpanBtn);
panelTombol.add(tampilBtn);
panelTombol.add(hapusBtn);
panelTombol.add(saveToFileBtn);

// Panel Tabel
tabelModel = new DefaultTableModel(new String[]{"Nama", "Deskripsi", "Deadline", "Kategori"}, 0);
tabelTugas = new JTable(tabelModel);
JScrollPane scrollTabel = new JScrollPane(tabelTugas);
scrollTabel.setBorder(BorderFactory.createTitledBorder("Daftar Tugas"));

// Tambah ke panel utama
panelUtama.add(panelInput);
panelUtama.add(panelTombol);
panelUtama.add(scrollTabel);

// Tambah ke frame utama
add(panelUtama, BorderLayout.CENTER);

// Aksi Tombol tetap sama seperti sebelumnya
simpanBtn.addActionListener(e -> simpanTugas());
tampilBtn.addActionListener(e -> tampilkanTugas());
hapusBtn.addActionListener(e -> hapusTugas());
saveToFileBtn.addActionListener(e -> simpanKeFile());
    }

    private void simpanTugas() {
        String nama = namaField.getText();
        String deskripsi = deskripsiArea.getText();
        String deadline = deadlineField.getText();
        String kategori = (String) kategoriBox.getSelectedItem();

        if (nama.isEmpty() || deadline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan deadline harus diisi.");
            return;
        }

        Tugas tugas = new Tugas(nama, deskripsi, deadline, kategori);
        daftarTugas.add(tugas);
        JOptionPane.showMessageDialog(this, "Tugas berhasil disimpan!");
        bersihkanInput();
    }

    private void tampilkanTugas() {
        tabelModel.setRowCount(0);
        for (Tugas t : daftarTugas) {
            tabelModel.addRow(new Object[]{t.nama, t.deskripsi, t.deadline, t.kategori});
        }
    }

    private void hapusTugas() {
        int selectedRow = tabelTugas.getSelectedRow();
        if (selectedRow != -1) {
            daftarTugas.remove(selectedRow);
            tabelModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.");
        }
    }

    private void simpanKeFile() {
        try (FileWriter writer = new FileWriter("tugas.txt")) {
            for (Tugas t : daftarTugas) {
                writer.write("Nama: " + t.nama + "\n");
                writer.write("Deskripsi: " + t.deskripsi + "\n");
                writer.write("Deadline: " + t.deadline + "\n");
                writer.write("Kategori: " + t.kategori + "\n");
                writer.write("--------------------------\n");
            }
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke tugas.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
        }
    }

    private void bersihkanInput() {
        namaField.setText("");
        deskripsiArea.setText("");
        deadlineField.setText("YYYY-MM-DD");
        kategoriBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManajemenTugasMahasiswa().setVisible(true));
    }

    class Tugas {
        String nama, deskripsi, deadline, kategori;

        Tugas(String nama, String deskripsi, String deadline, String kategori) {
            this.nama = nama;
            this.deskripsi = deskripsi;
            this.deadline = deadline;
            this.kategori = kategori;
        }
    }
}
