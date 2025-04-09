package model;

public class Tugas {
    private String nama;
    private String deskripsi;
    private String deadline;
    private kategori kategori;

    public Tugas(String nama, String deskripsi, String deadline, kategori kategori) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.deadline = deadline;
        this.kategori = kategori;
    }

    // Getter
    public String getNama() { return nama; }
    public String getDeskripsi() { return deskripsi; }
    public String getDeadline() { return deadline; }
    public kategori getkategori() { return kategori; }
}
