package kishido.mangaportal.crawler;

/**
 * Created by syspaulo on 9/29/2015.
 */
public interface CrawlAlgorithm {

    public void error(Exception e);

    public void list(String response);

    public void view(String response);
}
