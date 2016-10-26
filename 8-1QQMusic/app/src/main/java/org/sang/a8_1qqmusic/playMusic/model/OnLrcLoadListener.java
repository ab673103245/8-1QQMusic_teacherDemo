package org.sang.a8_1qqmusic.playMusic.model;

import org.sang.lrcview.bean.LrcBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/20.
 */

public interface OnLrcLoadListener {
    void onLoadSuccess(List<LrcBean> lrcBeen);

    void onLoadFailed(String msg);
}
