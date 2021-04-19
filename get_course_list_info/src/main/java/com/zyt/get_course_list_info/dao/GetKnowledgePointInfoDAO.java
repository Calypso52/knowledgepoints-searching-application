package com.zyt.get_course_list_info.dao;
import com.zyt.get_course_list_info.pojo.GetKnowledgePointInfoDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GetKnowledgePointInfoDAO extends JpaRepository<GetKnowledgePointInfoDataBase,Integer>{
    List<GetKnowledgePointInfoDataBase> findAll();

    @Query(name="findByKnowledge_point",nativeQuery = true,value = "select * from knowledge_point_info_chart where knowledge_point LIKE %:knowledge_point%")  // knowledge_point_info_chart是数据库course_list_info下的表knowledge_point_info_chart，存网页搜索结果
    List<GetKnowledgePointInfoDataBase> findByKnowledge_point(@Param("knowledge_point") String knowledge_point); //knowledge_point,对数据库进行查询
}
