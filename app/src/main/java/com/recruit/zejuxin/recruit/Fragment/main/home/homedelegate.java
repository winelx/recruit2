package com.recruit.zejuxin.recruit.Fragment.main.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.recruit.zejuxin.recruit.Bean.DataBean;
import com.recruit.zejuxin.recruit.Bean.HomeBean;
import com.recruit.zejuxin.recruit.Bean.ListBean;
import com.recruit.zejuxin.recruit.Bean.homeBanner;
import com.recruit.zejuxin.recruit.Code.app.latte;
import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BottomItemDalegate;
import com.recruit.zejuxin.recruit.Code.net.RestClient;
import com.recruit.zejuxin.recruit.Code.net.callBack.IError;
import com.recruit.zejuxin.recruit.Code.net.callBack.ISuccess;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class homedelegate extends BottomItemDalegate implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.home_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.home_list)
    ListView recycler;
    private ArrayList<HomeBean> mHomeBeen = new ArrayList<>();//最新职位
    private ArrayList<DataBean> listview = new ArrayList<>();
    private String address, date, education, experience, jobid, name, salary;
    private String comnames, ids, industry, size;
    private List<ListBean> listbean = new ArrayList<>();
    private List<String> comp;
    private listAdapter mAdapter;
    private JSONObject jsonObject = null;
    private String id, names, pic, time, state;
    private ArrayList<homeBanner> banner = new ArrayList<>();//轮播图

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initData();
        //  initBanner();
        //  intiet();
        mAdapter = new listAdapter(latte.geteApplication(), mHomeBeen);
        recycler.setAdapter(mAdapter);

    }

    private void initBanner() {
        RestClient.Builder()
                .url("http://192.168.2.146:8989/huajiayi/company/queryHjyLunBo.do")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String msg) {
                        try {
                            jsonObject = new JSONObject(msg);
                            //获取到json数据中里的activity数组内容
                            JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                            final int size = resultJsonArray.length();
                            for (int i = 0; i < size; i++) {
                                id = resultJsonArray.getJSONObject(i).getString("id");
                                names = resultJsonArray.getJSONObject(i).getString("name");
                                pic = resultJsonArray.getJSONObject(i).getString("pic");
                                state = resultJsonArray.getJSONObject(i).getString("state");
                                time = resultJsonArray.getJSONObject(i).getString("time");
                                banner.add(new homeBanner(id, names, pic, state, time));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void OnError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

    private void intiet() {
        Toast.makeText(getActivity(), "访问", Toast.LENGTH_SHORT).show();
        RestClient.Builder()
                .url("http://192.168.2.146:8989/huajiayi/company/selectaddproNewJob/贵阳.do")
//                .params("address", "贵阳")
//                .loaderl(latte.geteApplication()
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String msg) {
                        //将字符串转换成jsonObject对象
                        try {
                            listview = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(msg);
                            //获取到json数据中里的activity数组内容
                            JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                            //遍历
                            for (int i = 0; i < resultJsonArray.length(); i++) {
                                //城市
                                address = resultJsonArray.getJSONObject(i).getString("address");
                                //时间
                                date = resultJsonArray.getJSONObject(i).getString("date");
                                //学历
                                education = resultJsonArray.getJSONObject(i).getString("education");
                                //工作年限
                                experience = resultJsonArray.getJSONObject(i).getString("experience");
                                //职位
                                name = resultJsonArray.getJSONObject(i).getString("name");
                                //工资
                                salary = resultJsonArray.getJSONObject(i).getString("salary");
                                JSONArray list = resultJsonArray.getJSONObject(i).getJSONArray("list");
                                for (int j = 0; j < list.length(); j++) {
                                    //公司名称
                                    comnames = list.getJSONObject(j).getString("name");
                                    //公司唯一ID
                                    ids = list.getJSONObject(j).getString("ids");
                                    //公司性质
                                    industry = list.getJSONObject(j).getString("industry");
                                    //公司规模
                                    size = list.getJSONObject(j).getString("size");
                                    JSONArray companyWelfare = resultJsonArray.getJSONObject(i).getJSONArray("companyWelfare");
                                    final int lenght = companyWelfare.length();
                                    comp = new ArrayList<String>();
                                    for (int k = 0; k < lenght; k++) {
                                        String name1 = companyWelfare.getJSONObject(k).getString("name");
                                        comp.add(name1);
                                    }
                                    listbean.add(new ListBean(ids, industry, name, size, comp));
                                }
                            }
                            listview.add(new DataBean(address, date, education, experience, jobid, name, salary, listbean));
                            mHomeBeen.add(new HomeBean(listview));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void OnError(int code, String msg) {
                        try {
                            JSONObject jsonObject = new JSONObject(msg);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }


    private void initData() {
        //改变加载显示的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.RED);
        //设置初始时的大小
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        //设置监听
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置向下拉多少出现刷新
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置刷新出现的位置
        swipeRefreshLayout.setProgressViewEndTarget(false, 200);
    }

    //刷新
    @Override
    public void onRefresh() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
