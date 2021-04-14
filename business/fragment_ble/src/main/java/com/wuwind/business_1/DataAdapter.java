package com.wuwind.business_1;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuwind.business_1.bean.DeviceBean;

/**
 * Created by wuhongfeng
 * data: 2021/4/8
 * desc:
 */
public class DataAdapter extends BaseQuickAdapter<DeviceBean, BaseViewHolder> {

    public DataAdapter() {
        super(R.layout.ble_item_device);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_address, item.getAddress());
        helper.setText(R.id.tv_desc, item.getDesc());
    }
}
