package com.cityu.forum.service;

import com.cityu.forum.domain.Vote;
import com.cityu.forum.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Vote 服务实现.
 * @since 1.0.0
 * @author Gin
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;
    
	@Override
	public Vote getVoteById(Long id) {
		return voteRepository.findById(id).get();
	}
	
	@Override
	public void removeVote(Long id) {
		voteRepository.deleteById(id);
	}

}
