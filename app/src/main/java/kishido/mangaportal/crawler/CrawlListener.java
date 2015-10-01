package kishido.mangaportal.crawler;

import java.util.List;

import kishido.mangaportal.model.Manga;
import kishido.mangaportal.model.MangaChapter;

/**
 * Created by syspaulo on 10/1/2015.
 */
public interface CrawlListener {

    public void onMangaListObtained(List<Manga> mangaList);

    public void onChapterListObtained(String status, String author, String desc, List<MangaChapter> chapterList);
}
