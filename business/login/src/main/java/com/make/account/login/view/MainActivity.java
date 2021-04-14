package com.make.account.login.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.make.account.login.R;
import com.make.account.login.databinding.ActivityLoginBinding;
import com.make.account.login.viewmodel.UserListViewModel;
import com.make.account.login.viewmodel.UserViewModelFactory;
import com.make.dblibrary.AppDatabase;
import com.make.dblibrary.UserDao;
import com.make.dblibrary.model.User;
import com.make.uilibrary.base.BaseActivity;
import com.make.uilibrary.click.AClickStr;
import com.make.utilcode.util.LogUtils;
import com.wuwind.common.RouterPathConst;
import com.wuwind.zrouter_annotation.ZRoute;

import java.util.List;

@ZRoute(RouterPathConst.PATH_ACTIVITY_LOGIN)
public class MainActivity extends BaseActivity<ActivityLoginBinding> {

    private UserListViewModel mUserListViewModel;
    private UserAdapter mUserAdapter;

    public void init() {
        mUserAdapter = new UserAdapter(R.layout.item_user_mvvm);
        binding.rvUserList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvUserList.setAdapter(mUserAdapter);
        initViewModel();
        getData();
        observeLiveData();
    }

    @AClickStr({"btn_login", "btn_delete", "btn_get"})
    public void click(View view, String viewId) {
        switch (viewId) {
            case "btn_login":
                User user = new User("useradd", 23);
                mUserListViewModel.addUser(user);
                break;
            case "btn_delete":
                mUserListViewModel.deleteUser(mUserAdapter.getItem(0));
                break;
            case "btn_get":
                mUserListViewModel.getUserInfo();
                break;
        }
    }


    private void initViewModel() {
//        mUserListViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserListViewModel.class);
        UserDao userDao = AppDatabase.getDatabase().userDao();
        mUserListViewModel = new ViewModelProvider(this, new UserViewModelFactory(userDao)).get(UserListViewModel.class);
    }

    /**
     * 获取数据，调用ViewModel的方法获取
     */
    private void getData() {
//        mUserListViewModel.getUserInfo();
    }

    private void observeLiveData() {
        mUserListViewModel.getUserListLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users == null) {
                    Toast.makeText(MainActivity.this, "获取user失败！", Toast.LENGTH_SHORT).show();
                    return;
                }
                LogUtils.e("observeLiveData user");
                mUserAdapter.setNewData(users);
            }
        });

        mUserListViewModel.getLoadingLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.pbLoadingUsers.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            }
        });
    }


}
