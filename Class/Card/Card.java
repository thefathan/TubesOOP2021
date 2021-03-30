package Class.Card;
// Class Card

// Berisi tentang seluk beluk kartu HIJI 
// diperuntukkan sebagai wadah bagi kartu Angka, Reverse, Block (Kartu yang memiliki warna)

public abstract class Card {
    private String jenisKartu;
    private String warnaKartu;
    private String skillKartu;
    private int angkaKartu;

    // Constructor
    public Card(String jenisKartu, String warnaKartu, String skillKartu, int angkaKartu) {
        this.jenisKartu = jenisKartu;
        this.warnaKartu = warnaKartu;
        this.skillKartu = skillKartu;
        this.angkaKartu = angkaKartu;
    }

    // Getter
    public String getJenisKartu() {
        return this.jenisKartu;
    }

    public String getWarnaKartu() {
        return this.warnaKartu;
    }

    public String getSkillKartu() {
        return this.skillKartu;
    }

    public int getAngkaKartu() {
        return this.angkaKartu;
    }

    // abstract method
    public abstract void sayHi();

    public abstract void infoKartu();

    public abstract void cardAbility();
}