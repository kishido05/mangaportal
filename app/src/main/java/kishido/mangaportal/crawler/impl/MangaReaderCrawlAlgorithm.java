package kishido.mangaportal.crawler.impl;

import android.text.Html;

import java.util.ArrayList;
import java.util.List;

import kishido.mangaportal.crawler.CrawlAlgorithm;
import kishido.mangaportal.crawler.CrawlListener;
import kishido.mangaportal.model.Manga;

/**
 * Created by syspaulo on 10/1/2015.
 */
public class MangaReaderCrawlAlgorithm extends CrawlAlgorithm {

    public MangaReaderCrawlAlgorithm(CrawlListener listener) {
        super(listener);
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void list(String response) {
        String source = response.substring(
                response.indexOf("id=\"bodyalt"),
                response.indexOf("id=\"navigator"))
                .replace("\t", "");

        String[] parse = source.split("\n");

        List<String> filtered = new ArrayList<String>();

        for (int i=0; i<parse.length; i++) {
            if (parse[i].contains("<h3>") || parse[i].contains("imgsearchresults")) {
                filtered.add(parse[i]);
            }
        }

        List<Manga> mangaList = new ArrayList<Manga>();

        for (int i=0; i+1<filtered.size(); i+=2) {
            Manga manga = new Manga();

            String s1 = filtered.get(i);
            String s2 = filtered.get(i+1);

            String image = s1.substring(s1.indexOf("http"), s1.lastIndexOf("'"));
            String url = "http://www.mangareader.net" + s2.substring(s2.indexOf("/"), s2.lastIndexOf("\""));
            String name = Html.fromHtml(s2).toString();

            manga.setName(name);
            manga.setImageUrl(image);
            manga.setIndexUrl(url);

            mangaList.add(manga);
        }

        if (listener != null) {
            listener.onMangaListObtained(mangaList);
        }
    }

    @Override
    public void view(String response) {

    }
}
