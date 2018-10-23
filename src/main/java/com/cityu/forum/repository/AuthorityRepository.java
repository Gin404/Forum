package com.cityu.forum.repository;

import com.cityu.forum.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority 仓库.
 * 
 * @since 1.0.0 2018年10月3日
 * @author Gin
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
