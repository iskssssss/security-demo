package cn.lz.demo.filter.config.security.filter;

import cn.lz.security.exception.base.SecurityException;
import cn.lz.security.filter.context.SpringBootContextTheadLocal;
import cn.lz.security.filter.filters.LzInterfacesFilter;
import cn.lz.security.filter.mode.LzRequest;
import cn.lz.security.filter.mode.LzResponse;
import cn.lz.security.log.LzLoggerUtil;
import org.springframework.stereotype.Component;

/**
 * 测试执行链
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/10/22 17:33
 */
@Component
public class TestInterfacesFilter extends LzInterfacesFilter {

    @Override
    public boolean doFilter(
            LzRequest lzRequest,
            LzResponse lzResponse,
            SpringBootContextTheadLocal context,
            Object... params
    ) throws SecurityException {
        LzLoggerUtil.info(this.getClass(), "自定义测试过滤器-过滤");
        return yes();
    }

    @Override
    public void init() {
        LzLoggerUtil.info(this.getClass(), "自定义测试过滤器-初始化");
    }

    @Override
    public void destroy() {
        LzLoggerUtil.info(this.getClass(), "自定义测试过滤器-销毁");
    }
}
