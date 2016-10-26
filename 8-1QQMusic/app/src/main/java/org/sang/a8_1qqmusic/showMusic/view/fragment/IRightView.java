package org.sang.a8_1qqmusic.showMusic.view.fragment;

import org.sang.a8_1qqmusic.showMusic.model.bean.MusicBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/19.
 */

public interface IRightView {
    void initRv(List<MusicBean> list);

    void showErrorMsg(String errorMsg);
}
