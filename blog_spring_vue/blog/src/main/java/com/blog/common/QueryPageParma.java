package com.blog.common;

import lombok.Data;

@Data
public class QueryPageParma {

    private static int PAGE_SIZE = 10;
    private static int PAGE_NUM = 1;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;
    private Parma parma = new Parma();
}
