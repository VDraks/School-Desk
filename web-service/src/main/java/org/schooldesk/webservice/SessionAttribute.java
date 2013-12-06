package org.schooldesk.webservice;

import javax.servlet.http.HttpSession;


public enum SessionAttribute {
	USER_ID;

	public void setValue(HttpSession session, Object value) {
		session.setAttribute(this.toString(), value);
	}

	public Object getValue(HttpSession session) {
		return session.getAttribute(this.toString());
	}
}
