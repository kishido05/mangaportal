package kishido.mangaportal.crawler;

/**
 * Created by syspaulo on 9/29/2015.
 */
public abstract class CrawlAlgorithm {

    protected CrawlListener listener;

    public CrawlAlgorithm(CrawlListener listener) {
        this.listener = listener;
    }

    public abstract void error(Exception e);

    public abstract void list(String response);

    public abstract void view(String response);
}
