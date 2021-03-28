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
}