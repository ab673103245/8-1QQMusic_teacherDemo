package org.sang.a8_1qqmusic.playMusic.view;

import org.sang.lrcview.bean.LrcBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/20.
 */

public interface IPlayerView {
    void updatePlayerControl();

    void initLrcView(List<LrcBean> list);
}
