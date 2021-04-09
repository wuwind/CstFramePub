package com.wuwind.business_1;

import android.bluetooth.BluetoothDevice;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by wuhongfeng
 * data: 2021/4/8
 * desc:
 */
public class DataAdapter extends BaseQuickAdapter<BluetoothDevice, BaseViewHolder> {

    public DataAdapter() {
        super(R.layout.ble_item_device);
    }

    @Override
    protected void convert(BaseViewHolder helper, BluetoothDevice item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_address, item.getAddress());
    }
}
