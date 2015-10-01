package kishido.mangaportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import kishido.mangaportal.adapter.MangaChaptersAdapter;
import kishido.mangaportal.adapter.MangaListAdapter;
import kishido.mangaportal.crawler.Crawler;
import kishido.mangaportal.crawler.impl.MangafoxCrawlAlgorithm;
import kishido.mangaportal.model.Manga;

/**
 * Created by syspaulo on 10/1/2015.
 */
public class MangaInfoActivity extends AppCompatActivity {

    private MangaChaptersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_manga_info);

        ListView list = (ListView) findViewById(R.id.listManga);
        adapter = new MangaChaptersAdapter(this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(adapter);

        Manga manga = (Manga) getIntent().getSerializableExtra("manga_info");

        setTitle(manga.getName());

        Crawler.viewManga(manga.getIndexUrl(), new MangafoxCrawlAlgorithm(this, adapter));
    }
}
