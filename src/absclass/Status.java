package absclass;

import org.jsoup.nodes.Document;

public class Status {
    /* 共通部分 */
    private Document doc;
    private int page = 1; //ページ数

    private int Shipping; // 送料(無料1,負担0)
    private int Anonymous; //匿名
    private int Convenience; //コンビニ受け取り
    private int Conditions; // 状態(新品1、中古0)
    private int min; // 値段最小値
    private int max; // 値段最大値
    private int Sale; // 販売中か否か

    private String keyword; //キーワード
    private String FileName; //ファイルネーム

    /* yahoo */
    private int timefirst; // 一時間以内(yes1,no0)
    private String conb;

    /* メルカリ */
    private String AnonymousString;

    public Status() {

    }

    public int getAnonymous() {
        return Anonymous;
    }
    public String getAnonymousString() {
        return AnonymousString;
    }
    public String getConb() {
        return conb;
    }
    public int getConditions() {
        return Conditions;
    }
    public int getConvenience() {
        return Convenience;
    }
    public Document getDoc() {
        return doc;
    }
    public String getFileName() {
        return FileName;
    }
    public String getKeyword() {
        return keyword;
    }
    public int getMax() {
        return max;
    }
    public int getMin() {
        return min;
    }
    public int getPage() {
        return page;
    }
    public int getSale() {
        return Sale;
    }
    public int getShipping() {
        return Shipping;
    }
    public int getTimefirst() {
        return timefirst;
    }

    public void setAnonymous(int anonymous) {
        Anonymous = anonymous;
    }
    public void setAnonymousString(String anonymousString) {
        AnonymousString = anonymousString;
    }
    public void setConb(String conb) {
        this.conb = conb;
    }
    public void setConditions(int conditions) {
        Conditions = conditions;
    }
    public void setConvenience(int convenience) {
        Convenience = convenience;
    }
    public void setDoc(Document doc) {
        this.doc = doc;
    }
    public void setFileName(String fileName) {
        FileName = fileName;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public void setSale(int sale) {
        Sale = sale;
    }
    public void setShipping(int shipping) {
        Shipping = shipping;
    }
    public void setTimefirst(int timefirst) {
        this.timefirst = timefirst;
    }

    @Override
    public String toString() {
        return "Status: "+Anonymous+Conditions+Convenience+FileName+Sale+Shipping+keyword+max+min+page+timefirst;
    }
}