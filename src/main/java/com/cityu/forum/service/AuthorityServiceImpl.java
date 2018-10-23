/**
 * 
 */
package com.cityu.forum.service;

import com.cityu.forum.domain.Authority;
import com.cityu.forum.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Authority 服务接口的实现.
 * 
 * @since 1.0.0
 * @author Gin
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findById(id).get();
	}

}
