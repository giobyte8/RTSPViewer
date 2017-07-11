package org.rtspviewer.streams.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.rtspviewer.R;
import org.rtspviewer.databinding.FragStreamsBinding;
import org.rtspviewer.db.Stream;
import org.rtspviewer.player.PlayerFragment;
import org.rtspviewer.player.PlayerPresenter;
import org.rtspviewer.streams.form.StreamFormFragment;
import org.rtspviewer.streams.form.StreamFormPresenter;

import io.realm.RealmResults;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class StreamsFragment extends Fragment implements StreamsContract.View {
    private static final int CODE_ADD_STREAM = 2013;

    private StreamsContract.Presenter mPresenter;
    private FragStreamsBinding viewBinding;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.streams));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            // When dialog fragment with form creates a new stream and closes
            case CODE_ADD_STREAM:
                if (resultCode == Activity.RESULT_OK) {
                    mPresenter.loadStreams();
                }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_streams, container, false);
        viewBinding = FragStreamsBinding.bind(rootView);

        mPresenter.loadStreams();
        setupAddStreamButton();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void setPresenter(StreamsContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void goToStream(Stream stream) {
        Bundle args = new Bundle();
        args.putString(PlayerFragment.STREAM_ID, stream.getId());

        PlayerFragment playerFragment = new PlayerFragment();
        playerFragment.setArguments(args);
        new PlayerPresenter(playerFragment);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, playerFragment)
                .addToBackStack("playerFragment")
                .commit();
    }

    @Override
    public void setupAddStreamButton() {
        viewBinding.fabAddStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StreamFormFragment formFragment = new StreamFormFragment();
                new StreamFormPresenter(formFragment);

                formFragment.setTargetFragment(StreamsFragment.this, CODE_ADD_STREAM);
                formFragment.show(getFragmentManager(), "streamForm");
            }
        });
    }

    @Override
    public void updateStreamsList(RealmResults<Stream> streams) {
        if (streams.size() > 0) {
            final StreamsAdapter streamsAdapter = new StreamsAdapter(getContext(), streams);
            viewBinding.lvStreams.setAdapter(streamsAdapter);
            viewBinding.lvStreams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    goToStream(streamsAdapter.getStream(i));
                }
            });
        } else {
            // TODO Show empty icon
        }
    }
}
