package com.itany.p2p.constants;

/**
 * 常量接口
 * <一句话功能简述>
 *  
 * @author  崔译
 * @version  [V1.00, 2016-1-28]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface Constants
{
    /**
     * 数据库常量接口
     * <一句话功能简述>
     *  
     * @author  崔译
     * @version  [V1.00, 2016-1-28]
     * @see  [相关类/方法]
     * @since V1.00
     */
    interface SQLConstants{
        /**
         * 产品类型状态-可用
         */
        public static final int PRODUCT_TYPE_STATUS_OPEN = 1001;
        /**
         * 产品类型状态 -禁用
         */
        public static final int PRODUCT_TYPE_STATUS_CLOSE = 1002;
    }
    
    
}
