package Card;
// Wildcard, Draw +4

public class ActionCard extends Card {
    public ActionCard(String jenis, String warna, String skill, int angka) {
        super("Action Card", "NULL", skill, -99);
    }

    public void sayHi() {
        System.out.println("Sebuah kartu aksi dengan skill " +this.getSkillKartu());
    }
    public void infoKartu() {
        System.out.println(this.getSkillKartu());
    }
}