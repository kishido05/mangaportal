package kishido.mangaportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import kishido.mangaportal.adapter.MangaListAdapter;
import kishido.mangaportal.crawler.Crawler;
import kishido.mangaportal.crawler.impl.MangafoxCrawlAlgorithm;

/**
 * Created by syspaulo on 9/27/2015.
 */
public class MainActivity extends AppCompatActivity {

    private MangaListAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);

        ListView list = (ListView) findViewById(R.id.listManga);
        arrayAdapter = new MangaListAdapter(this);
        list.setAdapter(arrayAdapter);

        Crawler.lookup(Crawler.Site.MANGA_FOX, new MangafoxCrawlAlgorithm(arrayAdapter));
    }
}
