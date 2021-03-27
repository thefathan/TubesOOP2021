// Class Card
// Berisi tentang seluk beluk kartu HIJI 
// diperuntukkan sebagai wadah bagi kartu Angka, Reverse, Block (Kartu yang memiliki warna)

public class Card {
    private int angka;
    private String warna;
    private String jenis;
    private String skill;

    public Card(int angka, String warna, String jenis, String skill) {
        this.angka = angka;
        this.warna = warna;
        this.jenis = jenis;
        this.skill = skill;
    }

    // getter
    public int getAngka() {
        return this.angka;
    }

    public String getWarna() {
        return this.warna;
    }

    public String getJenis() {
        return this.jenis;
    }

    public String getSkill() {
        return this.skill;
    }

    // setter
}