package com.project.exam.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbQuestion {
    private Integer id;

    private Integer subject;

    private String title;

    private Integer type;

    private String answer;

    private String attachment;

    private Date createTime;

    private Date updateTime;

    private String analysis;
    
    private Integer examId;
    
    private Integer examInfoId;
    
    private String myanswer;
    
    private Integer isRight;
    
    private Integer sort;
    
    private List<TbOptions> options = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis == null ? null : analysis.trim();
    }

	public List<TbOptions> getOptions() {
		return options;
	}

	public void setOptions(List<TbOptions> options) {
		this.options = options;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}


	public Integer getExamInfoId() {
		return examInfoId;
	}

	public void setExamInfoId(Integer examInfoId) {
		this.examInfoId = examInfoId;
	}

	public String getMyanswer() {
		return myanswer;
	}

	public void setMyanswer(String myanswer) {
		this.myanswer = myanswer;
	}

	public Integer getIsRight() {
		return isRight;
	}

	public void setIsRight(Integer isRight) {
		this.isRight = isRight;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}