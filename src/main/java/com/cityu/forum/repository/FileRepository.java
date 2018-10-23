package com.cityu.forum.repository;


import com.cityu.forum.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * File 存储库.
 * 
 * @since 1.0.0
 * @author Gin
 */
public interface FileRepository extends MongoRepository<File, String> {
}
