package kishido.mangaportal.crawler;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by syspaulo on 9/27/2015.
 */
public class Crawler {

    public enum Site {

        MANGA_READER("http://www.mangareader.net", "popular", "latest"),

        MANGA_FOX("http://www.mangafox.me", "directory", "releases");

        private String url;

        private String popular;
        private String latest;

        Site(String url, String popular, String latest) {
            this.url = url;
            this.popular = popular;
            this.latest = latest;
        }

        public String getUrl() {
            return url;
        }
        public String getPopular() {
            return popular;
        }

        public String getLatest() {
            return latest;
        }

    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void lookup(Site site, final CrawlAlgorithm algorithm) {
        client.get(site.getUrl() + "/" + site.getPopular(), null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                algorithm.error(new Exception(throwable.getMessage()));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                algorithm.list(responseString);
            }
        });
    }
}
