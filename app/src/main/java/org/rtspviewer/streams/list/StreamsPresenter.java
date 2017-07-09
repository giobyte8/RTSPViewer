package org.rtspviewer.streams.list;

import org.rtspviewer.db.StreamService;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class StreamsPresenter implements StreamsContract.Presenter {

    private StreamsContract.View streamsView;
    private StreamService streamService;

    public StreamsPresenter(StreamsContract.View streamsView) {
        this.streamsView = streamsView;
        streamsView.setPresenter(this);

        streamService = new StreamService();
    }

    @Override
    public void destroy() {
        streamService.close();
    }

    @Override
    public void loadStreams() {
        streamsView.updateStreamsList(streamService.findAll());
    }
}
