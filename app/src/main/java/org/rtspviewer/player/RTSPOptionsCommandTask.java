package org.rtspviewer.player;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by giovanni on 11/4/15.
 */
public class RTSPOptionsCommandTask extends AsyncTask<Uri, Void, Void> {

    RTSPControl rtspControl;

    protected Void doInBackground(Uri... uris) {

        Log.d("RTSPControl", "Executing AsyncTask");
        rtspControl = new RTSPControl(uris[0].toString());
        rtspControl.RTSPOptions();

        return  null;
    }
}
