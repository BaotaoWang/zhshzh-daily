package cn.com.zhshzh.core.util;

import cn.com.zhshzh.core.model.PageResult;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * pojo对象转换工具类
 *
 * @author WBT
 * @since 2020/03/20
 */
public class ConvertBeanUtil {
    private static final Logger logger = LogManager.getLogger(ConvertBeanUtil.class);

    /**
     * 分页查询时，将poList转为带分页数据的dtoList
     *
     * @param poList
     * @param clazz
     * @param <P>    PO对象泛型
     * @param <D>    DTO对象泛型
     * @return
     */
    public static <P, D> PageResult<D> getPageResult(List<P> poList, Class<D> clazz) {
        // 将数据库查出的poList转为pagehelper插件中的PageInfo对象
        PageInfo<P> pageInfo = new PageInfo<>(poList);
        // 将po对象转为dto对象
        List<D> dtoList = poList.stream().map(po -> {
            D dto = null;
            try {
                // 实例化dto对象
                dto = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error(e.getMessage(), e);
            }
            assert dto != null;
            BeanUtils.copyProperties(po, dto);
            return dto;
        }).collect(Collectors.toList());
        PageResult<D> pageResult = new PageResult<>(dtoList);
        // 将分页数据复制给pageResult对象
        BeanUtils.copyProperties(pageInfo, pageResult);
        return pageResult;
    }
}
