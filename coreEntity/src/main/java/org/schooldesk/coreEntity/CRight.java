package org.schooldesk.coreEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CRight
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String code;

	/**
	 * @deprecated exists for mapping purposes
	 */
	public CRight()
	{
	}

	public static CRight createNew(String code)
	{
		CRight coreEntity = new CRight();
		coreEntity.setCode(code);
		return coreEntity;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}
