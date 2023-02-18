package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Play.YahooHack;
import absclass.Status;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GuiPrinter extends JFrame implements ActionListener
{
    private File file;
    private File Fcsv;
    private int Thread_End = 0;
    private int Thread_Plus = 0;
    private List<YahooHack> Thread_List;
    
    public JTabbedPane tabs;
    private JFreeChart chart;
    private JTextArea Log_Area;
    private JTable Print_Table;
    private JTextArea Show_Table;
    public JFrame Application_Frame;
    private ButtonGroup JradioGroup;
    private DefaultCategoryDataset data;
    
    public JPanel Pmain[];
    private JCheckBox checkboxs[];
    private JTextArea Input_Texts[];
    public JPanel Pmain_1_Set_Panel[];
    private JTextArea Input_price_Texts[];
    private JRadioButton Web_radio_Buttons[];

    private String[] columnNames = {"Title", "URL", "Price", "Day"};
    private final int Pmain_1_Set_NUMBER = 6;
    private final int Pmain_NUMBER = 3;

    public GuiPrinter() 
    {
        Thread_List = new ArrayList<>();
        data = new DefaultCategoryDataset();
        
        tabs = new JTabbedPane();
        Application_Frame = new JFrame();
        Pmain = new JPanel[Pmain_NUMBER];
        Pmain_1_Set_Panel = new JPanel[Pmain_1_Set_NUMBER];

        for (int i = 0; i < Pmain.length; i++) {
            Pmain[i] = new JPanel();
        }
        
        for (int i = 0; i < Pmain_1_Set_Panel.length; i++) {
            Pmain_1_Set_Panel[i] = new JPanel();
        }

        Pmain[0].add(tabs);
    }
    
    public void Initialize() {
        JRaidButtonSetter(); //0
        TextFildSetter();   // 1
        CheckBoxs();        // 2
        max_min(); // 3
        TextFildScrollPane();//4
        JButtonSetter();    // 5
        Graphic_bar();
        Analys();
    }
    
    private TitledBorder CreateBorders(Color color, String title) {
        Border lines = BorderFactory.createLineBorder(color);
        TitledBorder titles = BorderFactory.createTitledBorder(lines,title);

        return titles;
    }

    private void JRaidButtonSetter() {
        JradioGroup = new ButtonGroup();
        Web_radio_Buttons = new JRadioButton[2];

        String Web_name[] = {"Yahoo","Mercari"};

        for (int i = 0; i < Web_name.length; i++) {
            Web_radio_Buttons[i] = new JRadioButton(Web_name[i],true);
        }
        
        for (JRadioButton jr : Web_radio_Buttons) {
            jr.setFont(new Font("Arial", Font.PLAIN,18));
            jr.addActionListener(this);

            JradioGroup.add(jr);
            Pmain_1_Set_Panel[0].add(jr);
            Pmain_1_Set_Panel[0].setBorder(CreateBorders(Color.BLACK, "WEB"));
        }
    }

    private void TextFildSetter() {
        JLabel ts[] = new JLabel[2];
        Input_Texts = new JTextArea[2];
        Show_Table = new JTextArea(1,20);
        Font font = new Font("Arial", Font.BOLD, 18);

        String setfild = "NUMBER:";
        String txfild[] = {"Keyword:","FILE Name:"};
        JLabel setts = new JLabel(setfild);

        for (int i = 0; i < txfild.length; i++) {
            ts[i] = new JLabel(txfild[i]);
            ts[i].setFont(font);

            Input_Texts[i] = new JTextArea(1,20);
            Input_Texts[i].setLineWrap(true);
            Input_Texts[i].setBorder(new EtchedBorder(EtchedBorder.RAISED));

            Pmain_1_Set_Panel[1].add(ts[i]);
            Pmain_1_Set_Panel[1].add(Input_Texts[i]);
        }

        setts.setFont(font);

        Show_Table.setLineWrap(true);
        Show_Table.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        Pmain[2].add(setts);
        Pmain[2].add(Show_Table);
    }

    private void CheckBoxs() {
        checkboxs = new JCheckBox[5];
        String Check_name[] = {"New","1Hour","Privacy","Free Delivery","Convenience"};

        for (int i = 0; i < Check_name.length; i++) {
            checkboxs[i] = new JCheckBox(Check_name[i]);
            checkboxs[i].addActionListener(this);
            checkboxs[i].setFont(new Font("Arial", Font.PLAIN, 18));
            Pmain_1_Set_Panel[2].add(checkboxs[i]);
        }
    }

    private void max_min() {
        JLabel Price_label[] = new JLabel[2];
        Input_price_Texts = new JTextArea[2];
        Font font = new Font("Arial", Font.BOLD, 18);

        String Price_wid[] = {"Price:  Min","~ Max"};

        for (int i = 0;i < Price_wid.length; i++) {
            Input_price_Texts[i] = new JTextArea(1,10);
            Input_price_Texts[i].setLineWrap(true);
            Input_price_Texts[i].setBorder(new EtchedBorder(EtchedBorder.RAISED));

            Price_label[i] = new JLabel(Price_wid[i]);
            Price_label[i].setFont(font);

            Pmain_1_Set_Panel[3].add(Price_label[i]);
            Pmain_1_Set_Panel[3].add(Input_price_Texts[i]);
        }
    }

    private void TextFildScrollPane() {
        Log_Area = new JTextArea(10,40);
        JScrollPane scrollpane = new JScrollPane(Log_Area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        Pmain_1_Set_Panel[4].add(scrollpane);
    }

    private void JButtonSetter() {
        JButton jbutton[] = new JButton[4];
        String Button_name[] = {"Clear","Search","Stop","Folder"};

        for (int i = 0; i < Button_name.length; i++) {
            jbutton[i] = new JButton(Button_name[i]);
            jbutton[i].setFont(new Font("BOLD", Font.PLAIN, 18));
            jbutton[i].addActionListener(this);
            Pmain_1_Set_Panel[5].add(jbutton[i]);
        }

        int i = 0;
        JButton dum[] = new JButton[2];
        String Print_button_name[] = {"Print","CSV"};
        for (JButton jButton : dum) {
            jButton = new JButton(Print_button_name[i++]);
            jButton.addActionListener(this);
            Pmain[1].add(jButton);
        }

        int j = 0;
        String RSeload[] = {"Show","CSV"};
        JButton Reload[] = new JButton[2];
        for (JButton jButton : Reload) {
            jButton = new JButton(RSeload[j++]);
            jButton.addActionListener(this);;
            Pmain[2].add(jButton);
        }
    }

    private void AreaPrint(String str) {
        Log_Area.append(str);
    }

    private void Opening(){
        Status stat = new Status();
        AreaPrint("Search\n");
        AreaPrint("type: "+(Web_radio_Buttons[0].isSelected() ? "Yahoo" : "Mercari")+"\n");
        AreaPrint("key: "+Input_Texts[0].getText()+"\n");
        AreaPrint("FileName: "+Input_Texts[1].getText()+"\n");
        AreaPrint("Root: "+file.getAbsolutePath()+"\n");

        stat.setKeyword(Input_Texts[0].getText());
        stat.setFileName(Input_Texts[1].getText());
        stat.setConditions(checkboxs[0].isSelected() ? 1 : 0);
        stat.setTimefirst(checkboxs[1].isSelected() ? 1 : 0);
        stat.setAnonymous(checkboxs[2].isSelected() ? 1 : 0);
        stat.setShipping(checkboxs[3].isSelected() ? 1 : 0);
        stat.setConvenience(checkboxs[4].isSelected() ? 1 : 0);

        if (Input_price_Texts[0].getText().equals("") == false)
            stat.setMin(Integer.parseInt(Input_price_Texts[0].getText()));
        if (Input_price_Texts[1].getText().equals("") == false)
            stat.setMax(Integer.parseInt(Input_price_Texts[1].getText()));

        if (Web_radio_Buttons[0].isSelected()) {
            Thread_List.add(new YahooHack(stat,Application_Frame,Thread_Plus));
            JOptionPane.showMessageDialog(this, "Thread: "+Thread_Plus+"を実行しています。");
            AreaPrint("Thread: "+Thread_Plus+"を実行しています。\n");
            Thread_List.get(Thread_Plus++).start();
        }
    }

    private void Graphic_bar() {
        chart = ChartFactory.createLineChart("Market Price", "Number", "Price", data,PlotOrientation.VERTICAL,true,false,false);
        ChartPanel cpanel = new ChartPanel(chart);
        Pmain[1].add(cpanel);
    }

    private void GReload() {
        Dumper dum = new Dumper();
        List<Integer> list = new ArrayList<>();
        list = dum.GetterInt(Fcsv);

        if (list == null) {
            JOptionPane.showMessageDialog(this, "Error: 対応しているファイルではありません。");
            return;
        }
        
        int i = 1;
        for (Integer integer : list) {
            data.addValue(integer, "DATA", "No"+i++);
        }
        chart = ChartFactory.createLineChart("Market Price", "Number", "Price", data,PlotOrientation.VERTICAL,true,false,false);
    }

    private void Analys() {
        String[][] tabledata = {};
        Print_Table = new JTable(tabledata, columnNames);
        
        JScrollPane sp = new JScrollPane(Print_Table);
        sp.setPreferredSize(new Dimension(650, 400));
        Pmain[2].add(sp);
    }

    private void TReload() {
        DefaultTableModel model = new DefaultTableModel();
        Dumper dum = new Dumper();
        String[][] Slist;

        if (Show_Table.getText().equals("")) Show_Table.setText("0");
        Slist = dum.GetterString(Fcsv,Integer.parseInt(Show_Table.getText()));

        if (Slist == null) {
            JOptionPane.showMessageDialog(this, "Error: 対応しているファイルではありません。");
            return;
        }
        
        model = new DefaultTableModel(Slist,columnNames);
        Print_Table.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Mercari")){
            JOptionPane.showMessageDialog(this, "ERROR: 準備中");
            JradioGroup.clearSelection();
        }
        else if (e.getActionCommand().equals("Folder")) {
            JFileChooser filechooser = new JFileChooser("c:");
            filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int selected = filechooser.showSaveDialog(this);
            if (selected == JFileChooser.APPROVE_OPTION){
            file = filechooser.getSelectedFile();
            }
        }
        else if (e.getActionCommand().equals("Clear")) {
            Log_Area.setText("");
            JradioGroup.clearSelection();
            for (JCheckBox jCheckBox : checkboxs) {
                jCheckBox.setSelected(false);
            }

            for (JTextArea jt : Input_Texts) {
                jt.setText("");
            }
        }
        else if (e.getActionCommand().equals("Search")) {
            if (file == null) {
                JOptionPane.showMessageDialog(this, "ERROR: fileの保存先を指定してください");
                return;
            }
            if (Input_Texts[1].getText().equals("") == true) {
                JOptionPane.showMessageDialog(this, "ERROR: file名を入力してください");
                return;
            }
            if (Input_Texts[0].getText().equals("") == true) {
                JOptionPane.showMessageDialog(this, "ERROR: Keywordを入力してください");
                return;
            }
            if (!(Web_radio_Buttons[0].isSelected() || Web_radio_Buttons[1].isSelected())) {
                JOptionPane.showMessageDialog(this, "ERROR: 検索する種類を選択してください");
                return;
            }
            else Opening();
        }
        else if (e.getActionCommand().equals("CSV")) {
            JFileChooser filechooser = new JFileChooser("c:");
            filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int selected = filechooser.showSaveDialog(this);
            if (selected == JFileChooser.FILES_ONLY){
            Fcsv = filechooser.getSelectedFile();
            }
        }
        else if (e.getActionCommand().equals("Print")) {
            if (Fcsv == null) {
                JOptionPane.showMessageDialog(this, "ERROR: CSVファイルを指定してください。");
            }
            else {
                JOptionPane.showMessageDialog(this, "しばらくお待ちください");
                GReload();
                JOptionPane.showMessageDialog(this, "終了しました");
            }
        }
        else if (e.getActionCommand().equals("Show")) {
            if (Fcsv == null) {
                JOptionPane.showMessageDialog(this, "ERROR: CSVファイルを指定してください。");
            }
            else {
                JOptionPane.showMessageDialog(this, "しばらくお待ちください");
                TReload();
                JOptionPane.showMessageDialog(this, "終了しました");
            }
        }
        else if (e.getActionCommand().equals("Stop")) {
            if (Thread_Plus <= 0) {
                JOptionPane.showMessageDialog(this, "起動しているスレッドはありません。");
                return;
            }
            Thread_List.get(Thread_End).ChengeThread();
            JOptionPane.showMessageDialog(this, "Thread: "+Thread_End+"を停止しました。");
            AreaPrint("Thread: "+Thread_End+"を停止しました。\n");
            Thread_End++;
        }
    }
}