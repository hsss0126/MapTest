package com.example.user_16.maptest;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapProjection;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

public class MainActivity extends NMapActivity {

    //클라이언트 아이디
    public static final String Client_ID = "Uh0Rg6lwLbguQhv_8zr4";
    NMapView mMapView = null;                   //네이버 맵 객체
    NMapController mMapController = null;       //맵 컨트롤러
    LinearLayout MapContainer;                  //맵을 추가할 레이아웃
    NMapViewerResourceProvider mMapViewerResourceProvider = null;
    NMapOverlayManager mMapOverlayManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //지도를 넣기위한 LinearLayout 컴포넌트
        MapContainer = (LinearLayout) findViewById(R.id.MapContainer);

        //지도 객체 생성
        mMapView = new NMapView(this);

        //지도 객체로부터 컨트롤러 추출
        mMapController = mMapView.getMapController();

        //지도 객체에 Client_ID 지정
        mMapView.setApiKey(Client_ID);

        //생성된 지도 객체를 LinearLayout에 추가
        MapContainer.addView(mMapView);

        //지도를 터치할 수 있도록 옵션 활성화
        mMapView.setClickable(true);

        //확대 축소를 위한 줌 컨트롤러 표시 옵션 활성화
        mMapView.setBuiltInZoomControls(true, null);

        //지도에 대한 상태 변경 이벤트 연결
        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
        mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);

        //-----------------------------------------------------------------------------------//
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        mMapOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);

//        int markerID = NMapPOIflagType.PIN;
//        NMapPOIdata poiData = new NMapPOIdata(2,mMapViewerResourceProvider);
//        poiData.beginPOIdata(2);
//        poiData.addPOIitem(126.82575, 37.487444, "성공회대학교", markerID,0);
//        poiData.addPOIitem(126.82304, 37.49203, "온수역", markerID,0);
//        poiData.endPOIdata();
//        NMapPOIdataOverlay poiDataOverlay = mMapOverlayManager.createPOIdataOverlay(poiData,null);
//
//        poiDataOverlay.showAllPOIdata(0);
//        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);

    }
    private final NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {
        public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item){
            Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
        }
        public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {

            if (item != null) {
                Log.i("NMAP", "onFocusChanged: " + item.toString() + item.getTitle());
            } else {
                Log.i("NMAP", "onFocusChanged: ");
            }

        }
    };


    private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {
        /**
         * 지도가 초기화된 후 호출된다.
         * 정상적으로 초기화되면 error 객체는 null이 전달되며,
         * 초기화 실패 시 error객체에 에러 원인이 전달된다
         */
        @Override
        public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {

            if(nMapError == null) { //성공
                mMapController.setMapCenter(new NGeoPoint(126.82575, 37.487444), 11);
            }
            else { //실패
                Log.e("NMAP", "onMapInitHandler: 에러=" + nMapError.toString());
            }
        }

        /**
         * 지도 중심 변경 시 호출되며 변경된 중심 좌표가 파라미터로 전달된다.
         */
        @Override
        public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

        }

        /**
         * 지도 레벨 변경 시 호출되며 변경된 지도 레벨이 파라미터로 전달된다.
         */
        @Override
        public void onZoomLevelChange(NMapView nMapView, int i) {

        }

        /**
         * 지도 애니메이션 상태 변경 시 호출된다.
         * animType : ANIMATION_TYPE_PAN or ANIMATION_TYPE_ZOOM
         * animState : ANIMATION_STATE_STARTED or ANIMATION_STATE_FINISHED
         */
        @Override
        public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

        }

        @Override
        public void onMapCenterChangeFine(NMapView nMapView) {

        }
    };

    private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {

        @Override
        public void onLongPress(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub
            int touch_x = (int) ev.getX();
            Log.d("터치X좌표",touch_x+"");
            int touch_y = (int) ev.getY();
            Log.d("터치Y좌표",touch_y+"");
            NMapProjection mMapProjection = mapView.getMapProjection();
            NGeoPoint selectPoint = mMapProjection.fromPixels(touch_x,touch_y);
            Log.d("지오포인트",selectPoint.getLatitude()+":"+selectPoint.getLongitude());
            int markerID = NMapPOIflagType.PIN;
            NMapPOIdata poiData = new NMapPOIdata(0,mMapViewerResourceProvider);
            poiData.beginPOIdata(1);
            poiData.addPOIitem(selectPoint, "내가 선택한거", markerID,0);
            poiData.endPOIdata();
            NMapPOIdataOverlay poiDataOverlay = mMapOverlayManager.createPOIdataOverlay(poiData,null);

            poiDataOverlay.showAllPOIdata(0);
            poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);
        }

        @Override
        public void onLongPressCanceled(NMapView mapView) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSingleTapUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTouchDown(NMapView mapView, MotionEvent ev) {

        }

        @Override
        public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {
        }

        @Override
        public void onTouchUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

    };
}
