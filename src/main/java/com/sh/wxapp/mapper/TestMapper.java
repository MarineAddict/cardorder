package com.sh.wxapp.mapper;

import com.sh.wxapp.domain.Test;

public interface TestMapper {
    int insert(Test record);

    int insertSelective(Test record);
}