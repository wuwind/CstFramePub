package com.make.account.login.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.make.dblibrary.UserDao;


public class UserViewModelFactory implements ViewModelProvider.Factory {

    private UserDao userDao;

    public UserViewModelFactory(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        ViewModel viewModel = null;
        String classname = modelClass.getName();
        try {
            //通过反射来生产不同的ViewModel实例
            viewModel = (ViewModel) Class.forName(classname).getConstructor(UserDao.class).newInstance(userDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) viewModel;
    }
}
