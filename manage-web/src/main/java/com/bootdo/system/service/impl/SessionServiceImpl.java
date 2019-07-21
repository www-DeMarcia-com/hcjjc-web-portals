package com.bootdo.system.service.impl;

import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserOnline;
import com.bootdo.system.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 待完善
 * 
 * @author bootdo
 *
 */
@Service
public class SessionServiceImpl implements SessionService {
	@Autowired
	private SessionDAO sessionDAO;

	@Override
	public List<UserOnline> list() {
		List<UserOnline> list = new ArrayList<>();
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		for (Session session : sessions) {
			UserOnline userOnline = new UserOnline();
			UserDO userDO = new UserDO();
			SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
			if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
				continue;
			} else {
				principalCollection = (SimplePrincipalCollection) session
						.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				userDO = (UserDO) principalCollection.getPrimaryPrincipal();
				userOnline.setUsername(userDO.getUsername());
			}
			userOnline.setId((String) session.getId());
			userOnline.setHost(session.getHost());
			userOnline.setStartTimestamp(session.getStartTimestamp());
			userOnline.setLastAccessTime(session.getLastAccessTime());
			userOnline.setTimeout(session.getTimeout());
			list.add(userOnline);
		}
		return list;
	}

	@Override
	public Collection<Session> sessionList() {
		return sessionDAO.getActiveSessions();
	}

	@Override
	public int forceLogout(String sessionId,Long userid) {
		Collection<Session> sessions = sessionList();
		for (Session session : sessions) {
			UserDO userDO = new UserDO();
			SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
			if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
				continue;
			} else {
				principalCollection = (SimplePrincipalCollection) session
						.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				userDO = (UserDO) principalCollection.getPrimaryPrincipal();
			}
			if(userDO.getUserId()==1l) {
				return 1;
			}
			if(userDO.getUserId()==userid) {
				return 2;
			}
		}
		/*for (Session session2 : sessions) {
			UserDO userDO = new UserDO();
			SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
			if (session2.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
				continue;
			} else if(session2.getId().equals(sessionId)){
				principalCollection = (SimplePrincipalCollection) session2
						.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				userDO = (UserDO) principalCollection.getPrimaryPrincipal();
				if(userDO.getDeptId()==1l) {
					return 1;
				}
				if(userDO.getDeptId()==userid) {
					return 2;
				}
			}
		}*/
		Session session = sessionDAO.readSession(sessionId);
		session.setTimeout(0);
		return 3;
	}
}
