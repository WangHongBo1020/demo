package com.example.whb_demo.enumo;


import com.example.whb_demo.constant.ClientProfileConstant;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

public interface WmsstockEnum {

    enum Enum {

        KN(0, "库内验车"),
        QK(1, "入库验车"),
        CK(2, "出库验车");

        private int code;

        private String name;

        Enum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getTaskTypeEnumMsg(int code) {
            if (code >= 0) {
                for (Enum e : Enum.values()) {
                    if (e.getCode() == code) {
                        return e.name;
                    }
                }
            }
            return null;
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
    }

    enum ClientProfile {
        ADVOCATE(1, "主机厂客户"),
        BCLIENT(2, "小b端客户"),
        PARTS(3, "零部件客户"),
        REST(4, "其他客户"),
        ;

        private int code;

        private String name;

        ClientProfile(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getTaskTypeEnumMsg(int code) {
            if (code >= 0) {
                for (Enum e : Enum.values()) {
                    if (e.getCode() == code) {
                        return e.name;
                    }
                }
            }
            return null;
        }

        public static int getCode(String name) {
            if (name != null && !"".equals(name)) {
                for (Enum e : Enum.values()) {
                    if (e.getName().equals(name)) {
                        return e.code;
                    }
                }
            }
            return 0;
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
    }


    enum QualityStatus {

        YX(0, "无质损"),
        YW(1, "有质损"),

        ;

        private int code;

        private String name;

        QualityStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getName(int code) {
            if (code >= 0) {
                for (Enum e : Enum.values()) {
                    if (e.getCode() == code) {
                        return e.name;
                    }
                }
            }
            return null;
        }

        public static int getCode(String name) {
            if (name != null && !"".equals(name)) {
                for (Enum e : Enum.values()) {
                    if (e.getName().equals(name)) {
                        return e.code;
                    }
                }
            }
            return 0;
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

    }

    enum KeyFollow {

        BSC(0, "不随车"),
        SC(1, "随车"),

        ;

        private int code;

        private String name;

        KeyFollow(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getName(int code) {
            if (code >= 0) {
                for (Enum e : Enum.values()) {
                    if (e.getCode() == code) {
                        return e.name;
                    }
                }
            }
            return null;
        }

        public static int getCode(String name) {
            if (name != null && !"".equals(name)) {
                for (Enum e : Enum.values()) {
                    if (e.getName().equals(name)) {
                        return e.code;
                    }
                }
            }
            return 0;
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

    }
}
