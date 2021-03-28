public class SkillCard extends Card {
    public SkillCard(String jenis, String warna, String skill, int angka) {
        super(jenis, warna, skill, -99);
    }

    public void sayHi() {
        System.out.println("Kartu skill dengan warna " +this.getWarnaKaru()+ " dan berjenis " +this.getJenisKartu());
    }
}