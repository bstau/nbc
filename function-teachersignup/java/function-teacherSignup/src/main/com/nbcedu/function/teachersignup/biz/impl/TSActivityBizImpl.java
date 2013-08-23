package com.nbcedu.function.teachersignup.biz.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashSet;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.constants.ActStatus;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.core.pager.PagerModel;
import com.nbcedu.function.teachersignup.dao.TSActivityDao;
import com.nbcedu.function.teachersignup.dao.TSRewardDao;
import com.nbcedu.function.teachersignup.dao.TSSubjectDao;
import com.nbcedu.function.teachersignup.model.TSActivity;
import com.nbcedu.function.teachersignup.model.TSReward;
import com.nbcedu.function.teachersignup.model.TSSubject;


public class TSActivityBizImpl extends BaseBizImpl<TSActivity> implements TSActivityBiz{
	
	private static final Logger logger = Logger.getLogger(TSActivityBizImpl.class);
	private TSActivityDao actDao;
	private TSRewardDao rewardDao;
	private TSSubjectDao subjectDao;
	
	public void setActDao(TSActivityDao actDao) {
		super.setDao(actDao);
		this.actDao = actDao;
	}

	@Override
	public void addOrUpdate(TSActivity act, String[] subs,String[] rews) {
		
		if(StringUtils.isNotBlank(act.getId())){
			this.actDao.createQuery("DELETE FROM TSReward r WHERE r.activityId=?",act.getId()).executeUpdate();
			this.actDao.createQuery("DELETE FROM TSSubject s WHERE s.activityId=?", act.getId());
			act = this.findById(act.getId());
		}else{
			act.setId(null);
		}
		
		act.setSubjects(new HashSet<TSSubject>());		
		act.setRewards(new HashSet<TSReward>());
		
		if(rews!=null&&rews.length>0){
			for (String re :rews) {
				if(StringUtils.isNotBlank(re)){
					TSReward rewa = new TSReward();
					try {
						rewa.setName(URLDecoder.decode(re,"utf-8"));
					} catch (UnsupportedEncodingException e) {
						logger.error(e);
					}
					act.getRewards().add(rewa);
				}
			}
		}
		
		if(subs!=null&&subs.length>0){
			for(String su:subs){
				if(StringUtils.isNotBlank(su)){
					TSSubject subj = new TSSubject();
					try {
						subj.setName(URLDecoder.decode(su, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						logger.error(e);
					}
					act.getSubjects().add(subj);
				}
			}
		}
		
		this.actDao.saveOrUpdate(act);
	}
	
	public List<TSActivity> findAll(){
		return actDao.getAll();
	}
	
	@Override
	public PagerModel findAllByPage() {
		return this.actDao.searchPaginated("FROM TSActivity a ORDER BY a.createDate DESC");
	}
	
	@Override
	public PagerModel findByMonthStatus(Integer month, Integer status) {
		Criteria cri = this.actDao.createCriteria();
		
		if(month!=null && month >0){
			Date beforeDate = new Date(
					new Date().getTime() - 1000L*60L*60L*24L*30L*month.longValue()
			);
			cri.add(
				Restrictions.gt("createDate", beforeDate)		
			);
		}
		
		if(status!=null&&ActStatus.findById(status)!=null){
			cri.add(Restrictions.eq("status", status));
		}
		
		cri.addOrder(Order.desc("createDate"));
		return this.actDao.searchPaginated(cri);
	}
	
	@Override
	public void modifyStatus(String id, ActStatus status) {
		this.actDao.createQuery("UPDATE TSActivity a SET a.status=? WHERE a.id=?", status.getId(),id).executeUpdate();
	}
	
	@Override
	public void modifyFinActs() {
		this.actDao.createQuery("UPDATE TSActivity a SET a.status = ? WHERE a.endDate < ?",
					ActStatus.FINISHED.getId(),new Date() ).executeUpdate();
	}
	
	@Override
	public TSActivity removeById(Serializable id) {
		return super.removeById(id);
	}
	
	@Override
	public void removeActById(String id) {
		TSActivity act = this.findById(id);
		this.actDao.createQuery("DELETE FROM TSSign s WHERE s.subjectId in (FROM TSSubject sub WHERE sub.activityId=?)", id).executeUpdate();
		if(act!=null){
			if(act.getRewards()!=null){
				for (TSReward rew : act.getRewards()) {
					this.rewardDao.remove(rew);
				}
			}
			if(act.getSubjects()!=null){
				for (TSSubject sub : act.getSubjects()) {
					this.subjectDao.remove(sub);
				}
			}
		}
		
		this.removeById(id);
	}
	
	@Override
	public List<TSActivity> findByStatus(ActStatus status) {
		return this.actDao.findBy("status", status.getId());
	}

	@Override
	public PagerModel findFinished() {
		Criteria cri = this.actDao.createCriteria(
				Restrictions.in("status", 
				new Integer[]{ActStatus.FINISHED.getId(),ActStatus.PAUSED.getId()}));
		cri.addOrder(Order.desc("endDate"));
		return this.actDao.searchPaginated(cri);
	}
}
