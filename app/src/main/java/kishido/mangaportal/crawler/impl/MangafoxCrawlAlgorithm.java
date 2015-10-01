package kishido.mangaportal.crawler.impl;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import kishido.mangaportal.adapter.MangaListAdapter;
import kishido.mangaportal.crawler.CrawlAlgorithm;
import kishido.mangaportal.model.Manga;

/**
 * Created by syspaulo on 9/29/2015.
 */
public class MangafoxCrawlAlgorithm implements CrawlAlgorithm, AdapterView.OnItemClickListener {

    protected MangaListAdapter adapter;

    public MangafoxCrawlAlgorithm(MangaListAdapter adapter) {
        this.adapter = adapter;
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

            adapter.add(manga);
        }

        adapter.notifyDataSetChanged();
        adapter.setListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
