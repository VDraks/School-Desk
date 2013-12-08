package org.schooldesk.client.models;

import javax.swing.*;


public class CourseSectionLabel extends JLabel{
	private CourseSectionModel sectionModel;

	public CourseSectionLabel(CourseSectionModel sectionModel){
		super(sectionModel.getName());
		this.sectionModel = sectionModel;
	}

	public CourseSectionModel getSectionModel(){
		return sectionModel;
	}
}
