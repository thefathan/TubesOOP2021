package Card;
// kartu angka biasa


public class CardBiasa extends Card {
    public CardBiasa(String jenis, String warna, String skill, int angka) {
        super("Biasa", warna, "NULL", angka);
    }

    public void sayHi() {
        System.out.println("Kartu biasa dengan warna " +this.getWarnaKaru()+ " dan berangka " +this.getAngkaKartu());
    }
    public void infoKartu() {
        if (this.getAngkaKartu() == 0) {
            System.out.println("Zero " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 1) {
            System.out.println("One " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 2) {
            System.out.println("Two " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 3) {
            System.out.println("Three " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 4) {
            System.out.println("Four " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 5) {
            System.out.println("Five " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 6) {
            System.out.println("Six " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 7) {
            System.out.println("Seven " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 8) {
            System.out.println("Eight " +this.getWarnaKaru());
        }
        else if (this.getAngkaKartu() == 9) {
            System.out.println("Nine " +this.getWarnaKaru());
        }
    }

    public void cardAbility() {
        System.out.println("Hanya Kartu Biasa, link warna dan angka untuk turn selanjutnya");
    }
}