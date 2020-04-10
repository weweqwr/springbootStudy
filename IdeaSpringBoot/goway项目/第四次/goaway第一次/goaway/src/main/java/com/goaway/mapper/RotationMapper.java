package com.goaway.mapper;

import com.goaway.entity.Gw_rotation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RotationMapper {
    List<Gw_rotation> queryRontation();

}
