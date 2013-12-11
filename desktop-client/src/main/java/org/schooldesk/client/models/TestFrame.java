package org.schooldesk.client.models;

import org.schooldesk.client.Response;
import org.schooldesk.client.ResultFrame;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;


public class TestFrame extends JFrame implements ActionListener{

	java.util.List<ToggleButtonAnswer> currentQuestionAnswers;
	Iterator<TestQuestionModel> iterator;
	private JPanel panel;
	private JPanel footer;
	private JButton next;
	private Long currentQuestionId;
	private Long testId;

	private Set<UserTestPassingModel.UserAnswerModel> userAnswers;

	private static final ObjectMapper mapper = new ObjectMapper();

	public TestFrame(TestModel model) {
		super();
		panel = new JPanel();
		footer = new JPanel();
		userAnswers = new HashSet<>();
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
		next = new JButton("Next");
		next.addActionListener(this);
		footer.add(next);
		this.iterator = model.getQuestions().iterator();
		testId = model.getId();
		showNewQuestion();
		setPreferredSize(new Dimension(800, 600));
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void showNewQuestion(){
		if (iterator.hasNext()){
			panel.removeAll();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			currentQuestionAnswers = new ArrayList<ToggleButtonAnswer>();

			TestQuestionModel model = iterator.next();
			currentQuestionId = model.getId();
			panel.add(new JLabel(model.getQuestion()));

			if (model.getTestQuestionType() == TestQuestionType.MULTIPLE_ANSWER_QUESTION) {
				for (TestAnswerModel tam : model.getAnswers()) {
					JToggleButton btn = new JCheckBox(tam.getAnswer());
					panel.add(btn);
					currentQuestionAnswers.add(new ToggleButtonAnswer(btn, tam.getId()));
				}
			}
			else if (model.getTestQuestionType() == TestQuestionType.SINGLE_ANSWER_QUESTION) {
				ButtonGroup bg = new ButtonGroup();
				for (TestAnswerModel tam : model.getAnswers()) {
					JToggleButton btn = new JRadioButton(tam.getAnswer());
					bg.add(btn);
					panel.add(btn);
					currentQuestionAnswers.add(new ToggleButtonAnswer(btn, tam.getId()));
				}
			}
			panel.repaint();
			pack();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == next){
			UserTestPassingModel.UserAnswerModel answerModel = new UserTestPassingModel.UserAnswerModel();
			answerModel.setAnswerIds(new HashSet<Long>());
			answerModel.setQuestionId(currentQuestionId);
			for (ToggleButtonAnswer tba : currentQuestionAnswers){
				if (tba.btn.isSelected()){
					answerModel.getAnswerIds().add(tba.answerId);
				}
			}
			userAnswers.add(answerModel);
			if (iterator.hasNext()){
				showNewQuestion();
			}
			else{

				UserTestPassingModel utpModel = new UserTestPassingModel();
				utpModel.setTestId(testId);
				utpModel.setUserAnswers(userAnswers);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					mapper.writeValue(baos, utpModel);
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}

				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpPost post = new HttpPost("http://127.0.0.1:8080/test/validateUserTestPassing");

				java.util.List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
				nvps.add(new BasicNameValuePair("UserTestPassing", new String(baos.toByteArray())));

				try {
					post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
					CloseableHttpResponse response = httpClient.execute(post);

					ResponseTest resp = mapper.readValue(EntityUtils.toString(response.getEntity()), ResponseTest.class);

					if (resp.isSuccess()){
						ResultFrame frame = new ResultFrame(resp.getData());
						frame.setVisible(true);
					}
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private class ToggleButtonAnswer {
		JToggleButton btn;
		Long answerId;

		private ToggleButtonAnswer(JToggleButton btn, Long answerId) {
			this.btn = btn;
			this.answerId = answerId;
		}
	}

	public static class ResponseTest {
		private boolean success;
		private String message;
		private TestResultModel data;

		ResponseTest() {
		}

		ResponseTest(boolean success, String message, TestResultModel data) {
			this.success = success;
			this.message = message;
			this.data = data;
		}

		public TestResultModel getData() {
			return data;
		}

		public void setData(TestResultModel data) {
			this.data = data;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
