package com.cityu.forum.service;

import com.cityu.forum.domain.Blog;
import com.cityu.forum.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;

/**
 * Blog 服务接口.
 * 
 * @since 1.0.0
 * @author Gin
 */
public interface BlogService {
	/**
	 * 保存Blog
	 * @param blog
	 * @return
	 */
	Blog saveBlog(Blog blog);
	
	/**
	 * 删除Blog
	 * @param id
	 * @return
	 */
	void removeBlog(Long id);
	
	/**
	 * 根据id获取Blog
	 * @param id
	 * @return
	 */
	Blog getBlogById(Long id);
 
	/**
	 * 根据用户进行博客分页查询
	 * @param user
	 * @return
	 */
	Page<Blog> listBlogsByUser(User user, Pageable pageable);
 
	/**
	 * 根据用户进行博客分页查询（最新）
	 * @param user
	 * @return
	 */
	Page<Blog> listBlogsByUserNew(User user, Pageable pageable);

	/**根据需求时间和种类进行查询
     * */
	Page<Blog> listBlogsByReqTimeAndTags(User user, Timestamp timestamp, String tags, Pageable pageable);

	/**
	 * 阅读量递增
	 * @param id
	 */
	void readingIncrease(Long id);

	/**
	 * 发表评论
	 * @param blogId
	 * @param commentContent
	 * @return
	 */
	Blog createComment(Long blogId, String commentContent);

	/**
	 * 删除评论
	 * @param blogId
	 * @param commentId
	 * @return
	 */
	void removeComment(Long blogId, Long commentId);

    /**
     * 点赞
     * @param blogId
     * @return
     */
    Blog createVote(Long blogId);

    /**
     * 取消点赞
     * @param blogId
     * @param voteId
     * @return
     */
    void removeVote(Long blogId, Long voteId);

    /**
     * 根据取件地点进行查询
     * @param location
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByLocation(User user, String location, Pageable pageable);

	/**
	 * 根据取件地点进行查询(最新)
	 * @param location
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlogsByLocationNew(User user, String location, Pageable pageable);

	/**
	 * 根据请求类型进行查询
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlogsByTags(User user, String tags, Pageable pageable);
    /**
     * 根据请求类型进行查询(最新)
     * @param tags
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTagsNew(User user, String tags, Pageable pageable);


	/**
	 * 根据请求类型进行查询
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlogsByTagsAndLocation(User user, String tags, String location,Pageable pageable);

	/**
	 * 根据请求类型进行查询(最新)
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlogsByTagsAndLocationNew(User user, String tags, String location,Pageable pageable);
}
