package org.rtspviewer.player;

import org.rtspviewer.db.StreamService;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class PlayerPresenter implements PlayerContract.Presenter {
    private PlayerContract.View playerView;
    private StreamService streamService;

    public PlayerPresenter(PlayerContract.View playerView) {
        this.playerView = playerView;
        playerView.setPresenter(this);

        streamService = new StreamService();
    }

    @Override
    public void destroy() {
        streamService.close();
    }

    @Override
    public void loadStream(String id) {
        playerView.playStream(streamService.find(id));
    }
}
