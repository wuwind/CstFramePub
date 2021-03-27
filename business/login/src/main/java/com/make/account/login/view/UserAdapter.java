package com.make.account.login.view;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leelen.community.accout.login.R;
import com.make.account.login.model.User;

class UserAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public UserAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, User user) {
        viewHolder.setText(R.id.tv_user_name, user.getName());
        viewHolder.setText(R.id.tv_user_age, String.valueOf(user.getAge()));
    }

}
