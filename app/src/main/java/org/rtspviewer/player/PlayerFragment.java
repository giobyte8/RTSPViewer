package org.rtspviewer.player;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import org.rtspviewer.R;
import org.rtspviewer.databinding.FragViewerBinding;
import org.rtspviewer.db.Stream;
import org.rtspviewer.player.util.RTSPOptionsBackground;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class PlayerFragment extends Fragment implements PlayerContract.View {
    public static final String STREAM_ID = "STREAM_ID";

    private PlayerContract.Presenter mPresenter;
    private FragViewerBinding viewBinding;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.view_stream));
            toolbar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_viewer, container, false);
        viewBinding = FragViewerBinding.bind(rootView);

        // Retrieve stream
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(STREAM_ID)) {
            String id = arguments.getString(STREAM_ID);
            mPresenter.loadStream(id);
        } else {
            Log.e(getClass().getName(), "Required bundle arguments were not found");
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void setPresenter(PlayerContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void playStream(Stream stream) {
        if (stream != null) {
            startStreamingPlayback(stream.getUrl());
            viewBinding.tvName.setText(stream.getName());
        }
    }

    public void startStreamingPlayback(String streamUrl) {

        // Url to fetch stream
//        String streamUrl = "rtsp://mpv.cdn3.bigCDN.com:554/bigCDN/_definst_/mp4:bigbuckbunnyiphone_400.mp4";
        Uri liveStreamUri = Uri.parse(streamUrl);

        // Configure media controller
        MediaController mediaController = new MediaController(getActivity().getApplicationContext());
        mediaController.setMediaPlayer(viewBinding.vvPlayer);

        // Configure video view to fetch stream
        viewBinding.vvPlayer.setMediaController(mediaController);
        viewBinding.vvPlayer.setVideoURI(liveStreamUri);
        viewBinding.vvPlayer.start();

        // Start video on another intent
        //Intent intent = new Intent(Intent.ACTION_VIEW, liveStreamUri);
        //startActivity(intent);

        // Start a background task every 40 secs sending
        // an OPTIONS Request to rtsp server to keep alive the connection
        startRTSPOptionsPeriodically(streamUrl);
    }

    public void startRTSPOptionsPeriodically(final String targetUri) {
        RTSPOptionsBackground rtspOptionsBackground = new RTSPOptionsBackground(targetUri);
        rtspOptionsBackground.start();
    }
}
