package me.company.rtspviewer.fragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

import me.company.rtspviewer.utils.rtsp.RTSPOptionsCommandTask;
import rtspviewer.company.me.rtspviewer.R;

/**
 * Created by giovanni on 11/4/15.
 */
public class StreamViewerFragment extends Fragment {

    Timer rtspOptionsCommandTimer = new Timer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View rootView = inflater.inflate(R.layout.fragment_streamviewer, container, false);

        // Start to receiving streaming
        VideoView streamVideoView = (VideoView) rootView.findViewById(R.id.streamPlayerVV);
        this.startStreamingPlayback(streamVideoView);

        return rootView;
    }


    /**********************************************************************************************/

    /**
     * Initializes the stream playback on a videoView.
     * Streaming is received over RTSP Connection.
     *
     * @param videoView A videoView to render video stream
     */
    public void startStreamingPlayback(VideoView videoView) {

        // Url to fetch stream
        String streamUrl = "rtsp://192.168.100.14:8554/livestream";
        Uri liveStreamUri = Uri.parse(streamUrl);

        // Configure media controller
        MediaController mediaController = new MediaController(getActivity().getApplicationContext());
        mediaController.setMediaPlayer(videoView);

        // Configure video view to fetch stream
        /*videoView.setMediaController(mediaController);
        videoView.setVideoURI(liveStreamUri);
        videoView.start();*/

        // Start video on another intent
        Intent intent = new Intent(Intent.ACTION_VIEW, liveStreamUri);
        startActivity(intent);

        // Start a background task every 40 secs sending
        // an OPTIONS Request to rtsp server to keep alive the connection
        //startRTSPOptionsPeriodically(streamUrl);
    }

    public void startRTSPOptionsPeriodically(final String targetUri) {

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                Uri[] uris = new Uri[]{Uri.parse(targetUri)};
                new RTSPOptionsCommandTask().execute(uris);
            }
        };
        rtspOptionsCommandTimer.schedule(timerTask, 40000, 40000);
    }
}
