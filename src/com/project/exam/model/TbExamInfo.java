package com.project.exam.model;

import java.util.Date;

public class TbExamInfo {
    private Integer id;

    private Integer examId;

    private Integer questionId;

    private Integer sort;

    private Integer isRight;

    private Date createTime;

    private String myanswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsRight() {
        return isRight;
    }

    public void setIsRight(Integer isRight) {
        this.isRight = isRight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMyanswer() {
        return myanswer;
    }

    public void setMyanswer(String myanswer) {
        this.myanswer = myanswer == null ? null : myanswer.trim();
    }
}