package com.cityu.forum.service;
 


import com.cityu.forum.domain.User;
import com.cityu.forum.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;


/**
 * EsBlog 服务接口.
 * 
 * @since 1.0.0
 * @author Gin
 */
public interface EsBlogService {
 	
	/**
	 * 删除EsBlog
	 * @param id
	 * @return
	 */
	void removeEsBlog(String id);
	
	/**
	 * 更新 EsBlog
	 * @param esBlog
	 * @return
	 */
	EsBlog updateEsBlog(EsBlog esBlog);
	
	/**
	 * 根据Blog的Id获取EsBlog
	 * @param blogId
	 * @return
	 */
	EsBlog getEsBlogByBlogId(Long blogId);

	/**
	 * 最新博客列表，分页(keyword和username)
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listNewestEsBlogsByKeywordAndUsername(String keyword, String username, Pageable pageable);

	/**
	 * 最热博客列表，分页(keyword和username)
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listHotestEsBlogsByKeywordAndUsername(String keyword, String username, Pageable pageable);

	/**
	 * 最新博客列表，分页(keyword)
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listNewestEsBlogsByKeyword(String keyword, Pageable pageable);
 
	/**
	 * 最热博客列表，分页(keyword)
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listHotestEsBlogsByKeyword(String keyword, Pageable pageable);

	/**
	 * 最热博客列表，分页(取件地点)
	 * @param location
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listHotestEsBlogsByLocation(String location, Pageable pageable);

	/**
	 * 最新博客列表，分页(取件地点)
	 * @param location
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listNewestEsBlogsByLocation(String location, Pageable pageable);

	/**
	 * 最热博客列表，分页(请求类型)
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listHotestEsBlogsByTags(String tags, Pageable pageable);

	/**
	 * 最新博客列表，分页(请求类型)
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listNewestEsBlogsByTags(String tags, Pageable pageable);

	/**
	 * 最热博客列表，分页(取件日期)
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listHotestEsBlogsByRequireTime(String deliverDate, Pageable pageable);

	/**
	 * 最新博客列表，分页(取件日期)
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listNewestEsBlogsByRequireTime(String deliverDate, Pageable pageable);

	/**
	 * 最热博客列表，分页(取件地点和请求类型)
	 * @param location
	 * @param tags
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByLocationAndTags(String location, String tags, Pageable pageable);

	/**
	 * 最新博客列表，分页(取件地点和请求类型)
	 * @param location
	 * @param tags
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByLocationAndTags(String location, String tags, Pageable pageable);

	/**
	 * 最热博客列表，分页(取件地点和取件日期)
	 * @param location
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByLocationAndRequiretime(String location, String deliverDate, Pageable pageable);

	/**
	 * 最新博客列表，分页(取件地点和取件日期)
	 * @param location
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByLocationAndRequiretime(String location, String deliverDate, Pageable pageable);

	/**
	 * 最热博客列表，分页(请求类型和取件日期)
	 * @param tags
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByTagsAndRequiretime(String tags, String deliverDate, Pageable pageable);

	/**
	 * 最新博客列表，分页(请求类型和取件日期)
	 * @param tags
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByTagsAndRequiretime(String tags, String deliverDate, Pageable pageable);

	/**
	 * 最热博客列表，分页(关键字和取件地点)
	 * @param keyword
	 * @param location
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByKeywordAndLocation(String keyword, String location, Pageable pageable);

	/**
	 * 最新博客列表，分页(关键字和取件地点)
	 * @param keyword
	 * @param location
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByKeywordAndLocation(String keyword, String location, Pageable pageable);

	/**
	 * 最热博客列表，分页(关键字和请求类型)
	 * @param keyword
	 * @param tags
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByKeywordAndTags(String keyword, String tags, Pageable pageable);

	/**
	 * 最新博客列表，分页(关键字和请求类型)
	 * @param keyword
	 * @param tags
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByKeywordAndTags(String keyword, String tags, Pageable pageable);

	/**
	 * 最热博客列表，分页(关键字和取件日期)
	 * @param keyword
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByKeywordAndRequireTime(String keyword, String deliverDate, Pageable pageable);

	/**
	 * 最新博客列表，分页(关键字和取件日期)
	 * @param keyword
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByKeywordAndRequireTime(String keyword, String deliverDate, Pageable pageable);

	/**
	 * 取件地点， 请求类型， 取件时间/hotest
	 * @param location
	 * @param tags
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByLocationAndTagsAndRequireTime(String location, String tags, String deliverDate, Pageable pageable);

	/**
	 * 取件地点， 请求类型， 取件时间/newest
	 * @param location
	 * @param tags
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByLocationAndTagsAndRequireTime(String location, String tags, String deliverDate, Pageable pageable);

	/**
	 * 关键字，取件时间，请求类型/hostest
	 * @param keyword
	 * @param deliverDate
	 * @param tags
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByKeywordAndRequireTimeAndTags(String keyword, String deliverDate, String tags, Pageable pageable);

	/**
	 * 关键字，取件时间，请求类型/newest
	 * @param keyword
	 * @param deliverDate
	 * @param tags
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByKeywordAndRequireTimeAndTags(String keyword, String deliverDate, String tags, Pageable pageable);

	/**
	 * 关键字，取件时间，请求类型/hotest
	 * @param keyword
	 * @param location
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByKeywordAndLocationAndRequireTime(String keyword, String location, String deliverDate, Pageable pageable);

	/**
	 * 关键字，取件地点，取件时间/newest
	 * @param keyword
	 * @param location
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByKeywordAndLocationAndRequireTime(String keyword, String location, String deliverDate, Pageable pageable);

	/**
	 * 取件地点，请求类型，关键字/hotest
	 * @param location
	 * @param tags
	 * @param keyword
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByLocationAndTagsAndKeyword(String location, String tags, String keyword, Pageable pageable);

	/**
	 * 取件地点，请求类型，关键字/newest
	 * @param location
	 * @param tags
	 * @param keyword
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByLocationAndTagsAndKeyword(String location, String tags, String keyword, Pageable pageable);

	/**
	 * 取件地点，请求类型，关键字，取件日期/hotest
	 * @param location
	 * @param tags
	 * @param keyword
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listHotestEsBlogsByLocationAndTagsAndKeywordAndRequireTime(String location, String tags, String keyword, String deliverDate, Pageable pageable);

	/**
	 * 取件地点，请求类型，关键字，取件日期/newest
	 * @param location
	 * @param tags
	 * @param keyword
	 * @param deliverDate
	 * @param pageable
	 * @return
	 */
    Page<EsBlog> listNewestEsBlogsByLocationAndTagsAndKeywordAndRequireTime(String location, String tags, String keyword, String deliverDate, Pageable pageable);

    /**
	 * 博客列表，分页
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listEsBlogs(Pageable pageable);
	/**
	 * 最新前5
	 * @return
	 */
	List<EsBlog> listTop5NewestEsBlogs();
	
	/**
	 * 最热前5
	 * @return
	 */
	List<EsBlog> listTop5HotestEsBlogs();
	
	/**
	 * 最热前 30 标签
	 * @return
	 */
	/*List<TagVO> listTop30Tags();*/

	/**
	 * 最热前12用户
	 * @return
	 */
	List<User> listTop12Users();
}
