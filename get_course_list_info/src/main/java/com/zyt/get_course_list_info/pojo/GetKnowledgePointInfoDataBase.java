package com.zyt.get_course_list_info.pojo;

import javax.persistence.*;

@Entity
@Table(name= "knowledge_point_info_chart")//数据库表名
public class GetKnowledgePointInfoDataBase {
    @Id//定义主键，该注解下面的属性即为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "knowledge_id")
    private int knowledge_id;

    //定义表中的每一列，即每一个属性
    @Column(name="knowledge_point")
    private String knowledge_point;

    @Column(name="teacher")
    private String teacher;

    @Column(name="teacher_style")
    private String teacher_style;

    @Column(name="course_id")
    private int course_id;

    @Column(name="course_name")
    private String course_name;

    @Column(name="school")
    private String school;

    @Column(name="introduction")
    private String introduction;

    @Column(name="isquality")
    private String isquality;

    @Column(name="status")
    private String status;

    @Column(name="hot_knowledge")
    private int hot_knowledge;

    @Column(name="course_img")
    private String course_img;

    public int getKnowledge_id() {
        return knowledge_id;
    }
    public void setKnowledge_id(int knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public String getKnowledge_point() {
        return knowledge_point;
    }
    public void setKnowledge_point(String knowledge_point) {
        this.knowledge_point = knowledge_point;
    }

    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher_style() {
        return teacher_style;
    }
    public void setTeacher_style(String teacher_style) {
        this.teacher_style = teacher_style;
    }

    public int getCourse_id() {
        return course_id;
    }
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIsquality() {
        return isquality;
    }
    public void setIsquality(String isquality) {
        this.isquality = isquality;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getHot_knowledge() {
        return hot_knowledge;
    }
    public void setHot_knowledge(int hot_knowledge) {
        this.hot_knowledge = hot_knowledge;
    }

    public String getCourse_img() {
        return course_img;
    }
    public void setCourse_img(String course_img) {
        this.course_img = course_img;
    }
}
