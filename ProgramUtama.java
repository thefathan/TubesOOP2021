import java.util.*;
import java.io.*;
import Class.*;


public class ProgramUtama {
    public static void main(String[] args) {
        String fname = "Asset/ascii-art.txt";
        Scanner scan = new Scanner(System.in); 


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
    }
}
