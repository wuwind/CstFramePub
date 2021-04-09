package com.make.httplibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.make.httplibrary.net.request.LockOrderRequest;
import com.make.httplibrary.net.response.LockOrderResponse;
import com.make.httplibrary.net.response.LoginResponse;
import com.make.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        OKHttpClient.httpPost(HttpConfig.baseUrl + HttpConfig.UrlPath.LOCKORDER_QUERY, jsonObject, true, new OK3HttpClient.NetCallBack() {
//            @Override
//            public void onSuccess(String o) {
//                //System.out.println("0str>>:" + o);
//                try {
//                    JSONObject obj = JSONObject.parseObject(o);
//                    //String data = obj.getString("data");
//                    Gson gson = new Gson();
//                    //参数1：满足json对象格式的字符串
//                    //String data = obj.getString("data");
//                    //ResponseData e = gson.fromJson(o, ResponseData.class);
//
//                    Message msg = Message.obtain();
//                    msg.arg1 = obj.getInteger("code");//Message类有属性字段arg1、arg2、what...
//                    if (obj.getInteger("code") == 200) {
//                        msg.obj = obj.getJSONArray("data");
//                    } else {
//                        msg.obj = obj.getString("message");
//                    }
//                    mHandler2.sendMessage(msg);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailed(Exception e) {
//                Message msg = Message.obtain();
//                msg.arg1 = 2;//Message类有属性字段arg1、arg2、what...
//                msg.obj = getActivity().getResources().getString(R.string.unable_lock_data_server_error);
//                mHandler2.sendMessage(msg);
//                dismissDialog();
//            }
//        });

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
//        new LoginRequest(false).requset();
        new LockOrderRequest().requset();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLoginResult(LoginResponse response) {
        ToastUtils.showShort(response.code);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLockOrderResponse(LockOrderResponse response) {
        ToastUtils.showShort(response.code);
    }
}