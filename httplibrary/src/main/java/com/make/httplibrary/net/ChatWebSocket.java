package com.make.httplibrary.net;

import android.util.Log;

import com.google.gson.Gson;
import com.make.utilcode.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by hongfengwu on 2017/10/5.
 */

public final class ChatWebSocket extends WebSocketListener {
    private WebSocket _WebSocket = null;
    private JSONObject _JsonObject = null;

    private String url = UrlConst.SOCKET_URL + "/websocket/business/message.do";
    private static ChatWebSocket mChatWebSocket = null;

    Gson gson;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        _WebSocket = webSocket;
        LogUtils.e("ChatWebSocket onOpen");
        if (null == gson) {
            gson = new Gson();
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        LogUtils.e("ChatWebSocket MESSAGE:" + text);
        ChatWebResponse chatWebResponse = gson.fromJson(text, ChatWebResponse.class);
        EventBus.getDefault().post(chatWebResponse);
//        try {
//            _JsonObject = new JSONObject(text);
//        }catch (JSONException e){
//            e.printStackTrace();
//        }

    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        LogUtils.e("ChatWebSocket MESSAGE: " + bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        mChatWebSocket = null;
        LogUtils.e("ChatWebSocket onClosing:" + code + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
        mChatWebSocket = null;
        LogUtils.e("ChatWebSocket onFailure");
    }

    /**
     * ?????????WebSocket?????????
     */
    private void run() {
        OkHttpClient client;// = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
        client = OkHttpClientManager.getInstance().getmOkHttpClient();
//        client = new OkHttpClient.Builder().cookieJar(client.cookieJar()).readTimeout(0, TimeUnit.MILLISECONDS).build();


        client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//????????????(??????:???)
                .writeTimeout(20, TimeUnit.SECONDS)//????????????(??????:???)
                .readTimeout(20, TimeUnit.SECONDS)//????????????(??????:???)
                .pingInterval(20, TimeUnit.SECONDS) //websocket????????????(??????:???)
                .cookieJar(client.cookieJar())//Cookies?????????
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();

        Request request = new Request.Builder().url(url).build();
        client.newWebSocket(request, this);
        client.dispatcher().executorService().shutdown();
    }

    /**
     * @param s
     * @return
     */
    public boolean sendMessage(String s) {
        return _WebSocket.send(s);
    }

    public void closeWebSocket() {
        mChatWebSocket = null;
        _WebSocket.close(1000, "????????????");
        Log.e("ChatWebSocket close", "????????????");
    }

    /**
     * ???????????????ChatWebSocket???
     *
     * @return ChatWebSocket
     */
    public static ChatWebSocket getChartWebSocket() {
        if (mChatWebSocket == null) {
            mChatWebSocket = new ChatWebSocket();
            mChatWebSocket.run();
        }
        return mChatWebSocket;
    }

}