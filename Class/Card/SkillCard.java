package Card;
// Draw +2, reverse, Block

public class SkillCard extends Card {
    public SkillCard(String jenis, String warna, String skill, int angka) {
        super("Skill Card", warna, skill, -99);
    }

    public void sayHi() {
        System.out.println("Kartu skill dengan warna " +this.getWarnaKaru()+ " dan berkemampuan " +this.getSkillKartu());
    }
    public void infoKartu() {
        System.out.println(this.getSkillKartu()+ " " +this.getWarnaKaru());
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