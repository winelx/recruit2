package com.recruit.zejuxin.recruit.Fragment.main.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.recruit.zejuxin.recruit.Adapter.DropMenuAdapter;
import com.recruit.zejuxin.recruit.Code.app.latte;
import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BottomItemDalegate;
import com.recruit.zejuxin.recruit.Code.util.drop.entity.FilterUrl;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import butterknife.BindView;

/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class videoDelegate extends BottomItemDalegate implements OnFilterDoneListener {
    @BindView(R2.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @BindView(R2.id.mFilterContentView)
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Object setLayout() {
        return R.layout.delagate_video;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        String[] titleList = new String[]{"第一个", "第二个"};
        dropDownMenu.setMenuAdapter(new DropMenuAdapter(latte.geteApplication(), titleList, this));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        FilterUrl.instance().clear();
    }


    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        if (position != 1) {
            dropDownMenu.setPositionIndicatorText(FilterUrl.instance()
                    .position, FilterUrl.instance().positionTitle);
        }
        dropDownMenu.close();
        mTextView.setText(FilterUrl.instance().toString());
    }
}
