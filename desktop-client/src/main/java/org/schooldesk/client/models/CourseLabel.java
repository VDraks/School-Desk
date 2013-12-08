package org.schooldesk.client.models;

import javax.swing.*;


public class CourseLabel extends JLabel{
	private  CourseModel model;

	public CourseLabel(CourseModel model){
		super(model.getName());
		this.model = model;
	}

	public CourseModel getModel(){
		return model;
	}
}
