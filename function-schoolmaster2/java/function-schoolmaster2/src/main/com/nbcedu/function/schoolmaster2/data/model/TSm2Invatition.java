package com.nbcedu.function.schoolmaster2.data.model;

import java.util.Date;
import java.util.List;

import com.nbcedu.function.schoolmaster2.utils.UCService;


public class TSm2Invatition implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private Date createTime;
	private String content;
	private String flag;
	private String title;
	private String status = "0";
	private Date lastUpdateTime;
	private String link;
	private Integer score = -1;
	private List<String> usersId;

	// Constructors

	/** default constructor */
	public TSm2Invatition() {
	}

	public TSm2Invatition(String createrId) {
		this.createrId=createrId;
	}

	/** full constructor */
	public TSm2Invatition(String id,String createrId,Date createTime,String content,String flag,String title,String status,Date lastUpdateTime,String link,String invatId,Integer score){
		this.id=id;
		this.createrId=createrId;
		this.createTime=createTime;
		this.content=content;
		this.flag=flag;
		this.title=title;
		this.status=status;
		this.lastUpdateTime=lastUpdateTime;
		this.link=link;
		this.score=score;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the createrId
	 */
	public String getCreaterId() {
		return createrId;
	}
	
	/**
	 * @return the createrId
	 */
	public String getCreaterName() {
		String name = UCService.findNameByUid(createrId);
		if (name!=null && name.trim().length()>0) {
			return name;
		}
		return createrId;
	}

	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}


//	/**
//	 * @return the invatId
//	 */
//	public String getInvatName() {
//		String name = UCService.findNameByUid(invatId);
//		if (name!=null && name.trim().length()>0) {
//			return name;
//		}
//		return invatId;
//	}


	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	public List<String> getUsersId() {
		return usersId;
	}

	public void setUsersId(List<String> usersId) {
		this.usersId = usersId;
	}
	
}