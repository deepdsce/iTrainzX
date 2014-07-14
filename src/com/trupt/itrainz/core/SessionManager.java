package com.trupt.itrainz.core;

import java.util.HashMap;
import java.util.Map;

import com.trupt.itrainz.model.session.Session;

public class SessionManager {
	
	private static SessionManager sessionManager;
	
	private Map<ServerEnum, Session> sessionsMap;
	
	private SessionManager() {
		sessionsMap = new HashMap<>(3);
	}
	
	public static SessionManager getInstance() {
		if(sessionManager == null) {
			sessionManager = new SessionManager();
		}
		return sessionManager;
	}
	
	public Session getSession(ServerEnum serverEnum) {
		Session session = null;
		if(sessionsMap != null && !sessionsMap.isEmpty()) {
			session = sessionsMap.get(serverEnum);
		}
		return session;
	}
	
	public Session setSession(ServerEnum serverEnum, Session session) {
		Session oldSession = null;
		if(sessionsMap != null) {
			oldSession = sessionsMap.put(serverEnum, session);
		}
		return oldSession;
	}
	
}
