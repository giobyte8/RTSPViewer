package me.company.rtspviewer.utils.rtsp;

import android.net.Uri;
import android.os.AsyncTask;

/**
 * Created by giovanni on 11/4/15.
 */
public class RTSPOptionsCommandTask extends AsyncTask<Uri, Void, Void> {

    RTSPControl rtspControl;

    protected Void doInBackground(Uri... uris) {

        rtspControl = new RTSPControl(uris[0].toString());
        rtspControl.RTSPOptions();

        return  null;
    }
}
