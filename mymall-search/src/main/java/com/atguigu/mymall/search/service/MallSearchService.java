package com.atguigu.mymall.search.service;

import com.atguigu.mymall.search.vo.SearchParam;
import com.atguigu.mymall.search.vo.SearchResult;

/**
 * @author 孟享广
 * @date 2021-01-14 10:04 上午
 * @description
 */
public interface MallSearchService {

    SearchResult search(SearchParam param);
}
