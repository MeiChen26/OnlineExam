package com.project.utils.shiro;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.dao.SysUserMapper;
import com.project.exam.model.SysUser;

@Transactional
public class MyShiroRealm extends AuthorizingRealm {
	public static final Logger logger = Logger.getLogger(MyShiroRealm.class);
	
	@Inject
	private SysUserMapper sysUserMapper;

	/**
	 * 权限认证,授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录时输入的用户名
		SysUser loginUser = (SysUser) principalCollection.fromRealm(getName()).iterator().next();
		// 到数据库查是否有此对象
		// SysUser user= (SysUser)loginUser;
		if (loginUser != null) {
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			return info;
		}
		return null;
	}

	/**
	 * 登录认证，身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		try {
			// UsernamePasswordToken对象用来存放提交的登录信息
			UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
			// 查出是否有此用户
			SysUser localUser = sysUserMapper.findByUserName(token.getUsername());
			if (localUser != null) {
				// 若存在，将此用户存放到登录认证info中
				byte[] salt = null;
				try {
					salt = Hex.decode(localUser.getPassword().substring(0, 16).toCharArray());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new SimpleAuthenticationInfo(localUser, localUser.getPassword().substring(16),
						ByteSource.Util.bytes(salt), getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSysUserMapper(SysUserMapper sysUserMapper) {
		this.sysUserMapper = sysUserMapper;
	}
	
}