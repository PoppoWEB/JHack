package Play;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import absclass.JHack;
import absclass.Status;

public class YahooHack extends Thread implements JHack {

    private PrintWriter pw; 
    private Status stat;
    private JFrame jf;
    private int NUMBER_THREAD;
    private boolean ELIFH;

    private List<String> title;
    private List<String> url;
    private List<String> type;
    private List<String> price;
    private List<String> other;
    private List<String> other2;
    private List<String> time;
    
    public YahooHack(Status stat, JFrame jf,int i) {
        this.jf = jf;
        this.stat = stat;

        this.stat.setKeyword(this.stat.getKeyword().replaceAll(" ", "+"));

        ELIFH = true;
        NUMBER_THREAD = i;

        if (stat.getTimefirst() != 1) {
            stat.setTimefirst(1000);
        }

        if (stat.getConvenience() == 1) {
            stat.setConb("113%2C114%2C115%2C116");
        }
        else stat.setConb("NULL");
        this.stat.setPage(1);
        
        try {
            FileWriter fw = new FileWriter(stat.getFileName()+".csv");
            pw = new PrintWriter(new BufferedWriter(fw));

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void run() {
        Start();
    }
    
    @Override
    public String Start() {
        return Search("https://auctions.yahoo.co.jp/search/search?p="+stat.getKeyword()+"&price_type=currentprice&new="+stat.getConditions()+"&pstagefree="+stat.getShipping()+"&exflg=1&n=50&auchours="+stat.getTimefirst()+"&privacy_delivery="+stat.getAnonymous()+"&min="+stat.getMin()+"&max="+stat.getMax()+"&shipping="+stat.getConb()+"&b=");
    }

    @Override
    public String Search(String url) {
        pw.println(KEY_SET);
        pw.println("ROOT URL: "+url+stat.getPage()+"\n");
        try {
            while(ELIFH) {
                stat.setDoc(Jsoup.connect(url+stat.getPage()).timeout(10000).get());
                
                int res = Dump(stat.getDoc());
                if (res == Failure) { 
                    pw.println("X: \n");
                    pw.close();
                    JOptionPane.showMessageDialog(jf,"Thread: "+NUMBER_THREAD+" END");
                    break;
                }
                
                stat.setPage(stat.getPage()+50);
                Thread.sleep(5000);           
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return url;
    }
    
    @Override
    public int Dump(Document doc) {
        
        title = doc.select(".Product__detail h3 a").eachText(); //タイトル
        url = doc.select(".Product__detail h3 a").eachAttr("href"); // url
        
        type = doc.select(".Product__detail .Product__price .Product__label").eachText(); // 種類(オークション, フリマ)
        price = doc.select(".Product__detail .Product__price .Product__priceValue").eachText(); // 値段
        
        other = doc.select(".Product__detail .Product__otherInfo .Product__label").eachText();
        other2 = doc.select(".Product__detail .Product__otherInfo .Product__bid").eachText();
        
        time = doc.select(".Product__detail .Product__time").eachText(); //残り時間
        
        if (title.size() == 0) return Failure; // NULL

        for (int i = 0; i < title.size(); i++) {
            pw.println("A:"+title.get(i));
            pw.println("B:"+url.get(i));
            pw.println("C:"+type.get(i));
            pw.println("D:"+price.get(i));
            pw.println("E:"+other.get(i)+" : "+other2.get(i));
            pw.println("F: "+time.get(i));
            pw.println("X: \n");
        }

        return Success;
    }

    public void ChengeThread() {
        ELIFH = false;
    }
}