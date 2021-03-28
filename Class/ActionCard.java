public class ActionCard extends Card {
    public ActionCard(String jenis, String warna, String skill, int angka) {
        super(jenis, "NULL", skill, -99);
    }

    public void sayHi() {
        System.out.println("Kartu Action dengan jenis " +this.getJenisKartu());
    }
}