package org.sang.a8_1qqmusic.playMusic.presenter;

import android.os.Handler;

import org.sang.a8_1qqmusic.BasePresenter;
import org.sang.a8_1qqmusic.playMusic.model.IPlayerData;
import org.sang.a8_1qqmusic.playMusic.model.IPlayerDataImpl;
import org.sang.a8_1qqmusic.playMusic.model.OnLrcLoadListener;
import org.sang.a8_1qqmusic.playMusic.view.IPlayerView;
import org.sang.lrcview.bean.LrcBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/20.
 */

public class PlayerPresenter implements BasePresenter {
    private IPlayerView iPlayerView;
    private IPlayerData iPlayerData;
    private Handler mHandler = new Handler();

    public PlayerPresenter(IPlayerView iPlayerView) {
        this.iPlayerView = iPlayerView;
        iPlayerData = new IPlayerDataImpl();
    }

    @Override
    public void start() {
        iPlayerView.updatePlayerControl();
    }

    public void loadLrc(int id) {
        iPlayerData.loadLrc(id, new OnLrcLoadListener() {
            @Override
            public void onLoadSuccess(final List<LrcBean> lrcBeen) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iPlayerView.initLrcView(lrcBeen);
                    }
                });
            }

            @Override
            public void onLoadFailed(String msg) {

            }
        });
    }
}
