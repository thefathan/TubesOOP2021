import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import Class.*;


public class ProgramUtama {
    public static void main(String[] args) throws Exception {

        // nyimpen variable untuk "klik enter"
        String klikLanjut;
        Scanner scan = new Scanner(System.in); 

        // cuma ascii art gambar Hiji
        String fname = "Asset/ascii-art.txt";


        String line = null;
        try {
            FileReader fileReader = new FileReader(fname);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Gabisa buka file '" + fname + "'");
        }

        // MULAI MASUK MENU
        printWithDelays("============TEKAN ENTER UNTUK LANJUT============", TimeUnit.MILLISECONDS, 1000);
        klikLanjut = scan.nextLine();
        printWithDelaysEveryChar("\nSELAMAT DATANG DI PERMAINAN GOBLOK HIJI!", TimeUnit.MILLISECONDS, 100);
    }










    
    // ngeprint output dengan delay
    public static void printWithDelays(String data, TimeUnit unit, long delay) throws InterruptedException {
        unit.sleep(delay);    
        System.out.print(data);
            
    }

    // ngeprint output dengan delay setiap hurufnya
    public static void printWithDelaysEveryChar(String data, TimeUnit unit, long delay) throws InterruptedException {
        for (char ch : data.toCharArray()) {
            System.out.print(ch);
            unit.sleep(delay);
        }
    }
}
