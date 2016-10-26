package org.sang.a8_1qqmusic.showMusic.presenter;

import android.content.Context;

import org.sang.a8_1qqmusic.BasePresenter;
import org.sang.a8_1qqmusic.showMusic.model.IShowData;
import org.sang.a8_1qqmusic.showMusic.model.IShowDataImpl;
import org.sang.a8_1qqmusic.showMusic.model.bean.MusicBean;
import org.sang.a8_1qqmusic.showMusic.view.IShowView;
import org.sang.a8_1qqmusic.util.PlayUtil;

/**
 * Created by 王松 on 2016/10/18.
 */

public class ShowPresenter implements BasePresenter {
    private IShowView iShowView;
    private IShowData iShowData;

    public ShowPresenter(IShowView iShowView) {
        this.iShowView = iShowView;
        iShowData = new IShowDataImpl();
    }

    public void saveData(MusicBean musicBean) {
        iShowData.saveData(musicBean, (Context) iShowView);
    }

    public void loadData(int type) {
        if (type== PlayUtil.LOCALMUSICBEAN) {
            iShowView.updateMainBottom(iShowData.loadData((Context) iShowView));
        } else if (type == PlayUtil.PLAYUTILMUSICBEAN) {
            iShowView.updateMainBottom(PlayUtil.currentMusic);
        }
    }

    @Override
    public void start() {

    }
}
