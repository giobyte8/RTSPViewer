package org.rtspviewer.streams.form;

import org.rtspviewer.db.Stream;
import org.rtspviewer.db.StreamService;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class StreamFormPresenter implements StreamFormContract.StreamFormPresenter {

    private StreamService streamService;

    public StreamFormPresenter(StreamFormContract.StreamFormView streamFormView) {
        StreamFormContract.StreamFormView streamFormView1 = streamFormView;
        streamFormView.setPresenter(this);

        streamService = new StreamService();
    }

    @Override
    public void destroy() {
        streamService.close();
    }

    @Override
    public void save(Stream stream) {
        streamService.save(stream);
    }
}
