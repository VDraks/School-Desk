package org.schooldesk.core.dto;

import java.util.Set;

public interface IGroupDTO extends IDTO{
    public String getName();

    public void setName(String name);

    public Set<Long> getRightsIds();

    public void setRightsIds(Set<Long> rightsIds);
}
