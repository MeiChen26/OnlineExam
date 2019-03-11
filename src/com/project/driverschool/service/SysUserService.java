package com.project.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.dao.SysUserMapper;
import com.project.exam.model.SysUser;

/**
 * 
 * <p>ClassName: SysUserService</p>
 * <p>Description: 用户service</p>
 * @Author  
 * @Date: 
 */
@Service("userService")
public class SysUserService extends PaginationBaseService<SysUserMapper, SysUser, Integer>{

	@Transactional
	public void saveUser(SysUser user) {
		mapper.insert(user);
	}

	@Transactional
	public int updateUser(SysUser user) {
		return mapper.updateByPrimaryKeySelective(user);
	}

	public List<SysUser> selectAll() {
		return this.mapper.selectAll();
	}
}
