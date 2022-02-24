package com.example.whb_demo.constant;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

public interface ClientProfileConstant extends GlobalConstants {

    enum ClientProfile {
        ADVOCATE(1, "主机厂客户"),
        BCLIENT(2, "小b端客户"),
        PARTS(3, "零部件客户"),
        REST(4, "其他客户"),
        ;

        private int source;
        private String desc;

        private static Map<Integer, ClientProfile> innerCacheMap = Maps.newHashMapWithExpectedSize(values().length);

        ClientProfile(int source, String desc) {
            this.source = source;
            this.desc = desc;
        }

        public int getSource() {
            return source;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDesc(int source) {
            if (MapUtils.isEmpty(innerCacheMap)) {
                for (ClientProfile clientProfile : values()) {
                    innerCacheMap.put(clientProfile.getSource(), clientProfile);
                }
            }
            return innerCacheMap.get(source) == null ? "无" : innerCacheMap.get(source).getDesc();
        }



        public static Map<Integer, ClientProfile> getInnerCacheMap() {
            if (MapUtils.isEmpty(innerCacheMap)) {
                for (ClientProfile clientProfile  : values()) {
                    innerCacheMap.put(clientProfile.getSource(), clientProfile);
                }
            }
            return innerCacheMap;
        }
    }

    enum QualityStatus {

        YX(0, "无质损"),
        YW(1, "有质损"),

        ;

        private int code;
        private String name;

        private static Map<Integer, QualityStatus> innerCacheMap = Maps.newHashMapWithExpectedSize(values().length);

        QualityStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getName(int code) {
            if (MapUtils.isEmpty(innerCacheMap)) {
                for (QualityStatus qualityStatus : values()) {
                    innerCacheMap.put(qualityStatus.getCode(), qualityStatus);
                }
            }
            return innerCacheMap.get(code) == null ? "无" : innerCacheMap.get(code).getName();
        }

    }
}
