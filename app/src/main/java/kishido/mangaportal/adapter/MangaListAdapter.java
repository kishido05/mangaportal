package kishido.mangaportal.adapter;

import android.content.Context;
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

/**
 * Created by syspaulo on 9/29/2015.
 */
public class MangaListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private List<Manga> mangaList;

    private boolean isList = true;

    protected AdapterView.OnItemClickListener listener;

    public MangaListAdapter(Context context) {
        this.context = context;
        this.mangaList = new ArrayList<Manga>();
    }

    public void add(Manga manga) {
        mangaList.add(manga);
    }

    public void clear() {
        mangaList.clear();
    }

    public void setListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mangaList.size();
    }

    @Override
    public Object getItem(int i) {
        return mangaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_manga, null);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.nameManga);
            holder.image = (ImageView) view.findViewById(R.id.imageManga);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Manga m = mangaList.get(i);
        holder.name.setText(m.getName());
        Glide.with(context)
                .load(m.getImageUrl())
                .override(200, 300)
                .into(holder.image);

        return view;
    }

    // true = ListView
    // false = GridView
    public void setType(boolean isList) {
        this.isList = isList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (listener != null) {
            listener.onItemClick(adapterView, view, i, l);
        }
    }

    static class ViewHolder {

        TextView name;
        ImageView image;
    }
}
