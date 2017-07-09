package org.rtspviewer.streams.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.rtspviewer.R;
import org.rtspviewer.db.Stream;

import io.realm.RealmResults;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class StreamsAdapter extends BaseAdapter {

    private RealmResults<Stream> streams;
    private LayoutInflater inflater;

    public StreamsAdapter(Context mContext, RealmResults<Stream> streams) {
        this.streams = streams;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return streams.size();
    }

    @Override
    public Object getItem(int i) {
        return streams.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Stream stream = streams.get(position);

        if (view == null) {
            view = inflater.inflate(R.layout.item_stream, viewGroup, false);
        }

        // Load name
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(stream.getName());

        // Load url
        TextView tvUrl = (TextView) view.findViewById(R.id.tv_url);
        tvUrl.setText(stream.getUrl());

        return view;
    }

    public Stream getStream(int position) {
        return streams.get(position);
    }
}
