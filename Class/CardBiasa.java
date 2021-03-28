public class CardBiasa extends Card {
    public CardBiasa(String jenis, String warna, String skill, int angka) {
        super("Biasa", warna, "NULL", angka);
    }

    public void sayHi() {
        System.out.println("Kartu biasa dengan warna " +this.getWarnaKaru()+ " dan berangka " +this.getAngkaKartu());
    }
}