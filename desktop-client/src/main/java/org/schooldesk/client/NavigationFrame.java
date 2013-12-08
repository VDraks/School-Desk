package org.schooldesk.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.schooldesk.client.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Set;


public class NavigationFrame extends JFrame {

	private JPanel panel;
	private MouseAdapter adapter;

	private static final ObjectMapper mapper = new ObjectMapper();

	public NavigationFrame(Set<CourseModel> courseModels){
		super();
		panel = new JPanel();
		adapter = new Adapter();
		add(panel, BorderLayout.CENTER);
		createAndLayoutCourseLabels(courseModels);
		pack();
		setPreferredSize(new Dimension(800, 600));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void createAndLayoutCourseLabels(Set<CourseModel> courseModels){
		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		if (courseModels != null){
			for (CourseModel model : courseModels){
				JLabel label = new CourseLabel(model);
				label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label.addMouseListener(adapter);
				panel.add(label);
			}
		}
	}

	private void createAndLayoutCourseSectionLabels(Set<CourseSectionModel> courseSectionModels){
		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		if (courseSectionModels != null){
			for (CourseSectionModel model : courseSectionModels){
				JLabel label = new CourseSectionLabel(model);
				label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label.addMouseListener(adapter);
				panel.add(label);
			}
		}
	}

	private class Adapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource().getClass() == CourseLabel.class){

				CourseLabel courceLabel = (CourseLabel) e.getSource();
				CloseableHttpClient httpClient = HttpClients.createDefault();
				try {
					URI uri = new URIBuilder()
							.setScheme("http")
							.setHost("127.0.0.1")
							.setPort(8080)
							.setPath("/course/getCourseSections")
							.setParameter("CourseId", courceLabel.getModel().getId().toString())
							.build();
					HttpGet httpget = new HttpGet(uri);

					CloseableHttpResponse response = httpClient.execute(httpget);

					Response resp = mapper.readValue(EntityUtils.toString(response.getEntity()), Response.class);
					if (resp.isSuccess()){
						createAndLayoutCourseSectionLabels((Set<CourseSectionModel>) resp.getData());
					}
				}
				catch (Exception e1) {
				}
			} else if (e.getSource().getClass() == CourseSectionLabel.class){
				CourseSectionLabel courceSectionLabel = (CourseSectionLabel) e.getSource();
				CloseableHttpClient httpClient = HttpClients.createDefault();
				try {
					URI uri = new URIBuilder()
							.setScheme("http")
							.setHost("127.0.0.1")
							.setPort(8080)
							.setPath("/test/getTest")
							.setParameter("CourseSectionId", courceSectionLabel.getSectionModel().getId().toString())
							.build();
					HttpGet httpget = new HttpGet(uri);

					CloseableHttpResponse response = httpClient.execute(httpget);
					Response resp = mapper.readValue(EntityUtils.toString(response.getEntity()), Response.class);

					if (resp.isSuccess()){
						TestFrame frame = new TestFrame((TestModel) resp.getData());
						frame.setVisible(true);
					}
				}
				catch (Exception e1) {
				}
			}
		}
	}
}
