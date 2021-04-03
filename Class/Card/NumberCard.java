package Class.Card;
// kartu angka biasa


public class NumberCard extends Card {
    public NumberCard(String jenis, String warna, String skill, int angka) {
        super("Number Card", warna, "NULL", angka);
    }

    public void sayHi() {
        System.out.println("Kartu biasa dengan warna " +this.getWarnaKartu()+ " dan berangka " +this.getAngkaKartu());
    }
    public void infoKartu() {
        if (this.getAngkaKartu() == 0) {
            System.out.println("Zero " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 1) {
            System.out.println("One " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 2) {
            System.out.println("Two " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 3) {
            System.out.println("Three " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 4) {
            System.out.println("Four " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 5) {
            System.out.println("Five " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 6) {
            System.out.println("Six " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 7) {
            System.out.println("Seven " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 8) {
            System.out.println("Eight " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == 9) {
            System.out.println("Nine " +this.getWarnaKartu());
        }
        else if (this.getAngkaKartu() == -99) {
            System.out.println("Dummy " +this.getWarnaKartu());
        }
    }

    public void cardAbility() {
        System.out.println("Hanya Kartu Biasa, link warna dan angka untuk turn selanjutnya");
    }
}