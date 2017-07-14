package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;
import com.example.agentzengyu.spacewar.entity.single.UserInfo;

/**
 * 初始化服务
 */
public class LoaderService extends Service {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate.");
        super.onCreate();
        app = (SpaceWarApp) getApplication();
        app.setLoaderService(this);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart.");
        super.onStart(intent, startId);
        loadData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy.");
        super.onDestroy();
    }

    /**
     * 加载数据
     */
    private void loadData() {
        Log.e(TAG, "loadData");
        loadShopData();
        loadPlayerData();
//        loadEnemyData();
//        loadMapData();
    }

    /**
     * 加载商店数据
     */
    private void loadShopData() {
        Log.e(TAG, "loadShopData");
        ShopLibrary library = app.getShopDao().findAll();
        if (library == null) {
            library = initShopData();
        }
        app.setShopLibrary(library);
        Intent intent = new Intent(Constant.BroadCast.LOADING);
        sendBroadcast(intent);
    }

    /**
     * 加载玩家数据
     */
    private void loadPlayerData() {
        Log.e(TAG, "loadPlayerData");
        PlayerData data = app.getPlayerDao().findAll();
        if (data == null) {
            Log.e("data", "null");
            data = initPlayerData();
        }
        app.setPlayerData(data);
    }

    /**
     * 加载敌人数据
     */
    private void loadEnemyData() {
        Log.e(TAG, "loadEnemyData");
        EnemyLibrary library = app.getEnemyDao().findAll();
        if (library == null) {
            library = initEnemyData();
        }
        app.setEnemyLibrary(library);
    }

    /**
     * 加载地图数据
     */
    private void loadMapData() {
        Log.e(TAG, "loadMapData");
        MapLibrary library = app.getMapDao().findAll();
        if (library == null) {
            library = initMapData();
        }
        app.setMapLibrary(library);
    }

    /**
     * 初始化商店数据
     *
     * @return
     */
    private ShopLibrary initShopData() {
        Log.e(TAG, "initShopData");
        ShopLibrary library = new ShopLibrary(true);

        ShopItem life1 = new ShopItem("life1", 100, 100, 100, R.mipmap.life1);
        library.getLives().add(life1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.LIFE, life1);

        ShopItem defense1 = new ShopItem("defense1", 100, 100, 100, R.mipmap.life1);
        library.getDefenses().add(defense1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.DEFENSE, defense1);

        ShopItem agility1 = new ShopItem("agility1", 100, 100, 100, R.mipmap.life1);
        library.getAgilities().add(agility1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.AGILITY, agility1);

        ShopItem shield1 = new ShopItem("shield1", 100, 100, 100, R.mipmap.ic_launcher_round);
        library.getShields().add(shield1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.SHIELD, shield1);

        ShopItem power1 = new ShopItem("power1", 100, 100, 100, R.mipmap.life1);
        library.getPowers().add(power1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.POWER, power1);

        ShopItem speed1 = new ShopItem("speed1", 100, 100, 100, R.mipmap.life1);
        library.getSpeeds().add(speed1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.SPEED, speed1);

        ShopItem range1 = new ShopItem("range1", 100, 100, 100, R.mipmap.life1);
        library.getRanges().add(range1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.RANGE, range1);

        ShopItem laser1 = new ShopItem("laser1", 100, 100, 100, R.mipmap.life1);
        library.getLasers().add(laser1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.LASER, laser1);

        return library;
    }

    /**
     * 初始化玩家数据
     *
     * @return
     */
    private PlayerData initPlayerData() {
        Log.e(TAG, "initPlayerData");
        PlayerData data = new PlayerData();
        data.setLife(app.getShopLibrary().getLives().get(0));
        data.setDefense(app.getShopLibrary().getDefenses().get(0));
        data.setAgility(app.getShopLibrary().getAgilities().get(0));
        data.setShield(app.getShopLibrary().getShields().get(0));
        data.setPower(app.getShopLibrary().getPowers().get(0));
        data.setSpeed(app.getShopLibrary().getSpeeds().get(0));
        data.setRange(app.getShopLibrary().getRanges().get(0));
        data.setLaser(app.getShopLibrary().getLasers().get(0));
        data.setInfo(new UserInfo("New User", 1000));
        app.getPlayerDao().update(data);
        return data;
    }

    /**
     * 初始化敌人数据
     *
     * @return
     */
    private EnemyLibrary initEnemyData() {
        Log.e(TAG, "initEnemyData");
        EnemyLibrary library = new EnemyLibrary();

        // TODO enemyDao.insert()
        return library;
    }

    /**
     * 初始化地图数据
     *
     * @return
     */
    private MapLibrary initMapData() {
        Log.e(TAG, "initMapData");
        MapLibrary library = new MapLibrary(true);

        // TODO mapDao.insert()
        return library;
    }
}
