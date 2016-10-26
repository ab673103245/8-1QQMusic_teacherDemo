package org.sang.a8_1qqmusic.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sang.a8_1qqmusic.showMusic.model.bean.MusicBean;
import org.sang.lrcview.bean.LrcBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2016/10/19.
 */

public class JsonParse {
    public static List<MusicBean> parseJson2List(String json) {
        List<MusicBean> list = new ArrayList<>();
        try {
            JSONArray songList = new JSONObject(json).getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("songlist");
            for (int i = 0; i < songList.length(); i++) {
                JSONObject data = songList.getJSONObject(i);
                String songname = data.getString("songname");
                int seconds = data.getInt("seconds");
                String albummid = data.getString("albummid");
                String albumpic_big = data.getString("albumpic_big");
                String albumpic_small = data.getString("albumpic_small");
                String downUrl = data.getString("downUrl");
                String url = data.getString("url");
                String singername = data.getString("singername");
                int songid = data.getInt("songid");
                int singerid = data.getInt("singerid");
                int albumid = data.getInt("albumid");
                list.add(new MusicBean(songname, seconds, albummid, songid, singerid, albumpic_big, albumpic_small, downUrl, url, singername, albumid));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LrcBean> parseLrc2List(String json) {
        List<LrcBean> list = new ArrayList<>();
        try {
            String lrcText = new JSONObject(json).getJSONObject("showapi_res_body")
                    .getString("lyric")
                    .replaceAll("&#58;", ":")
                    .replaceAll("&#10;", "\n")
                    .replaceAll("&#46;", ".")
                    .replaceAll("&#32;", " ")
                    .replaceAll("&#45;", "-")
                    .replaceAll("&#13;", "\r").replaceAll("&#39;","'");
            String[] split = lrcText.split("\n");
            for (int i = 0; i < split.length; i++) {
                String lrc = split[i];
                if (lrc.contains(".")) {
                    String min = lrc.substring(lrc.indexOf("[") + 1, lrc.indexOf("[") + 3);
                    String seconds = lrc.substring(lrc.indexOf(":") + 1, lrc.indexOf(":") + 3);
                    String mills = lrc.substring(lrc.indexOf(".") + 1, lrc.indexOf(".") + 3);
                    long startTime = Long.valueOf(min) * 60 * 1000 + Long.valueOf(seconds) * 1000 + Long.valueOf(mills) * 10;
                    String text = lrc.substring(lrc.indexOf("]") + 1);
                    if (text == null || "".equals(text)) {
                        text = "music";
                    }
                    LrcBean lrcBean = new LrcBean();
                    lrcBean.setStart(startTime);
                    lrcBean.setText(text);
                    list.add(lrcBean);
                    if (list.size() > 1) {
                        list.get(list.size() - 2).setEnd(startTime);
                    }
                    if (i == split.length - 1) {
                        list.get(list.size() - 1).setEnd(startTime + 100000);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
