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

        // cuma ngeprint ascii art loading
        printAscii("Asset/loading-art.txt");
        printWithDelaysEveryChar("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||", TimeUnit.MILLISECONDS, 10);
        printWithDelays("", TimeUnit.MILLISECONDS, 400);

        // cuma print ascii art tulisan hiji
        printAscii("Asset/hiji-art.txt");


        // MULAI MASUK MENU
        printWithDelays("============TEKAN ENTER UNTUK MULAI PERMAINAN============", TimeUnit.MILLISECONDS, 800);
        klikLanjut = scan.nextLine();
        System.out.println("\nSELAMAT DATANG DI PERMAINAN GOBLOK HIJI!");
        
        
        // block bawah ini belum bisa jalan
        boolean jumlahPemainStatus = false;
        int jumlahPemain;
        while (jumlahPemainStatus = false) {
            printWithDelays("Silahkan masukkan pemain yang mau main (2 - 6 orang): ", TimeUnit.MILLISECONDS, 600);
            jumlahPemain = scan.nextInt();
            if (jumlahPemain < 2 && jumlahPemain > 6) {
                printAscii("Asset/invalid-art");
            }
            else {
                break;
            }
        } 
        // System.out.println(jumlahPemain);
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

    // Baca file external dan tampilin ke terminal
    public static void printAscii(String filename) throws Exception {
        String line = null;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Gabisa buka file '" + filename + "'");
        }
    }
}
