package com.wuwind.fragment_home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.make.register.services.api.common.RegisterUser;
import com.wuwind.common.RouterPathConst;
import com.wuwind.zrouter_annotation.Autowired;
import com.wuwind.zrouter_annotation.ZRoute;
import com.wuwind.zrouter_api.ZRouter;


/**
 * 我们的首页Fragment，将会被外壳层app module引用.
 * 并且这个首页的Fragment 业务将会与其他业务完全隔离，这是从代码层面完全隔离，
 * 支持独立调试开发,也支持HomeFragment作为零件，附加在外壳上
 */
@ZRoute(RouterPathConst.PATH_FRAGMENT_TAB1)
public class HomeFragment extends Fragment {

    private Button mBtnToMine;
    private Button mBtnToOtherActivity;
    private Button mBtnCallMine;

    @Autowired
    public String name;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZRouter.getInstance().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, null);
        mBtnToMine = root.findViewById(R.id.btn_to_mine);
        mBtnToMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bd = new Bundle();
                bd.putString("from", "首页");
                ZRouter.getInstance().build(RouterPathConst.PATH_FRAGMENT_TAB4).navigation();
            }
        });

        mBtnToOtherActivity = root.findViewById(R.id.btn_to_other_activity);
        mBtnToOtherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZRouter.getInstance().build(RouterPathConst.PATH_ACTIVITY_LOGIN).navigation(getActivity(), 123);
            }
        });

        TextView tvDes = root.findViewById(R.id.tv_des);
        tvDes.setText(name);
        mBtnCallMine = root.findViewById(R.id.btn_call_mine);
        mBtnCallMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行其他模块的业务逻辑
                //方式1
                String accountNo = ZRouter.getInstance().navigation(RegisterUser.class).getUserName().name;
                Toast.makeText(getActivity(), accountNo, Toast.LENGTH_LONG).show();
                //方式2
//                RegisterUser api = ZRouter.getInstance().navigation("RegisterUser");
//                api.getUserName();
//                方式3
//                RegisterUser mineOpenServiceApi = (RegisterUser) ZRouter.getInstance().build("RegisterUser").navigation();
//                mineOpenServiceApi.getUserName();
            }
        });

        root.findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZRouter.getInstance().build(RouterPathConst.PATH_ACTIVITY_REGISTER).navigation();
            }
        });

        return root;
    }

}
