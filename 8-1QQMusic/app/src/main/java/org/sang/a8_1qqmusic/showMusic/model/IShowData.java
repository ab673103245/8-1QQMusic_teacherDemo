package org.sang.a8_1qqmusic.showMusic.model;

import android.content.Context;

import org.sang.a8_1qqmusic.showMusic.model.bean.MusicBean;
import org.sang.a8_1qqmusic.showMusic.model.bean.UserBean;

/**
 * Created by 王松 on 2016/10/18.
 */

public interface IShowData {
    void saveData(MusicBean musicBean, Context context);

    MusicBean loadData(Context context);


}
