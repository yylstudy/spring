package com.yyl.service;

import com.yyl.page.PageDto;
import com.yyl.page.PageHelper;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/4 15:00
 * @Version: 1.0
 */
@Component("pageObjectFacory")
public class PageObjectFacory extends DefaultObjectFactory {
    @Override
    public <T> T create(Class<T> type) {
        if(PageDto.class.isAssignableFrom(type)){
            PageDto pageDto = new PageDto();
            pageDto.setTotal(PageHelper.getTotal());
            return (T)pageDto;
        }
        return super.create(type);
    }
}
