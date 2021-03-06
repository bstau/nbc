package com.nbcedu.function.schoolmaster2.data.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class TSm2Subject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private String createrName;
	private Date createTime;
	private String content;
	private Integer flag;
	private String departmentId;
	private String title;
	private String moduleId;
	private String status;//new:新建立,updated:最近更新,attention:关注的,common:普通的
	private Date lastUpdateTime;
	private String parentId;
	private String typeId;
	private Integer progress;
	private String departmentName;
	/*
	 * 执行者
	 */
	private Set<TSm2SubjectUser> excuteUsers=new HashSet<TSm2SubjectUser>();

	private Set<SM2SubjectMaster> checkUsers=new HashSet<SM2SubjectMaster>();
	// Constructors

	/** default constructor */
	public TSm2Subject() {
	}
	public TSm2Subject(String id,String title) {
		this.id = id;
		this.title=title;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<TSm2SubjectUser> getExcuteUsers() {
		return excuteUsers;
	}

	public void setExcuteUsers(Set<TSm2SubjectUser> excuteUsers) {
		this.excuteUsers = excuteUsers;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Set<SM2SubjectMaster> getCheckUsers() {
		return checkUsers;
	}

	public void setCheckUsers(Set<SM2SubjectMaster> checkUsers) {
		this.checkUsers = checkUsers;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}