package com.cityu.forum.service;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cityu.forum.domain.User;
import com.cityu.forum.domain.es.EsBlog;
import com.cityu.forum.repository.es.EsBlogRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.SearchParseException;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;


/**
 * EsBlog 服务.
 * 
 * @since 1.0.0
 * @author Gin
 */
@Service
public class EsBlogServiceImpl implements EsBlogService {
	@Autowired
	private EsBlogRepository esBlogRepository;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	private UserService userService;
	
	private static final Pageable TOP_5_PAGEABLE = PageRequest.of(0, 5);
	private static final String EMPTY_KEYWORD = "";
	
	@Override
	public void removeEsBlog(String id) {
		esBlogRepository.deleteById(id);
	}

	@Override
	public EsBlog updateEsBlog(EsBlog esBlog) {
		return esBlogRepository.save(esBlog);
	}
	
	@Override
	public EsBlog getEsBlogByBlogId(Long blogId) {
		return esBlogRepository.findByBlogId(blogId);
	}

    @Override
    public Page<EsBlog> listNewestEsBlogsByKeywordAndUsername(String keyword, String username, Pageable pageable) throws SearchParseException {//new
        Page<EsBlog> pages = null;
        Sort sort = new Sort(Direction.DESC,"createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        pages = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContainingAndUsername(keyword,keyword,keyword,keyword, keyword, username,pageable);

        return pages;
    }

    @Override
    public Page<EsBlog> listHotestEsBlogsByKeywordAndUsername(String keyword, String username, Pageable pageable) throws SearchParseException{//hot

        Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContainingAndUsername(keyword, keyword, keyword, keyword, keyword,username, pageable);
    }

	@Override
	public Page<EsBlog> listNewestEsBlogsByKeyword(String keyword, Pageable pageable) throws SearchParseException {//new
		Page<EsBlog> pages = null;
		Sort sort = new Sort(Direction.DESC,"createTime");
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
 
		pages = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContaining(keyword,keyword,keyword,keyword, keyword,pageable);
 
		return pages;
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByKeyword(String keyword, Pageable pageable) throws SearchParseException{//hot
 
		Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

		return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContaining(keyword, keyword, keyword, keyword, keyword,pageable);
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByLocation(String location, Pageable pageable) throws SearchParseException{
		Page<EsBlog> pages = null;

        Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

		pages = esBlogRepository.findDistinctEsBlogByLocation(location, pageable);

		return pages;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByLocation(String location, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

		return esBlogRepository.findDistinctEsBlogByLocation(location, pageable);
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByTags(String tags, Pageable pageable) throws SearchParseException{
        Page<EsBlog> pages = null;
        Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        pages = esBlogRepository.findDistinctEsBlogByTags(tags, pageable);

        return pages;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByTags(String tags, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return esBlogRepository.findDistinctEsBlogByTags(tags, pageable);
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByRequireTime(String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByRequireTime(date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }

        return null;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByRequireTime(String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByRequireTime(date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByLocationAndTags(String location, String tags, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		return esBlogRepository.findDistinctEsBlogByLocationAndTags(location, tags, pageable);
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByLocationAndTags(String location, String tags, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return esBlogRepository.findDistinctEsBlogByLocationAndTags(location, tags, pageable);
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByLocationAndRequiretime(String location, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByLocationAndRequireTime(location, date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByLocationAndRequiretime(String location, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
            return esBlogRepository.findDistinctEsBlogByLocationAndRequireTime(location, date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByTagsAndRequiretime(String tags, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
            return esBlogRepository.findDistinctEsBlogByTagsAndRequireTime(tags, date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByTagsAndRequiretime(String tags, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTagsAndRequireTime(tags, date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByKeywordAndLocation(String keyword, String location, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingAndTagsAndLocation(keyword, keyword, keyword, keyword, location, pageable);
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByKeywordAndLocation(String keyword, String location, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingAndTagsAndLocation(keyword, keyword, keyword, keyword, location, pageable);
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByKeywordAndTags(String keyword, String tags, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

	    return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrLocationContainingAndTags(keyword, keyword, keyword, keyword, tags, pageable);
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByKeywordAndTags(String keyword, String tags, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrLocationContainingAndTags(keyword, keyword, keyword, keyword, tags, pageable);
    }

	@Override
	public Page<EsBlog> listHotestEsBlogsByKeywordAndRequireTime(String keyword, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
            return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContainingAndRequireTime(keyword, keyword, keyword, keyword, keyword, date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByKeywordAndRequireTime(String keyword, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingOrLocationContainingAndRequireTime(keyword, keyword, keyword, keyword, keyword, date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByLocationAndTagsAndRequireTime(String location, String tags, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTagsAndRequireTimeAndLocation(tags, date, location, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByLocationAndTagsAndRequireTime(String location, String tags, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTagsAndRequireTimeAndLocation(tags, date, location, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByKeywordAndRequireTimeAndTags(String keyword, String deliverDate, String tags, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrLocationContainingAndTagsAndRequireTime(keyword, keyword,keyword,keyword,tags,date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByKeywordAndRequireTimeAndTags(String keyword, String deliverDate, String tags, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
            return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrLocationContainingAndTagsAndRequireTime(keyword, keyword,keyword,keyword,tags,date, pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByKeywordAndLocationAndRequireTime(String keyword, String location, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingAndRequireTimeAndLocation(keyword,keyword,keyword,keyword,location,date,pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByKeywordAndLocationAndRequireTime(String keyword, String location, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContainingAndRequireTimeAndLocation(keyword,keyword,keyword,keyword,location,date,pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listHotestEsBlogsByLocationAndTagsAndKeyword(String location, String tags, String keyword, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingAndTagsAndLocation(keyword,keyword,keyword,tags,location,pageable);
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByLocationAndTagsAndKeyword(String location, String tags, String keyword, Pageable pageable) throws SearchParseException{
        Sort sort = new Sort(Direction.DESC,"createTime");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingAndTagsAndLocation(keyword,keyword,keyword,tags,location,pageable);
    }

	@Override
	public Page<EsBlog> listHotestEsBlogsByLocationAndTagsAndKeywordAndRequireTime(String location, String tags, String keyword, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingAndTagsAndLocationAndRequireTime(keyword,keyword,keyword,tags,location,date,pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }
	    return null;
	}

	@Override
	public Page<EsBlog> listNewestEsBlogsByLocationAndTagsAndKeywordAndRequireTime(String location, String tags, String keyword, String deliverDate, Pageable pageable) throws SearchParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(deliverDate);
            Sort sort = new Sort(Direction.DESC,"createTime");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

            return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingAndTagsAndLocationAndRequireTime(keyword,keyword,keyword,tags,location,date,pageable);
        } catch (ParseException e){
            e.printStackTrace();
        }

        return null;
	}


	@Override
	public Page<EsBlog> listEsBlogs(Pageable pageable) {
		return esBlogRepository.findAll(pageable);
	}
 
 
	/**
	 * 最新前5
	 * @param
	 * @return
	 */
	@Override
	public List<EsBlog> listTop5NewestEsBlogs() {
		Page<EsBlog> page = this.listNewestEsBlogsByKeyword(EMPTY_KEYWORD, TOP_5_PAGEABLE);
		return page.getContent();
	}
	
	/**
	 * 最热前5
	 * @param
	 * @return
	 */
	@Override
	public List<EsBlog> listTop5HotestEsBlogs() {
		Page<EsBlog> page = this.listHotestEsBlogsByKeyword(EMPTY_KEYWORD, TOP_5_PAGEABLE);
		return page.getContent();
	}
	
	@Override
	public List<User> listTop12Users() {
 
		List<String> usernamelist = new ArrayList<>();
		// given
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(matchAllQuery())
				.withSearchType(SearchType.QUERY_THEN_FETCH)
				.withIndices("blog").withTypes("blog")
				.addAggregation(terms("users").field("username").order(Terms.Order.count(false)).size(12))
				.build();
		// when
		Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
			@Override
			public Aggregations extract(SearchResponse response) {
				return response.getAggregations();
			}
		});
		
		StringTerms modelTerms =  (StringTerms)aggregations.asMap().get("users"); 
	        
        Iterator<Bucket> modelBucketIt = modelTerms.getBuckets().iterator();
        while (modelBucketIt.hasNext()) {
            Bucket actiontypeBucket = modelBucketIt.next();
            String username = actiontypeBucket.getKey().toString();
            usernamelist.add(username);
        }
        
        // 根据用户名，查出用户的详细信息
        List<User> list = userService.listUsersByUsernames(usernamelist);
        
		return list;
	}
}
