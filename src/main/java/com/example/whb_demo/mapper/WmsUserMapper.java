package com.example.whb_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.whb_demo.entity.WmsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表 Mapper 接口
 *
 * @author zpw
 * @date 2021/10/24
 */
@Mapper
public interface WmsUserMapper extends BaseMapper<WmsUser> {

}
