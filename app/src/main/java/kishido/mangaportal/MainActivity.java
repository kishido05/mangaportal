package kishido.mangaportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import kishido.mangaportal.adapter.MangaListAdapter;
import kishido.mangaportal.crawler.CrawlListener;
import kishido.mangaportal.crawler.Crawler;
import kishido.mangaportal.crawler.impl.MangafoxCrawlAlgorithm;
import kishido.mangaportal.model.Manga;
import kishido.mangaportal.model.MangaChapter;

/**
 * Created by syspaulo on 9/27/2015.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, CrawlListener {

    private MangaListAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);

        ListView list = (ListView) findViewById(R.id.listManga);
        arrayAdapter = new MangaListAdapter(this);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(this);

        Crawler.lookup(Crawler.Site.MANGA_FOX, new MangafoxCrawlAlgorithm(this));
    }

    @Override
    public void onMangaListObtained(List<Manga> mangaList) {
        arrayAdapter.add(mangaList);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChapterListObtained(String status, String author, String desc, List<MangaChapter> chapterList) {
        // do nothing
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
