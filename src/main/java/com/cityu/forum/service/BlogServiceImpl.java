package com.cityu.forum.service;

import com.cityu.forum.domain.*;
import com.cityu.forum.domain.es.EsBlog;
import com.cityu.forum.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * Blog 服务.
 * 
 * @since 1.0.0
 * @author Gin
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private EsBlogService esBlogService;

    @Autowired
	private BlogRepository blogRepository;
 
	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
        boolean isNew = (blog.getId() == null);
        EsBlog esBlog = null;

        Blog returnBlog = blogRepository.save(blog);

        if (isNew) {
            esBlog = new EsBlog(returnBlog);
        } else {
            esBlog = esBlogService.getEsBlogByBlogId(blog.getId());
            esBlog.update(returnBlog);
        }

        esBlogService.updateEsBlog(esBlog);
        return returnBlog;

    }

	@Transactional
	@Override
	public void removeBlog(Long id) {
        blogRepository.deleteById(id);
        EsBlog esblog = esBlogService.getEsBlogByBlogId(id);
        esBlogService.removeEsBlog(esblog.getId());

    }

	@Override
	public Blog getBlogById(Long id) {
		return blogRepository.findById(id).get();
	}

	@Override
	public Page<Blog> listBlogsByUserNew(User user, Pageable pageable) {
		Page<Blog> blogs = blogRepository.findByUserOrderByCreateTimeDesc(user, pageable);
		return blogs;
	}

	@Override
	public Page<Blog> listBlogsByUser(User user, Pageable pageable) {
		Page<Blog> blogs = blogRepository.findByUser(user, pageable);
		return blogs;
	}

	@Override
	public Page<Blog> listBlogsByReqTimeAndTags(User user, Timestamp timestamp, String tags, Pageable pageable) {
		Page<Blog> blogs = blogRepository.findByRequireTimeAndTagsAndUserOrderByCreateTimeDesc(timestamp, tags,user, pageable);
		return blogs;
	}

	@Override
	public void readingIncrease(Long id) {
		Blog blog = blogRepository.findById(id).get();
		blog.setReadSize(blog.getReadSize()+1); // 在原有的阅读量基础上递增1
		this.saveBlog(blog);
	}

	@Override
	public Blog createComment(Long blogId, String commentContent) {
		Blog originalBlog = blogRepository.findById(blogId).get();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();//从上下文获取评论用户信息
		Comment comment = new Comment(user, commentContent);
		originalBlog.addComment(comment);
		return this.saveBlog(originalBlog);
	}

	@Override
	public void removeComment(Long blogId, Long commentId) {
		Blog originalBlog = blogRepository.findById(blogId).get();
		originalBlog.removeComment(commentId);
		this.saveBlog(originalBlog);
	}

    @Override
    public Blog createVote(Long blogId) {
        Blog originalBlog = blogRepository.findById(blogId).get();
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vote vote = new Vote(user);
        boolean isExist = originalBlog.addVote(vote);
        if (isExist) {
            throw new IllegalArgumentException("该用户已经点过赞了");
        }
        return this.saveBlog(originalBlog);
    }

    @Override
    public void removeVote(Long blogId, Long voteId) {
        Blog originalBlog = blogRepository.findById(blogId).get();
        originalBlog.removeVote(voteId);
        this.saveBlog(originalBlog);
    }


    @Override
    public Page<Blog> listBlogsByLocation(User user, String location, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByUserAndLocation(user, location, pageable);
        return blogs;
    }

	@Override
	public Page<Blog> listBlogsByLocationNew(User user, String location, Pageable pageable) {
		Page<Blog> blogs = blogRepository.findByUserAndLocationOrderByCreateTimeDesc(user, location, pageable);
		return blogs;
	}

    @Override
	public Page<Blog> listBlogsByTags(User user, String tags, Pageable pageable){
		Page<Blog> blogs = blogRepository.findByUserAndTags(user, tags, pageable);
		return blogs;
	}

    @Override
    public Page<Blog> listBlogsByTagsNew(User user, String tags, Pageable pageable){
        Page<Blog> blogs = blogRepository.findByUserAndTagsOrderByCreateTimeDesc(user, tags, pageable);
        return blogs;
    }

	@Override
    public Page<Blog> listBlogsByTagsAndLocation(User user, String tags, String location, Pageable pageable){
        Page<Blog> blogs = blogRepository.findByUserAndTagsAndLocation(user, tags,location,pageable);
        return blogs;
    }

	@Override
	public Page<Blog> listBlogsByTagsAndLocationNew(User user, String tags, String location, Pageable pageable){
		Page<Blog> blogs = blogRepository.findByUserAndTagsAndLocationOrderByCreateTimeDesc(user, tags,location,pageable);
		return blogs;
	}
}
