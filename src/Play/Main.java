package Play;

import javax.swing.*;

import GUI.GuiPrinter;

public class Main {
    private static final String Version = "3.5.3";
    public static void main(String[] args) {

        GuiPrinter lstr = new GuiPrinter();
    
        lstr.Initialize();
    
        for (int i = 0; i < lstr.Pmain_1_Set_Panel.length; i++) {
            lstr.Pmain[0].add(lstr.Pmain_1_Set_Panel[i]);
        }
        
        lstr.Application_Frame.add(lstr.tabs);
        lstr.tabs.add("Search", lstr.Pmain[0]);
        lstr.tabs.add("Chart", lstr.Pmain[1]);
        lstr.tabs.add("Show", lstr.Pmain[2]);
        
        lstr.Application_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lstr.Application_Frame.setSize(750, 530);
        lstr.Application_Frame.setTitle("JHack Ver:"+Version);
        lstr.Application_Frame.setResizable(false);
        lstr.Application_Frame.setVisible(true);
    }
}