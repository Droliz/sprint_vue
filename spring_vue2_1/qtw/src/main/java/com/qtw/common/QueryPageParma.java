package com.qtw.common;

import com.qtw.entity.User;
import lombok.Data;

/**
 * <p>
 *  分页参数封装
 * </p>
 *
 * @author qtw
 * @since 2022-12-22
 */

@Data
public class QueryPageParma {
    // 默认的分页
    private static int PAGE_SIZE = 20;
    private static int PAGE_NUM = 1;

    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;
    private User parma=new User();
}
