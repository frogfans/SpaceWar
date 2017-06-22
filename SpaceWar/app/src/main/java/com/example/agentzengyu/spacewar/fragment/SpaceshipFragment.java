package com.example.agentzengyu.spacewar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.adapter.ShopAdapter;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.ShopItem;

import java.util.ArrayList;

/**
 * 商店战舰界面
 */
public class SpaceshipFragment extends Fragment implements View.OnClickListener {
    private SpaceWarApp app = null;
    private LinearLayoutManager manager;
    private ShopAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView mIvLife, mIvAgility, mIvDefense, mIvShield;
    private ArrayList<ShopItem> userItem = new ArrayList<>();
    private ArrayList<ShopItem> shopItems = new ArrayList<>();
    private int currentPosition = 1;

    public SpaceshipFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spaceship, null);
        initVariable();
        initView(view);
        shift(0);
        return view;
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getActivity().getApplication();
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ShopAdapter(getActivity(), userItem, shopItems);
    }

    /**
     * 初始化布局
     *
     * @param view
     */
    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rvSpaceship);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);

        mIvLife = (ImageView) view.findViewById(R.id.ivLife);
        mIvLife.setOnClickListener(this);
        mIvAgility = (ImageView) view.findViewById(R.id.ivAgility);
        mIvAgility.setOnClickListener(this);
        mIvDefense = (ImageView) view.findViewById(R.id.ivDefense);
        mIvDefense.setOnClickListener(this);
        mIvShield = (ImageView) view.findViewById(R.id.ivShield);
        mIvShield.setOnClickListener(this);
    }

    private void shift(int position) {
        if (position == currentPosition) return;
        shopItems.clear();
        switch (position) {
            case 0:
                userItem.add(app.getUser().getLife());
                shopItems.addAll(app.getService().getData().getLifes());
                break;
            case 1:
                userItem.add(app.getUser().getDefense());
                shopItems.addAll(app.getService().getData().getDefenses());
                break;
            case 2:
                userItem.add(app.getUser().getAgility());
                shopItems.addAll(app.getService().getData().getAgilities());
                break;
            case 3:
                userItem.add(app.getUser().getShield());
                shopItems.addAll(app.getService().getData().getShields());
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
        currentPosition = position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLife:
                shift(0);
                break;
            case R.id.ivDefense:
                shift(1);
                break;
            case R.id.ivAgility:
                shift(2);
                break;
            case R.id.ivShield:
                shift(3);
                break;
            default:
                break;
        }
    }
}