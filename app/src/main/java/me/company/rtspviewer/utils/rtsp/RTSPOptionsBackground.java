package me.company.rtspviewer.utils.rtsp;

import android.util.Log;

/**
 * Created by giovanni on 11/5/15.
 */
public class RTSPOptionsBackground extends Thread {

    private String targetUrl;
    private RTSPControl rtspControl;

    public RTSPOptionsBackground(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public void run() {

        while (true) {
            try {
                this.sleep(5000);
                Log.d("THREAD", "Background task");
                if(rtspControl == null) {
                    this.rtspControl = new RTSPControl(targetUrl);
                    this.rtspControl.RTSPOptions();
                }
                else {
                    this.rtspControl.RTSPOptions();
                }
            }
            catch (Exception err) {

            }
        }
    }
}
