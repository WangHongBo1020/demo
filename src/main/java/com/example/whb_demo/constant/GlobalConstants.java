package com.example.whb_demo.constant;

/**
 * @author zpw
 * @Description <p>全局常量类</p>
 * <p>
 * 定义全局的常量、枚举、ZK配置路径及默认值等
 * <p>
 * @date 2021/10/24
 */
public interface GlobalConstants {

    /**
     * <p>主键id长度</p>
     */
    int ID_LENGTH = 19;

    String SUCCEED = "succeed";
    String FAILED = "failed";

    int YES = 1;
    int NO = 0;

    /**
     * 删除
     */
    interface Deleted {
        /**
         * 已删除
         */
        int YES = GlobalConstants.YES;
        /**
         * 未删除
         */
        int NO = GlobalConstants.NO;
    }

    /**
     * 启用
     */
    interface Enabled {
        /**
         * 已启用
         */
        int YES = GlobalConstants.YES;
        /**
         * 未启用
         */
        int NO = GlobalConstants.NO;
    }

    /**
     * 有效
     */
    interface Effective {
        /**
         * 有效
         */
        int YES = GlobalConstants.YES;
        /**
         * 失效
         */
        int NO = GlobalConstants.NO;
    }

    /**
     * 时间常量 单位秒
     */
    interface SecondsTime {
        /**
         * 1分钟
         */
        int ONE_MINUTE = 60;

        /**
         * 5分钟
         */
        int FIVE_MINUTES = 300;

        /**
         * 10分钟
         */
        int TEN_MINUTES = 600;

        /**
         * 1小时
         */
        int ONE_HOUR = 3600;

        /**
         * 1天
         */
        int ONE_DAY = 86400;

        /**
         * 1周:7天
         */
        int ONE_WEEK = 604800;

        /**
         * 1个月:30天
         */
        int ONE_MONTH = 2592000;

        /**
         * 最大时间量:单位秒 约等于 68 年左右
         */
        int MAX_TIME = Integer.MAX_VALUE;
    }

    /**
     * 序列最大值
     */
    interface Sequence_Max {
        int ONE_SEQUENCE = 9;
        int TWO_SEQUENCE = 99;
        int THREE_SEQUENCE = 999;
        int FOUR_SEQUENCE = 9999;
        int FIVE_SEQUENCE = 99999;
        int SIX_SEQUENCE = 999999;
        int SEVEN_SEQUENCE = 9999999;
        int EIGHT_SEQUENCE = 9999999;
        int NINE_SEQUENCE = 99999999;

    }

}
