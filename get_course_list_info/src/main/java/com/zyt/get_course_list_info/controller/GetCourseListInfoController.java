package com.zyt.get_course_list_info.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zyt.get_course_list_info.dao.GetCourseListInfoDAO;
import com.zyt.get_course_list_info.pojo.GetCourseListInfoDatabase;
import com.zyt.get_course_list_info.pojo.GetCourseListInfoFront;

import java.util.List;

@Controller
public class GetCourseListInfoController {
    @Autowired GetCourseListInfoDAO getCourseListInfoDAO;

    @CrossOrigin
//    现在不用这个表去查找，所以把下面这句话注释掉
//    @PostMapping(value = "api/index")   // 处理POST请求
    @ResponseBody  // 使返回结果被解析为json数据
    public List<GetCourseListInfoDatabase> findByCourse_name(@RequestBody GetCourseListInfoFront requestSearch){
        String searchContent = requestSearch.getSearchContent();
//        System.out.println(searchContent);
        List<GetCourseListInfoDatabase> tp = getCourseListInfoDAO.findByCourse_name(searchContent);
//        System.out.println(tp);
//        返回搜索到的匹配的JSON数组
        return tp;
    }
}
