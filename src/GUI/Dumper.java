package GUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Dumper {
    private List<Integer> Ilist;
    private BufferedReader br;

    public Dumper() {
        br = null;
        Ilist = new ArrayList<>();
    }

    public String[][] GetterString(File file,int N) {
        if (N <= 0) N = 0;
        String save[][] = new String[N][];
        int s = 0;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            if (line.equals("#Y2021TOSWINC#") == false) return null;

            for (int i = 0; i < save.length; i++) {
                save[i] = new String[4];
            }

            while ((line = br.readLine()) != null) {
                if (s >= N) break;
                if (line.startsWith("A")) {
                    for (int j = 0; line != null && line.startsWith("X") == false; ) {
                        if (!(line.startsWith("C") || line.startsWith("E"))) {
                            save[s][j++] = line;
                        }
                        line = br.readLine();
                    }
                    s++;
                }
            }

            br.close();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        return save;
    }

    public List<Integer> GetterInt(File file) {
        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            line = br.readLine();
            if (line.equals("#Y2021TOSWINC#") == false) return null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("D")) {
                    Ilist.add(Integer.parseInt(line.substring(2,line.length()-1).replace(",", "")));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return Ilist;
    }
}