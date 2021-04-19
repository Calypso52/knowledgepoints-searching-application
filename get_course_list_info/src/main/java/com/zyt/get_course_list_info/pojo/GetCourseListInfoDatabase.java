// 用于映射数据库到后端，数据库：course_list_info，表名：course_list_info_chart，映射过来叫：GetCourseListInfoDatabase
package com.zyt.get_course_list_info.pojo;

import javax.persistence.*;

@Entity
@Table(name= "course_list_info_chart")//数据库表名
public class GetCourseListInfoDatabase {
    @Id//定义主键，该注解下面的属性即为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "course_id")
    private int course_id;

    //定义表中的每一列，即每一个属性
    @Column(name="url")
    private String url;

    @Column(name="course_name")
    private String course_name;

    @Column(name="term")
    private String term;

    @Column(name="platform")
    private String platform;

    @Column(name="school")
    private String school;

    @Column(name="teacher")
    private String teacher;

    @Column(name="team")
    private String team;

    @Column(name="start_date")
    private String start_date;

    @Column(name="end_date")
    private String end_date;

    @Column(name="save_time")
    private String save_time;

    @Column(name="status")
    private String status;

    @Column(name="extra")
    private String extra;

    @Column(name="crowd")
    private String crowd;

    @Column(name="clicked")
    private String clicked;

    @Column(name="isquality")
    private String isquality;

    @Column(name="course_score")
    private String course_score;

    @Column(name="introduction")
    private String introduction;

    @Column(name="subject")
    private String subject;

    @Column(name="course_target")
    private String course_target;

    @Column(name="scoring_standard")
    private String scoring_standard;

    @Column(name="isfree")
    private String isfree;

    @Column(name="certification")
    private String certification;

    @Column(name="ispublic")
    private String ispublic;

    @Column(name="term_id")
    private String term_id;

    @Column(name="total_crowd")
    private String total_crowd;

    @Column(name="update_time")
    private String update_time;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSave_time() {
        return save_time;
    }

    public void setSave_time(String save_time) {
        this.save_time = save_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd;
    }

    public String getClicked() {
        return clicked;
    }

    public void setClicked(String clicked) {
        this.clicked = clicked;
    }

    public String getIsquality() {
        return isquality;
    }

    public void setIsquality(String isquality) {
        this.isquality = isquality;
    }

    public String getCourse_score() {
        return course_score;
    }

    public void setCourse_score(String course_score) {
        this.course_score = course_score;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourse_target() {
        return course_target;
    }

    public void setCourse_target(String course_target) {
        this.course_target = course_target;
    }

    public String getScoring_standard() {
        return scoring_standard;
    }

    public void setScoring_standard(String scoring_standard) {
        this.scoring_standard = scoring_standard;
    }

    public String getIsfree() {
        return isfree;
    }

    public void setIsfree(String isfree) {
        this.isfree = isfree;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getTerm_id() {
        return term_id;
    }

    public void setTerm_id(String term_id) {
        this.term_id = term_id;
    }

    public String getTotal_crowd() {
        return total_crowd;
    }

    public void setTotal_crowd(String total_crowd) {
        this.total_crowd = total_crowd;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
