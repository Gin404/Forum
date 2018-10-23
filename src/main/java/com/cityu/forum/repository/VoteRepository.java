package com.cityu.forum.repository;

import com.cityu.forum.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Vote Repository接口.
 * @since 1.0.0
 * @author Gin
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
