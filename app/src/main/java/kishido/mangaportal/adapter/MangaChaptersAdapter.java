package kishido.mangaportal.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kishido.mangaportal.R;
import kishido.mangaportal.model.Manga;
import kishido.mangaportal.model.MangaChapter;

/**
 * Created by syspaulo on 9/29/2015.
 */
public class MangaChaptersAdapter extends BaseAdapter {

    private Context context;
    private List<MangaChapter> chapterList;

    public MangaChaptersAdapter(Context context) {
        this.context = context;
        this.chapterList = new ArrayList<MangaChapter>();
    }

    public void add(List<MangaChapter> chapterList) {
        chapterList.addAll(chapterList);
    }

    public void clear() {
        chapterList.clear();
    }

    @Override
    public int getCount() {
        return chapterList.size();
    }

    @Override
    public Object getItem(int i) {
        return chapterList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView tv;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
            tv = (TextView) view.findViewById(android.R.id.text1);
            view.setTag(tv);
        } else {
            tv = (TextView) view.getTag();
        }

        MangaChapter chapter = chapterList.get(i);

        tv.setText("Chapter " + chapter.getNumber() + " - " + Html.fromHtml(chapter.getName()).toString());

        return view;
    }
}
