package com.goaway.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogoMapper {
    String getLogoUrl();
}
