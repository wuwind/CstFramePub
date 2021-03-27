package com.make.account.login.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.leelen.community.accout.login.R;
import com.make.account.login.model.User;
import com.make.account.login.viewmodel.UserListViewModel;
import com.wuwind.common.RouterPathConst;
import com.wuwind.zrouter_annotation.ZRoute;

import java.util.List;


@ZRoute(RouterPathConst.PATH_ACTIVITY_LOGIN)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    private UserListViewModel mUserListViewModel;
    private ProgressBar mProgressBar;
    private RecyclerView mRvUserList;
    private UserAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initViewModel();

        getData();

        observeLiveData();
    }

    private void initViewModel() {
        mUserListViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserListViewModel.class);
    }

    /**
     * 获取数据，调用ViewModel的方法获取
     */
    private void getData() {
        mUserListViewModel.getUserInfo();
    }

    private void observeLiveData() {
        mUserListViewModel.getUserListLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users == null) {
                    Toast.makeText(MainActivity.this, "获取user失败！", Toast.LENGTH_SHORT).show();
                    return;
                }
                mUserAdapter.setNewData(users);
            }
        });

        mUserListViewModel.getLoadingLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mProgressBar.setVisibility(aBoolean? View.VISIBLE:View.GONE);
            }
        });
    }

    public void onClick(View view) {
        getData();
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        mProgressBar = findViewById(R.id.pb_loading_users);
        mRvUserList = findViewById(R.id.rv_user_list);

        mRvUserList.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter = new UserAdapter(R.layout.item_user_mvvm);
        mRvUserList.setAdapter(mUserAdapter);
    }

}
