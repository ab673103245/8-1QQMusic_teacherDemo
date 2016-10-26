package org.sang.a8_1qqmusic.showMusic.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sang.a8_1qqmusic.BaseFragment;
import org.sang.a8_1qqmusic.LoginActivity;
import org.sang.a8_1qqmusic.R;
import org.sang.a8_1qqmusic.localMusic.view.LocalMusicActivity;
import org.sang.a8_1qqmusic.showMusic.model.bean.MusicBean;
import org.sang.a8_1qqmusic.showMusic.model.bean.UserBean;
import org.sang.a8_1qqmusic.showMusic.presenter.MinePresenter;
import org.sang.a8_1qqmusic.showMusic.view.IShowView;
import org.sang.a8_1qqmusic.showMusic.view.adapter.MineFgLvAdapter;
import org.sang.a8_1qqmusic.showMusic.view.view.MyListView;
import org.sang.a8_1qqmusic.util.LoginUtil;
import org.sang.a8_1qqmusic.util.PlayUtil;

import java.util.List;

import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by 王松 on 2016/10/17.
 */

public class MineFragment extends BaseFragment implements IMineView {
    private MinePresenter minePresenter = new MinePresenter(this);
    private MyListView lv;
    private IShowView iShowView;
    private Handler mHandler = new Handler();
    private TextView playListSizeTv;
    private LinearLayout localMusic;
    private TextView usernameTv;
    private ImageView userfaceIv;
    private UserBean userBean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IShowView) {
            iShowView = (IShowView) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fg_layout, container, false);
        initView(view);
        minePresenter.start();
        return view;
    }

    private void initView(View view) {
        lv = ((MyListView) view.findViewById(R.id.play_list_lv));
        usernameTv = ((TextView) view.findViewById(R.id.username));
        userfaceIv = ((ImageView) view.findViewById(R.id.userface));
        RelativeLayout loginLayout = (RelativeLayout) view.findViewById(R.id.loginLayout);
        playListSizeTv = ((TextView) view.findViewById(R.id.play_list_size_tv));
        localMusic = ((LinearLayout) view.findViewById(R.id.localMusic));
        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("请登录".equals(usernameTv.getText().toString())) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else{
                    AlertDialog dialog = new AlertDialog.Builder(getActivity())
                            .setMessage("退出登录?")
                            .setTitle("提示")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    QQ qq = new QQ(getActivity());
                                    qq.removeAccount();
                                    LoginUtil.saveData(null, getActivity());
                                    minePresenter.initLoginInfo(getActivity());
                                    updateUserInfo(MineFragment.this.userBean);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .create();
                    dialog.show();
                }
            }
        });
        localMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LocalMusicActivity.class));
            }
        });
    }

    @Override
    public void setPlayList(final List<MusicBean> list) {
        playListSizeTv.setText(list.size() + "");
        lv.setAdapter(new MineFgLvAdapter(list, getActivity()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                PlayUtil.startService(getActivity(), list.get(position), PlayUtil.PLAY);
                PlayUtil.CURRENT_POSITION = position;
                PlayUtil.CURRENT_PLAY_LIST = list;
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        iShowView.updateMainBottom(list.get(position));
//                    }
//                }, 500);
            }
        });
    }

    @Override
    public void initLoginInfo(UserBean bean) {
        this.userBean = bean;
        if (usernameTv != null) {
            updateUserInfo(bean);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUserInfo(this.userBean);
    }

    public void updateUserInfo(UserBean bean) {
        String username = bean.getUsername();
        if (username != null && !"".equals(username)) {
            usernameTv.setText(username);
            Picasso.with(getActivity()).load(bean.getUserface()).into(userfaceIv);
        } else {
            usernameTv.setText("请登录");
            userfaceIv.setImageResource(R.drawable.default1);
        }
    }

    public void updateUserInfo(Context context) {
        minePresenter.initLoginInfo(context);
    }
}
