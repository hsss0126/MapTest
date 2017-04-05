package com.example.user_16.maptest;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

public class MainActivity extends NMapActivity implements NMapView.OnMapStateChangeListener {

    //클라이언트 아이디
    public static final String Client_ID = "Uh0Rg6lwLbguQhv_8zr4";
    //네이버 맵 객체
    NMapView nMapView = null;
    //맵 컨트롤러
    NMapController nMapController = null;
    //맵을 추가할 레이아웃
    LinearLayout MapContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //지도를 넣기위한 LinearLayout 컴포넌트
        MapContainer = (LinearLayout) findViewById(R.id.MapContainer);

        //지도 객체 생성
        nMapView = new NMapView(this);

        //지도 객체로부터 컨트롤러 추출
        nMapController = nMapView.getMapController();

        //지도 객체에 Client_ID 지정
        nMapView.setApiKey(Client_ID);

        //생성된 지도 객체를 LinearLayout에 추가
        MapContainer.addView(nMapView);

        //지도를 터치할 수 있도록 옵션 활성화
        nMapView.setClickable(true);

        //확대 축소를 위한 줌 컨트롤러 표시 옵션 활성화
        nMapView.setBuiltInZoomControls(true, null);

        //지도에 대한 상태 변경 이벤트 연결
        nMapView.setOnMapStateChangeListener(this);
    }

    /**
     * 지도가 초기화된 후 호출된다.
     * 정상적으로 초기화되면 error 객체는 null이 전달되며,
     * 초기화 실패 시 error객체에 에러 원인이 전달된다
     */
    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {

        if(nMapError == null) { //성공
            nMapController.setMapCenter(new NGeoPoint(126.82575, 37.487444), 11);
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
}
