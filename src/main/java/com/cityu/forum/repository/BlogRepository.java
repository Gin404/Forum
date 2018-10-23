package com.cityu.forum.repository;


import com.cityu.forum.domain.Blog;
import com.cityu.forum.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;


/**
 * Blog 仓库.
 *
 * @since 1.0.0
 * @author Gin
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {

	/**
	 * 根据用户名分页查询博客列表
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUser(User user, Pageable pageable);
	
	/**
	 * 根据用户名查询博客列表（时间逆序）
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUserOrderByCreateTimeDesc(User user, Pageable pageable);

	/**根据需求时间和需求查询列表（时间逆序）
	 * @param timestamp
	 * @param tags
	 */
	Page<Blog> findByRequireTimeAndTagsAndUserOrderByCreateTimeDesc(Timestamp timestamp, String tags, User user, Pageable pageable);

	/**
	 * 根据取件地点查询博客列表
	 * @param location
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUserAndLocation(User user, String location, Pageable pageable);

    /**
     * 根据取件地点查询博客列表(时间逆序)
     * @param location
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndLocationOrderByCreateTimeDesc(User user, String location, Pageable pageable);

	/**
	 * 根据请求方式查询博客列表
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUserAndTags(User user, String tags, Pageable pageable);

    /**
     * 根据请求方式查询博客列表(时间逆序)
     * @param tags
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTagsOrderByCreateTimeDesc(User user, String tags, Pageable pageable);

	/**
	 * 根据请求方式和取件地点查询博客列表
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUserAndTagsAndLocation(User user, String tags, String location,Pageable pageable);

    /**
     * 根据请求方式和取件地点查询博客列表(时间逆序)
     * @param tags
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTagsAndLocationOrderByCreateTimeDesc(User user, String tags, String location,Pageable pageable);

}
