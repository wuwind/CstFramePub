package com.make.account.login.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.make.account.login.model.Callback;
import com.make.account.login.model.User;
import com.make.account.login.model.UserRepository;

import java.util.List;


/**
 * UserListViewModel
 * 注意，没有持有View层的任何引用
 * @author hufeiyang
 * @data 2021/1/24
 * @Description:
 */
public class UserListViewModel extends ViewModel {

    /**
     * 用户信息
     */
    private MutableLiveData<List<User>> userListLiveData;

    /**
     * 进条度的显示
     */
    private MutableLiveData<Boolean> loadingLiveData;

    public UserListViewModel() {
        userListLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
    }

    /**
     * 获取用户列表信息
     * 假装网络请求 2s后 返回用户信息
     */
    public void getUserInfo() {

        loadingLiveData.setValue(true);

        UserRepository.getUserRepository().getUsersFromServer(new Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                loadingLiveData.setValue(false);
                userListLiveData.setValue(users);
            }

            @Override
            public void onFailed(String msg) {
                loadingLiveData.setValue(false);
                userListLiveData.setValue(null);
            }
        });
    }

    /**
     * 返回LiveData类型
     */
    public LiveData<List<User>> getUserListLiveData() {
        return userListLiveData;
    }
    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }
}
