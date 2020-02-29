package com.project.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.exam.model.SysUser;

public interface SysUserMapper extends PaginationBaseMapper<SysUser, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    /**
     * 
     * <p>Title: findByUserName</p>
     * <p>Description: 排除教练</p>
     * @param username
     * @return SysUser
     */
	SysUser findByUserName(@Param("username")String username);

	/**
	 * 
	 * <p>Title: selectAll</p>
	 * <p>Description: 获取所有学员</p>
	 * @return List<SysUser>
	 */
	List<SysUser> selectAll();
}