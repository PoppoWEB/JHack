package absclass;
import org.jsoup.nodes.Document;

public interface JHack {
    public final int Success = 1;
    public final int Failure = -1;
    public final String KEY_SET = "#Y2021TOSWINC#";

    String Start();
    String Search(String url);
    int Dump(Document doc);
}