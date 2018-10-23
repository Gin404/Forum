package com.cityu.forum.controller;

import com.cityu.forum.domain.User;
import com.cityu.forum.domain.es.EsBlog;
import com.cityu.forum.service.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Blog 控制器.
 *
 * @since 1.0.0 2018年10月1日
 * @author Gin
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private EsBlogService esBlogService;

    @GetMapping
    public String listEsBlogs(
            @RequestParam(value="order",required=false,defaultValue="new") String order,
            @RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
            @RequestParam(value="indexlocation",required=false,defaultValue="全部" ) String location,
            @RequestParam(value="indextags",required=false,defaultValue="全部" ) String tags,
            @RequestParam(value="indexdate",required=false,defaultValue="" ) String deliverDate,
            @RequestParam(value="async",required=false) boolean async,
            @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
            @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
            Model model) {

        Page<EsBlog> page = null;
        List<EsBlog> list = null;

        boolean isEmpty = true; // 系统初始化时，没有博客数据

        //location--1
        //tags--2
        //deliverDate--3
        //keyword--4
        //location & tags--5
        //location & deliverDate--6
        //tags & deliverDate--7
        //location & keyword--8
        //tags & keyword--9
        //deliverDate & keyword--10
        //tags & deliverDate & location--11
        //tags & deliverDate & keyword--12
        //deliverDate & location & keyword--13
        //tags & location & keyword--14
        //tags & deliverDate & location & keyword--15
        //null -- 16

        //int choice = 0;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        try {
            if ((!location.equals("全部")) && tags.equals("全部") && deliverDate.equals("") && keyword.equals("")) {
                //choice = 1;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByLocation(location, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByLocation(location, pageable);
                }
            } else if (location.equals("全部") && (!tags.equals("全部")) && deliverDate.equals("") && keyword.equals("")) {
                //choice = 2;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByTags(tags, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByTags(tags, pageable);
                }
            } else if (location.equals("全部") && tags.equals("全部") && (!deliverDate.equals("")) && keyword.equals("")) {
                //choice = 3;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByRequireTime(deliverDate, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByRequireTime(deliverDate, pageable);
                }
            } else if (location.equals("全部") && tags.equals("全部") && deliverDate.equals("")) {
                //choice = 4;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByKeyword(keyword, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByKeyword(keyword, pageable);
                }
            } else if ((!location.equals("全部")) && (!tags.equals("全部")) && deliverDate.equals("") && keyword.equals("")) {
                //choice = 5;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByLocationAndTags(location, tags, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByLocationAndTags(location, tags, pageable);
                }
            } else if ((!location.equals("全部")) && tags.equals("全部") && (!deliverDate.equals("")) && keyword.equals("")) {
                //choice = 6;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByLocationAndRequiretime(location, deliverDate, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByLocationAndRequiretime(location, deliverDate, pageable);
                }
            } else if (location.equals("全部") && (!tags.equals("全部")) && (!deliverDate.equals("")) && keyword.equals("")) {
                //choice = 7;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByTagsAndRequiretime(tags, deliverDate, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByTagsAndRequiretime(tags, deliverDate, pageable);
                }
            } else if ((!location.equals("全部")) && tags.equals("全部") && deliverDate.equals("") && (!keyword.equals(""))) {
                //choice = 8;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByKeywordAndLocation(keyword, location, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByKeywordAndLocation(keyword, location, pageable);
                }
            } else if (location.equals("全部") && (!tags.equals("全部")) && deliverDate.equals("") && (!keyword.equals(""))) {
                //choice = 9;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByKeywordAndTags(keyword, tags, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByKeywordAndTags(keyword, tags, pageable);
                }
            } else if (location.equals("全部") && tags.equals("全部") && (!deliverDate.equals("")) && (!keyword.equals(""))) {
                //choice = 10;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByKeywordAndRequireTime(keyword, deliverDate, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByKeywordAndRequireTime(keyword, deliverDate, pageable);
                }
            } else if ((!location.equals("全部")) && (!tags.equals("全部")) && (!deliverDate.equals("")) && keyword.equals("")) {
                //choice = 11;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByLocationAndTagsAndRequireTime(location, tags, deliverDate, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByLocationAndTagsAndRequireTime(location, tags, deliverDate, pageable);
                }
            } else if ((!location.equals("全部")) && (!tags.equals("全部")) && deliverDate.equals("") && (!keyword.equals(""))) {
                //choice = 12;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByLocationAndTagsAndKeyword(location, tags, keyword, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByLocationAndTagsAndKeyword(location, tags, keyword, pageable);
                }
            } else if ((!location.equals("全部")) && tags.equals("全部") && (!deliverDate.equals("")) && (!keyword.equals(""))) {
                //choice = 13;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByKeywordAndLocationAndRequireTime(keyword, location, deliverDate, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByKeywordAndLocationAndRequireTime(keyword, location, deliverDate, pageable);
                }
            } else if ((!location.equals("全部")) && (!tags.equals("全部")) && deliverDate.equals("") && (!keyword.equals(""))) {
                //choice = 14;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByLocationAndTagsAndKeyword(location, tags, keyword, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByLocationAndTagsAndKeyword(location, tags, keyword, pageable);
                }
            } else if ((!location.equals("全部")) && (!tags.equals("全部")) && (!deliverDate.equals("")) && (!keyword.equals(""))) {
                //choice = 15;
                if (order.equals("hot")) {
                    page = esBlogService.listHotestEsBlogsByLocationAndTagsAndKeywordAndRequireTime(location, tags, keyword, deliverDate, pageable);
                } else if (order.equals("new")) {
                    page = esBlogService.listNewestEsBlogsByLocationAndTagsAndKeywordAndRequireTime(location, tags, keyword, deliverDate, pageable);
                }
            }

            isEmpty = false;
        }catch (Exception e){
            page = esBlogService.listEsBlogs(pageable);
        }

        list = page.getContent();   // 当前所在页面数据列表


        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("location", location);
        model.addAttribute("tags", tags);
        model.addAttribute("deliverDate", deliverDate);
        model.addAttribute("page", page);
        model.addAttribute("blogList", list);

        // 首次访问页面才加载
        if (!async && !isEmpty) {
            List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
            model.addAttribute("newest", newest);
            List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
            model.addAttribute("hotest", hotest);
            List<User> users = esBlogService.listTop12Users();
            model.addAttribute("users", users);
        }

        return (async==true?"/index :: #mainContainerRepleace":"/index");
    }

}
