package com.cityu.forum.repository;

import com.cityu.forum.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Comment Repository 接口. 
 * 
 * @since 1.0.0
 * @author Gin
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
