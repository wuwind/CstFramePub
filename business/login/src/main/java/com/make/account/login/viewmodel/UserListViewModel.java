package com.make.account.login.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.make.account.login.db.AppDatabase;
import com.make.account.login.db.UserDao;
import com.make.account.login.model.User;

import java.util.List;


/**
 * UserListViewModel
 * 注意，没有持有View层的任何引用
 *
 * @Description:
 */
public class UserListViewModel extends ViewModel {

    /**
     * 用户信息
     */
    private LiveData<List<User>> userListLiveData;

    /**
     * 进条度的显示
     */
    private MutableLiveData<Boolean> loadingLiveData;

    public UserListViewModel() {
        userListLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
    }

    public UserListViewModel(UserDao userDao) {
        userListLiveData = userDao.getAll();
        loadingLiveData = new MutableLiveData<>();
    }

    /**
     * 获取用户列表信息
     * 假装网络请求 2s后 返回用户信息
     */
    public void getUserInfo() {

        loadingLiveData.setValue(true);

        new AsyncTask<Void, Void, User>() {

            @Override
            protected void onPostExecute(User user) {
                addUser(user);
                loadingLiveData.setValue(false);
            }

            @Override
            protected User doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //假装从服务端获取的
                User user = new User("user new", 22);
                return user;
            }
        }.execute();

    }

    public void addUser(User user) {
        AppDatabase.getDatabase().userDao().insertAll(user);
    }

    public void deleteUser(User user) {
        AppDatabase.getDatabase().userDao().delete(user);
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
