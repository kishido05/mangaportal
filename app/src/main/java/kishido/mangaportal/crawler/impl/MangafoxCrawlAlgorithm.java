package kishido.mangaportal.crawler.impl;

import java.util.ArrayList;
import java.util.List;

import kishido.mangaportal.crawler.CrawlAlgorithm;
import kishido.mangaportal.crawler.CrawlListener;
import kishido.mangaportal.model.Manga;
import kishido.mangaportal.model.MangaChapter;

/**
 * Created by syspaulo on 9/29/2015.
 */
public class MangafoxCrawlAlgorithm extends CrawlAlgorithm {

    public MangafoxCrawlAlgorithm(CrawlListener listener) {
        super(listener);
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void list(String response) {
        String source = response.substring(
                response.indexOf("<div class=\"left\" id=\"content\">"),
                response.indexOf("<div class=\"left\" id=\"sidebar\">"))
                .replace("\t", "");
        String[] parse = source.split("\n");

        List<String> filtered = new ArrayList<String>();

        for (int i=0; i<parse.length; i++) {
            if (parse[i].contains("class=\"title\"") || parse[i].contains("class=\"manga_img\"")) {
                filtered.add(parse[i]);
            }
        }

        List<Manga> mangaList = new ArrayList<Manga>();

        for (int i=0; i+1<filtered.size(); i+=2) {
            Manga manga = new Manga();

            String s1 = filtered.get(i);
            String s2 = filtered.get(i+1);

            String image = s1.substring(s1.lastIndexOf("http"), s1.lastIndexOf("\""));
            String url = s2.substring(s2.indexOf("http"), s2.indexOf("rel"));
            String name = s2.substring(s2.lastIndexOf("\">"), s2.lastIndexOf("<"));

            image = image.substring(0, image.indexOf("\""));
            url = url.substring(0, url.lastIndexOf("/"));
            name = name.substring(name.indexOf(">") + 1);

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
        String source = response.substring(
                response.indexOf("<div id=\"chapters\""),
                response.indexOf("<div id=\"discussion\""))
                .replace("\t", "");

        String[] parse = source.split("\n");

        List<String> filtered = new ArrayList<String>();

        for (int i=0; i<parse.length; i++) {
            if (parse[i].contains("/manga/") || parse[i].contains("class=\"title nowrap")
                    || parse[i].contains("class=\"newch")) {
                filtered.add(parse[i]);
            }
        }

        List<MangaChapter> chapterList = new ArrayList<MangaChapter>();

        for (int i=0; i+1<filtered.size(); i+=2) {
            MangaChapter chapter = new MangaChapter();

            String s1 = filtered.get(i);
            String s2 = filtered.get(i+1);

            String url = s1.substring(s1.indexOf("http"), s1.indexOf("title"));
            String num = s1.substring(s1.indexOf("tips"), s1.lastIndexOf("<"));
            String name = s2.substring(s2.indexOf(">"), s2.lastIndexOf("<"));

            url = url.substring(0, url.indexOf("\""));
            num = num.substring(num.lastIndexOf(" ") + 1);
            name = name.substring(1, name.indexOf("<"));

            chapter.setUrl(url);
            chapter.setNumber(num);
            chapter.setName(name);

            chapterList.add(chapter);
        }

        if (listener != null) {
            listener.onChapterListObtained(chapterList);
        }
    }
}
