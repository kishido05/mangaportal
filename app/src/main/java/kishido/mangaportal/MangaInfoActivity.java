package kishido.mangaportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import kishido.mangaportal.adapter.MangaChaptersAdapter;
import kishido.mangaportal.adapter.MangaListAdapter;
import kishido.mangaportal.crawler.CrawlListener;
import kishido.mangaportal.crawler.Crawler;
import kishido.mangaportal.crawler.impl.MangafoxCrawlAlgorithm;
import kishido.mangaportal.model.Manga;
import kishido.mangaportal.model.MangaChapter;

/**
 * Created by syspaulo on 10/1/2015.
 */
public class MangaInfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, CrawlListener {

    private MangaChaptersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_manga_info);

        ListView list = (ListView) findViewById(R.id.listManga);
        adapter = new MangaChaptersAdapter(this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        Manga manga = (Manga) getIntent().getSerializableExtra("manga_info");

        setTitle(manga.getName());

        Crawler.viewManga(manga.getIndexUrl(), new MangafoxCrawlAlgorithm(this));
    }

    @Override
    public void onMangaListObtained(List<Manga> mangaList) {
        // do nothing
    }

    @Override
    public void onChapterListObtained(String status, String author, String desc, List<MangaChapter> chapterList) {
        adapter.add(chapterList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
