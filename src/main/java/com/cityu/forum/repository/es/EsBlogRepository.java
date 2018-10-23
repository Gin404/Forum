package com.cityu.forum.repository.es;


import com.cityu.forum.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;


/**
 * EsBlog Repository接口.
 * 
 * @since 1.0.0
 * @author Gin
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
    /**
     * 根据用户名和关键字(用于userspace)
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param location
     * @param username
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContainingAndUsername(String title, String Summary, String content, String tags, String location, String username, Pageable pageable);


    /**
     * 只根据keyword模糊查询(去重)
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContaining(String title, String Summary, String content, String tags, String location, Pageable pageable);

    /**
     * 只根据取件地点查询
     * @param location
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByLocation(String location, Pageable pageable);

    /**
     * 只根据请求类型查询
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTags(String tags, Pageable pageable);

    /**
     * 只根据取件时间查询
     * @param requireTime
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByRequireTime(Date requireTime, Pageable pageable);

    /**
     * 根据取件地点和请求类型查询
     * @param location
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByLocationAndTags(String location, String tags, Pageable pageable);

    /**
     * 根据区间地点和取件时间查询
     * @param location
     * @param requireTime
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByLocationAndRequireTime(String location, Date requireTime, Pageable pageable);

    /**
     * 根据请求类型和取件时间查询
     * @param tags
     * @param requireTime
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTagsAndRequireTime(String tags, Date requireTime, Pageable pageable);

    /**
     * 根据取件地点和关键字查询
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param location
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingAndLocation(String title, String Summary, String content, String tags, String location, Pageable pageable);


    /**
     * 根据请求类型和关键字查询
     * @param title
     * @param Summary
     * @param content
     * @param location
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrLocationContainingAndTags(String title, String Summary, String content, String location, String tags, Pageable pageable);

    /**
     * 根据取件时间和关键字查询
     * @param title
     * @param Summary
     * @param content
     * @param location
     * @param tags
     * @param requireTime
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContainingAndRequireTime(String title, String Summary, String content, String location, String tags, Date requireTime, Pageable pageable);

    /**
     * 根据取件地点和请求类型和取件时间查询
     * @param tags
     * @param requireTime
     * @param location
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTagsAndRequireTimeAndLocation(String tags, Date requireTime, String location, Pageable pageable);

    /**
     * 根据取件时间和请求类型和keyword模糊查询
     * @param title
     * @param summary
     * @param content
     * @param location
     * @param tags
     * @param requireTime
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrLocationContainingAndTagsAndRequireTime(String title, String summary, String content, String location, String tags, Date requireTime, Pageable pageable);

    /**
     * 根据取件时间和取件地点和keyword模糊查询
     * @param title
     * @param summary
     * @param content
     * @param tags
     * @param location
     * @param requireTime
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingAndRequireTimeAndLocation(String title, String summary, String content, String tags, String location, Date requireTime, Pageable pageable);

    /**
     * 根据取件地点和请求类型和keyword模糊查询
     * @param title
     * @param summary
     * @param content
     * @param tags
     * @param location
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingAndTagsAndLocation(String title, String summary, String content, String tags, String location, Pageable pageable);

    /**
     * 根据取件地点和请求类型和取件时间和keyword模糊查询
     * @param title
     * @param summary
     * @param content
     * @param tags
     * @param location
     * @param requireTime
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingAndTagsAndLocationAndRequireTime(String title, String summary, String content, String tags, String location, Date requireTime, Pageable pageable);

    /**
     * 根据 Blog 的id 查询 EsBlog
     * @param blogId
     * @return
     */
    EsBlog findByBlogId(Long blogId);
}
