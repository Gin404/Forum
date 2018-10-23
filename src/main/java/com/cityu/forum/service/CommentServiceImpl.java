package com.cityu.forum.service;

import com.cityu.forum.domain.Comment;
import com.cityu.forum.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Comment Service接口实现.
 * 
 * @since 1.0.0
 * @author Gin
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findById(id).get();
	}

	@Override
	public void removeComment(Long id) {
		commentRepository.deleteById(id);
	}

}
