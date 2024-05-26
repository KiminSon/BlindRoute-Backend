package com.lake.blindroute.domain.tmap;

import lombok.Data;

@Data
public class BusRoute {
    private String busRouteId;     // 버스 노선 ID

    private String busRouteNm;     // 버스 노선 이름

    private String busRouteAbrv;   // 버스 노선 약어

    private String length;         // 노선 길이

    private String busRouteType;   // 버스 노선 유형

    private String stBegin;        // 출발 정류장

    private String stEnd;          // 종료 정류장

    private String term;           // 운행 간격

    private String nextBus;        // 다음 버스 도착 정보

    private String firstBusTm;     // 첫차 시간

    private String lastBusTm;      // 막차 시간

    private String firstBusTmLow;  // 첫차 시간 (저상 버스)

    private String lastBusTmLow;   // 막차 시간 (저상 버스)

    private String adirection;     // 주행 방향
}
