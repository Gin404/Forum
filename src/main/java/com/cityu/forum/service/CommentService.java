package com.cityu.forum.service;


import com.cityu.forum.domain.Comment;

/**
 * Comment Service接口.
 * 
 * @since 1.0.0
 * @author Gin
 */
public interface CommentService {

	/**
     * 根据id获取 Comment
     * @param id
     * @return
     */
    Comment getCommentById(Long id);
    /**
     * 删除评论
     * @param id
     * @return
     */
    void removeComment(Long id);
}
