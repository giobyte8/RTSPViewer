package org.rtspviewer.player;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import org.rtspviewer.R;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class ViewerFragment extends Fragment {
    public static final String STREAM_ID = "STREAM_ID";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_viewer, container, false);

        // Start streaming playback
        VideoView videoView = (VideoView) rootView.findViewById(R.id.vv_player);
        startStreamingPlayback(videoView);

        return rootView;
    }

    public void startStreamingPlayback(VideoView videoView) {

        // Url to fetch stream
        String streamUrl = "rtsp://mpv.cdn3.bigCDN.com:554/bigCDN/_definst_/mp4:bigbuckbunnyiphone_400.mp4";
        Uri liveStreamUri = Uri.parse(streamUrl);

        // Configure media controller
        MediaController mediaController = new MediaController(getActivity().getApplicationContext());
        mediaController.setMediaPlayer(videoView);

        // Configure video view to fetch stream
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(liveStreamUri);
        videoView.start();

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
