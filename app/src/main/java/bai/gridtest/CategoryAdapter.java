package bai.gridtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by next on 4/16/2017.
 */

public class CategoryAdapter extends BaseAdapter{
    private Context mContext;
    private final List<String> c_title;
    private final int Imageid;

    public CategoryAdapter(Context c,List<String> c_title ) {
        mContext = c;
        this.Imageid = R.drawable.add_category;
        this.c_title = c_title;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return c_title.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_category, null);

        } else {
            grid = (View) convertView;
        }

        TextView textView = (TextView) grid.findViewById(R.id.cname);
        ImageView imageView = (ImageView)grid.findViewById(R.id.cimage);
        textView.setText(c_title.get(position));
        imageView.setImageResource(Imageid);

        return grid;
    }
}
