package com.wuwind.cstframe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.make.register.services.api.bean.UserBean;
import com.make.register.services.api.common.RegisterUser;
import com.wuwind.common.RouterPathConst;
import com.wuwind.cstframe.R;
import com.wuwind.cstframe.ui.bottom.BottomAdapter;
import com.wuwind.cstframe.ui.bottom.TabTitle;
import com.wuwind.cstframe.util.DrawableUtil;
import com.wuwind.zrouter_annotation.Autowired;
import com.wuwind.zrouter_api.ZRouter;

import java.util.ArrayList;
import java.util.List;

//@ZRoute(path = "/ar/pa")
public class MainActivity extends AppCompatActivity {

    //    @BindView(R.id.rv_bottom)
    RecyclerView mRvBottom;
    @Autowired
    public String name;

    //******************************************  生命周期函数   *******************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fragment路由的相关参数

//        ARouter.getInstance().build("").navigation();
//        ZRouter.getInstance().build(RouterPathConst.PATH_ACTIVITY_OTHER).navigation(this, 123);

        ZRouter.getInstance().initFragmentParameters(this, R.id.content_frame, new ZRouter.OnFragmentChangedLis() {
            @Override
            public void setTab(String routePath) {
                mAdapter.setSelection(findPositionByRoutePath(routePath));//找到这个routePath对应的position，然后切换下面的tab
            }
        });

        setContentView(R.layout.activity_main);
        mRvBottom = findViewById(R.id.rv_bottom);
//        ButterKnife.bind(this);
        initBottom();
//        initContentFrame();
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ZRouter.getInstance().build(RouterPathConst.PATH_ACTIVITY_LOGIN).navigation(MainActivity.this, 123);
//                startActivityForResult(new Intent(MainActivity.this, MainActivity2.class), 123);
                UserBean userBean = ZRouter.getInstance().navigation(RegisterUser.class).getUserName();
                Toast.makeText(MainActivity.this, userBean.name, Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            Log.e("tag", "name:" + (null != data ? data.getStringExtra("name") : ""));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ZRouter.getInstance().release();// 很诡异的一个问题。静态变量难道不会随着进程被杀而释放内存么，还要我在onDestroy里面手动释放
    }

    //*************************    中间内容区      ***********************************

    private void initContentFrame() {
        if (titleObjectList != null && titleObjectList.size() > 0) {
//            ZRouter.getInstance().build(titleObjectList.get(0).getRoutePath()).withString("name", "123").navigation();
//            Fragment fragment = (Fragment)ARouter.getInstance().build(titleObjectList.get(0).getRoutePath()).navigation();
//            getSupportFragmentManager().beginTransaction().replace(
//                    R.id.content_frame,fragment).commit();
        }
    }

    //*************************   中间内容区OVER    ***********************************

    //***************************       底部       ***************************************
    private BottomAdapter mAdapter;

    /**
     * 初始化底部
     */
    private void initBottom() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvBottom.setLayoutManager(linearLayoutManager);

        mAdapter = new BottomAdapter(this, getBottomSetting());
        mAdapter.setOnItemClickListener(new BottomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String fragmentRoutePath) {
                mAdapter.setSelection(position);
                if (!TextUtils.isEmpty(fragmentRoutePath)) {
                    ZRouter.getInstance().build(fragmentRoutePath).navigation();
                }
            }
        });
        mRvBottom.setAdapter(mAdapter);
        ZRouter.getInstance().build(RouterPathConst.PATH_FRAGMENT_TAB1).navigation();
    }

    private List<TabTitle> titleObjectList = new ArrayList<>();//底部TAB的数据

    /**
     * 按照routePath，找到routePath所在位置
     *
     * @param routePath
     * @return
     */
    private int findPositionByRoutePath(String routePath) {
        int position = 0;
        for (int i = 0; i < titleObjectList.size(); i++) {
            if (titleObjectList.get(i).getRoutePath().equals(routePath)) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * 导航栏配置
     *
     * @return
     */
    private List<TabTitle> getBottomSetting() {
        //本来这里应该要读取配置文件，然后配置文字和颜色，还有图标,暂时写死
        titleObjectList.add(new TabTitle(
                RouterPathConst.PATH_FRAGMENT_TAB1,
                R.string.tag_name_tab1,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab1, R.mipmap.a_tabbar_home_p)));
        titleObjectList.add(new TabTitle(
                RouterPathConst.PATH_ACTIVITY_REGISTER,
                R.string.tag_name_tab2,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab2, R.mipmap.a_tabbar_trade_p)));
        titleObjectList.add(new TabTitle(
                RouterPathConst.PATH_FRAGMENT_TAB3,
                R.string.tag_name_tab3,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab3, R.mipmap.a_tabbar_market_p)));
        titleObjectList.add(new TabTitle(
                RouterPathConst.PATH_FRAGMENT_TAB4,
                R.string.tag_name_tab4,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab4, R.mipmap.a_tabbar_me_p)));
//        ARouter.getInstance().inject(this);
//        ARouter.getInstance().build(RouterPathConst.PATH_FRAGMENT_TAB4).withString("name","123").navigation();
        return titleObjectList;
    }

    //***************************底部OVER***************************************

}
