package com.zyt.get_course_list_info.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zyt.get_course_list_info.dao.GetKnowledgePointInfoDAO;
import com.zyt.get_course_list_info.pojo.GetKnowledgePointInfoDataBase;
import com.zyt.get_course_list_info.pojo.GetCourseListInfoFront;

import java.util.List;

@Controller
public class GetKnowledgePointInfoController {
    @Autowired GetKnowledgePointInfoDAO GetKnowledgePointInfoDAO;

    @CrossOrigin
    // 搜索全部的数组
    @RequestMapping(value = "api/searchindex")   //处理初始界面GET请求，获取所有的数据库信息
    @ResponseBody  // 使返回结果被解析为json数据
    public List<GetKnowledgePointInfoDataBase> findAll()
    {
        List<GetKnowledgePointInfoDataBase> tp = GetKnowledgePointInfoDAO.findAll();
        System.out.println(tp);
        return tp;  // tp是后端给前端的数据接口，如果前端不接收，就直接显示在浏览器上.实际上是个JSON数据
    }

    @CrossOrigin
//    现在不用这个表去查找，所以把下面这句话注释掉
    @PostMapping(value = "api/index")   // 处理POST请求
    @ResponseBody  // 使返回结果被解析为json数据
    public List<GetKnowledgePointInfoDataBase> findByKnowledge_point(@RequestBody GetCourseListInfoFront requestSearch){
        String searchContent = requestSearch.getSearchContent();
//        System.out.println(searchContent);
        List<GetKnowledgePointInfoDataBase> tp = GetKnowledgePointInfoDAO.findByKnowledge_point(searchContent);
//        System.out.println(tp);
//        返回搜索到的匹配的JSON数组
        return tp;
    }
}
