package org.sang.a8_1qqmusic.localMusic.presenter;

import android.content.Context;

import org.sang.a8_1qqmusic.BasePresenter;
import org.sang.a8_1qqmusic.localMusic.model.ILocalData;
import org.sang.a8_1qqmusic.localMusic.model.ILocalDataImpl;
import org.sang.a8_1qqmusic.localMusic.view.fragment.ILocalMusicView;

/**
 * Created by 王松 on 2016/10/18.
 */

public class LocalMusicPresenter implements BasePresenter {
    private ILocalMusicView iLocalMusicView;
    private ILocalData iLocalData;

    public LocalMusicPresenter(ILocalMusicView iLocalMusicView) {
        this.iLocalMusicView = iLocalMusicView;
        iLocalData = new ILocalDataImpl();
    }

    public void start(Context context) {
        iLocalMusicView.initLv(iLocalData.getLocalMusic(context));
    }

    @Override
    public void start() {
    }
}
