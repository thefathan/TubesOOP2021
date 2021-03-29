package Class.Card;
// Draw +2, reverse, Block

public class PowerCard extends Card {
    public PowerCard(String jenis, String warna, String skill, int angka) {
        super("Power Card", warna, skill, -99);
    }

    public void sayHi() {
        System.out.println("Kartu skill dengan warna " +this.getWarnaKartu()+ " dan berkemampuan " +this.getSkillKartu());
    }
    public void infoKartu() {
        System.out.println(this.getSkillKartu()+ " " +this.getWarnaKartu());
    }
    public void cardAbility() {
        if (this.getSkillKartu().equals("Draw +2")) {
            System.out.println("Ability Draw +2, pemain turn selanjutnya mengambil 2 kartu");
        }
        else if (this.getSkillKartu().equals("Reverse")) {
            System.out.println("Ability Reverse, putaran berbalik arah");
        }
        else if (this.getSkillKartu().equals("Block")) {
            System.out.println("Ability Block, lompati turn selanjutnya");
        }
    }
}