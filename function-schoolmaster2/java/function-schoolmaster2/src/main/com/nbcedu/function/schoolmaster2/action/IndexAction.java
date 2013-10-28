package com.nbcedu.function.schoolmaster2.action;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.CollectionUtils;

import com.nbcedu.function.schoolmaster2.biz.SM2ModuleBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Module;
import com.nbcedu.function.schoolmaster2.data.util.HibernateDao;
import com.nbcedu.function.schoolmaster2.utils.UCService;

/**
 * 
 * @author xuechong
 */
@SuppressWarnings("serial")
public class IndexAction extends BaseAction{
	
	private String rightURL;
	private static final Logger logger = Logger.getLogger(IndexAction.class);
	private String photoPath ;
	private String userName;
	private String userPhrase;
	private HibernateDao dao;
	private String start;
	private String end;
	private String matcher;
	
	private SM2ModuleBiz sm2ModuleBiz;
	
	public String index(){
		this.photoPath = this.getPhoto();
		if(logger.isInfoEnabled()){
			logger.info(photoPath);
		}
		this.userPhrase = this.getPhrase();
		this.userName = (getSession().get("curUserName")!=null?
				getSession().get("curUserName").toString():
				getSession().put("curUserName", UCService.findNameByUid(getUserId())).toString());
		return "index";
	}
	
	public String home(){
		return "home";
	}
	public String login(){
		this.rightURL += "?matcher="+matcher;
		return "home";
	}
	
	public String teacherInput(){
		List<TSm2Module> modules = this.sm2ModuleBiz.findAll();
		this.getRequest().setAttribute("modules", modules);
		return "teacherInput";
	}
	
	private String getPhoto(){
		final String uid = this.getUserId();
		String photo = (String) dao.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query q = session.getNamedQuery("index_photo");
				q.setString("uid", uid);
				List result = q.list();
				return CollectionUtils.isEmpty(result)?"":result.get(0).toString();
			}
		});
		return StringUtils.trimToEmpty(photo);
	}
	
	private String getPhrase(){
		final String uid = this.getUserId();
		String phrase = (String) dao.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query q = session.getNamedQuery("index_phrase");
				q.setString("uid", uid);
				List result = q.list();
				return CollectionUtils.isEmpty(result)?"":result.get(0).toString();
			}
		});
		return StringUtils.trimToEmpty(phrase);
	}
	////////////////////////////////
	/////getters&setters//////
	/////////////////////////////
	public String getRightURL() {
		return rightURL;
	}
	public void setRightURL(String rightURL) {
		this.rightURL = rightURL;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public void setDao(HibernateDao dao) {
		this.dao = dao;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhrase() {
		return userPhrase;
	}
	public void setUserPhrase(String userPhrase) {
		this.userPhrase = userPhrase;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = (start==null)?"":start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = (end==null)?"":end;
	}

	public String getMatcher() {
		return matcher;
	}

	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

	public void setSm2ModuleBiz(SM2ModuleBiz sm2ModuleBiz) {
		this.sm2ModuleBiz = sm2ModuleBiz;
	}
	
}
