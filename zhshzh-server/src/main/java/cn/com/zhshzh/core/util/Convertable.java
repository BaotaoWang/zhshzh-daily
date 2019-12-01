package cn.com.zhshzh.core.util;

/**
 * 定义PO对象和DTO对象相互转换的接口
 *
 * @param <A> 转换后的对象
 * @param <B> 转换前的对象
 * @author WBT
 * @since 2019/11/27
 */
public interface Convertable<A, B> {
    /**
     * 将PO对象转换为DTO对象
     *
     * @param a PO对象
     * @return DTO对象
     */
    B convertToDTO(A a);

    /**
     * 将DTO对象转换为PO对象
     *
     * @param b DTO对象
     * @return PO对象
     */
    A convertToPO(B b);
}
