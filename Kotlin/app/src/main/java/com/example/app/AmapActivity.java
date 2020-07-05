package com.example.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.YuvImage;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.app.view.AnimView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class AmapActivity extends AppCompatActivity implements AMap.OnMyLocationChangeListener, AMap.OnMapClickListener, LocationSource, GeocodeSearch.OnGeocodeSearchListener {

    private MapView mMapView = null;
    private AMap aMap;
    private GroundOverlay groundOverlay = null;
    private Marker marker;
    private FrameLayout root;
    private boolean isPlayRefresh;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amp);
        root = findViewById(R.id.root);
        ProgressView proview = findViewById(R.id.proview);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isPlayRefresh = true;
//                startRotateAnim(v);
                CityDialogFragment cityDialogFragment = CityDialogFragment.newInstance();
                cityDialogFragment.show(getSupportFragmentManager(),"");
            }
        });
        proview.setButtonOnclick(new ProgressView.OnclickListener() {
            @Override
            public void onClick() {
                playOrStop();
                proview.playOrStop();
            }
        });



        initMapView(savedInstanceState);
        initLocation();
    }
    private void startRotateAnim(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"rotation",0,360);
        if (isPlayRefresh){
            objectAnimator.setDuration(1500);
            objectAnimator.start();
        }else{
            objectAnimator.cancel();
        }
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startRotateAnim(view);
            }
        });
    }

    private void initMapView(Bundle savedInstanceState) {
        //定义了一个地图view
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
    }

    private void initLocation() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。

//        myLocationStyle.myLocationIcon(new BitmapDescriptor()); //todo 设置自定义定位图标
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.getUiSettings().setScaleControlsEnabled(false);// 设置默认定位按钮是否显示
        // 自定义系统定位蓝点
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(
                BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);

        aMap.setOnMapClickListener(this);


        addOverLayToMap();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMyLocationChange(Location location) {
        //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取（获取地址描述数据章节有介绍）
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.i("Don", "onMapClick: " + latLng.latitude + "====" + latLng.longitude);
        if (marker != null) {
            marker.remove();
        }
        marker = aMap.addMarker(new MarkerOptions().position(latLng));
        getGeocodeSearch(latLng);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    private GeocodeSearch geocoderSearch;

    //逆地理编码获取当前位置信息
    private void getGeocodeSearch(LatLng targe) {
        if (geocoderSearch == null) geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        //逆地理编码查询条件：逆地理编码查询的地理坐标点、查询范围、坐标类型。
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(targe.latitude, targe.longitude), 1000, GeocodeSearch.AMAP);
        //异步查询
        geocoderSearch.getFromLocationAsyn(query);
    }


    /**
     * 得到逆地理编码异步查询结果
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        String simpleAddress = null;
        RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
        String province = regeocodeAddress.getProvince();
        String city = regeocodeAddress.getCity();
        String district = regeocodeAddress.getDistrict();
        if (district != null && !TextUtils.isEmpty(district)) {
            simpleAddress = district;
        }
        if (simpleAddress != null) {
        }

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    private int index = 0;
    int count = 0 ;

    private void addOverLayToMap() {


        new Thread(new Runnable() {
            @Override
            public void run() {


//                List<Bitmap> bitmapList = getBitmapList();
                List<Drawable> drawableList = getDrawableList();

                View view = LayoutInflater.from(AmapActivity.this).inflate(R.layout.layout_image, null, false);
                view.setBackground(drawableList.get(index));

                LatLng southwest = new LatLng(3.9079, 71.9282);
                LatLng northeast = new LatLng(57.9079, 150.6026);

                LatLngBounds bounds = new LatLngBounds.Builder()//设置显示在屏幕中的地图地理范围,地图中点显示为两个点的中点
                        .include(southwest)
                        .include(northeast).build();

                addGroudOverlay(view,bounds);

                //开启动画
                startAnim(view, drawableList,bounds);

            }
        }).start();

    }

    private void addGroudOverlay(View view, LatLngBounds bounds) {


        if (groundOverlay != null) {
            groundOverlay.remove();
        }

        groundOverlay = aMap.addGroundOverlay(new GroundOverlayOptions()
                .anchor(0.5f, 0.5f)//设置ground覆盖物的锚点比例，默认为0.5f，水平和垂直方向都居中对齐
                .transparency(0.1f)//设置覆盖物的透明度，范围：0.0~1.0
                .image(BitmapDescriptorFactory
                        .fromView(view)
                )
                .positionFromBounds(bounds));
    }

    /**
     * 开始动画
     */
    private ValueAnimator valueAnim;

    private void startAnim(View view, List<Drawable> drawableList,LatLngBounds bounds) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                valueAnim = ValueAnimator.ofFloat(0, 1);
                valueAnim.setInterpolator(new LinearInterpolator());
                valueAnim.setDuration(500);
                valueAnim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        index++;
                        index = index % drawableList.size();

                        View view = LayoutInflater.from(AmapActivity.this).inflate(R.layout.layout_image, null, false);
                        view.setBackground(drawableList.get(index));

                        addGroudOverlay(view,bounds);

                        startAnim(view, drawableList,bounds);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                valueAnim.start();
            }
        });

    }
    private boolean isPlay = true;
    public void playOrStop() {
        if (valueAnim == null) return;
        isPlay = !isPlay;
        if (isPlay) {
            valueAnim.resume();
        } else {
            valueAnim.pause();
        }
    }

    private List<Bitmap> getBitmapList() {
        List<String> strings = new ArrayList<>();
        List<Bitmap> bitmapList = new ArrayList<>();

        strings.add("https://cdn.caiyunapp.com//weather//radar//o//cnmap_merca_202007031555_202007031555_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031600_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031605_4e6bdfda63.png ");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031615_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031625_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031630_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031635_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031720_4e6bdfda63.png");
        Bitmap myBitmap = null;

        for (int i = 0; i < strings.size(); i++) {
            try {
                myBitmap = Glide.with(getApplicationContext())
                        .asBitmap()
                        .skipMemoryCache(false)                      //禁止Glide内存缓存
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)  //不缓存资源
                        .load(strings.get(i))
                        .centerCrop()
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
                bitmapList.add(myBitmap);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       /* Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tianqi1);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.tianqi2);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.tianqi3);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.tianqi4);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.tianqi5);
        bitmapList.add(bitmap);
        bitmapList.add(bitmap1);
        bitmapList.add(bitmap2);
        bitmapList.add(bitmap3);
        bitmapList.add(bitmap4);*/

        return bitmapList;
    }
    private List<Drawable> getDrawableList() {
        List<Drawable> drawableList = new ArrayList<>();

        List<String> strings = new ArrayList<>();

        strings.add("https://cdn.caiyunapp.com//weather//radar//o//cnmap_merca_202007031555_202007031555_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031600_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031605_4e6bdfda63.png ");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031615_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031625_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031630_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031635_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031720_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com//weather//radar//o//cnmap_merca_202007031555_202007031555_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031600_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031605_4e6bdfda63.png ");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031615_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031625_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031630_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031635_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031720_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com//weather//radar//o//cnmap_merca_202007031555_202007031555_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031600_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031605_4e6bdfda63.png ");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031615_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031625_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031630_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031635_4e6bdfda63.png");
        strings.add("https://cdn.caiyunapp.com/weather/radar/o/cnmap_merca_202007031555_202007031720_4e6bdfda63.png");


        for (int i = 0; i < strings.size(); i++) {
            FutureTarget<Drawable> drawable = Glide.with(this)
                    .asDrawable()
                    .load(strings.get(i))
                    .thumbnail(0.25f)
                    .submit();
            try {
                drawableList.add(drawable.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return drawableList;
    }

}
