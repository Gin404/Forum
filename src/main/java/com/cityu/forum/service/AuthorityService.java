package com.cityu.forum.service;


import com.cityu.forum.domain.Authority;

/**
 * Authority 服务接口.
 * 
 * @since 1.0.0
 * @author
 */
public interface AuthorityService {
	/**
	 * 根据ID查询 Authority
	 * @param id
	 * @return
	 */
    Authority getAuthorityById(Long id);
}
