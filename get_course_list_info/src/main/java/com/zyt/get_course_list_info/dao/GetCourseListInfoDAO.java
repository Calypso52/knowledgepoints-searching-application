package com.zyt.get_course_list_info.dao;
import com.zyt.get_course_list_info.pojo.GetCourseListInfoDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GetCourseListInfoDAO extends JpaRepository<GetCourseListInfoDatabase,Integer>{
    // 添加功能，按照关键字查询course_name ，这里一定要把 * 用上不然出问题，猜想可能是和主键设置有关？
    //新建一个查询，名字叫findByCourse_name，查询语句是后面那句
    @Query(name="findByCourse_name",nativeQuery = true,value = "select * from course_list_info_chart where course_name LIKE %:course_name%")  //course_list_info_chart是数据库course_list_info下的表course_list_info_chart，存网页搜索结果
    List<GetCourseListInfoDatabase> findByCourse_name(@Param("course_name") String course_name); //引用course_name,对数据库进行查询

    // 找到表中所有数据
    List<GetCourseListInfoDatabase> findAll();
}
