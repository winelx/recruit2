package com.recruit.zejuxin.recruit.Code.delegate.Bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 10942 on 2017/9/25 0025.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {
    /**
     * 用于存储子fagment
     */
    private final ArrayList<BottomItemDalegate> ITEM_DELEGATES = new ArrayList<>();
    // private final ArrayList<BottomItemDalegate> ITEM_DELEGATES = new ArrayList<>();

    /**
     * 用于存储tab
     */
    private final ArrayList<BottomTabBean> TAB_REANS = new ArrayList<>();
    // private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    /**
     * 存储映射
     */
    public final LinkedHashMap<BottomTabBean, BottomItemDalegate> ITEMS = new LinkedHashMap<>();
    //private final LinkedHashMap<BottomTabBean,BottomItemDalegate> ITEMS = new LinkedHashMap<>();

    //用来判断fragment
    private int mCurrentDelegate = 0;
    //用来判断哪个是第一个展示的fangment
    private int mIndxDalegate = 0;
    //点击后的颜色
    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDalegate> setItem(ItemBuilder builder);
    // public abstract LinkedHashMap<BottomTabBean, BottomItemDalegate> setItem(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.detegale_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndxDalegate = setIndexDelegate();
        if (mClickedColor != 0) {
            mClickedColor = setClickedColor();
        }
        final ItemBuilder builder = ItemBuilder.Builder();
        final LinkedHashMap<BottomTabBean, BottomItemDalegate> items = setItem(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDalegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDalegate value = item.getValue();
            TAB_REANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            //绑定布局
            LayoutInflater.from(getContext()).inflate(R.layout.delegate_item_icon, mBottomBar);
            //获取布局对象
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            //设置item的点击事件
            item.setOnClickListener(this);
            final IconTextView itemicon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_REANS.get(i);
            //初始化数据
            itemicon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndxDalegate) {
                //设置点击颜色
                itemicon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }

        }
        //这个是装子fragment集合
        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndxDalegate, delegateArray);
    }


    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();//拿到tag的坐标
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itenicon = (IconTextView) item.getChildAt(0);
        itenicon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitlr = (AppCompatTextView) item.getChildAt(1);
        itemTitlr.setTextColor(mClickedColor);
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //注意顺序
        mCurrentDelegate = tag;
    }

    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itenicon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitlr = (AppCompatTextView) item.getChildAt(1);
            itenicon.setTextColor(Color.GRAY);
            itemTitlr.setTextColor(Color.GRAY);

        }
    }


}
