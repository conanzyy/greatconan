package com.greatconan.conan.util;

/**
 * MQ相关常量类
 * 
 * @author zhangjie
 */
public class MQConstant {

    public static final int OPERATION_ADD = 0;

    public static final int OPERATION_MODIFY = 1;

    public static final int OPERATION_DELETE = 2;

    public static final int OPERATION_XML_RESOVLE = 3;

    public static final int OPERATION_BACKUP = 4;

    public static final int ZERO = 0;

    public static final int ONE = 1;

    /**
     * 下传数据删除标识
     */
    public static final String DELETE_FLAG = "X";

    /**
     * 下传数据删除标识D
     */
    public static final String DELETE_FLAG_D = "D";

    /** 鉴定主表信息 */
    public static final String APPR_MAIN_A = "A";

    /** 鉴定附表工厂信息 */
    public static final String APPR_MAIN_B = "B";

    /** 鉴定附表网点信息 */
    public static final String APPR_MAIN_C = "C";

    /** 维修主表信息 */
    public static final String APPR_MAIN_D = "D";

    /** 分批处理条数 */
    public static final int BATCH_COUNT = 50;

    private MQConstant() {

    }

}