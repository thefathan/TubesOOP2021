package Class.Card;
// Wildcard, Draw +4

public class WildCard extends Card {
    public WildCard(String jenis, String warna, String skill, int angka) {
        super("Wild Card", "NULL", skill, -99);
    }

    public void sayHi() {
        System.out.println("Sebuah kartu aksi dengan skill " +this.getSkillKartu());
    }
    public void infoKartu() {
        System.out.println(this.getSkillKartu());
    }
    public void cardAbility() {
        if (this.getSkillKartu().equals("Wildcard")) {
            System.out.println("Ability Wildcard, tentukan warna untuk turn selanjutnya");
        }
        else if (this.getSkillKartu().equals("Draw +4")) {
            System.out.println("Ability Draw +4, pemain turn selanjutnya wajib menambah +4 kartu dan pilihlah warna untuk turn setelah pemain ambil kartu");
        }
    }
}