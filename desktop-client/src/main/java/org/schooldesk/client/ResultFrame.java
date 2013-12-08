package org.schooldesk.client;

import org.schooldesk.client.models.TestAnswerModel;
import org.schooldesk.client.models.TestQuestionModel;
import org.schooldesk.client.models.TestResultModel;

import javax.swing.*;
import java.util.*;


public class ResultFrame extends JFrame{

	private JTextPane jta;
	private JScrollPane pane;

	public ResultFrame(TestResultModel testResult) {
		pane = new JScrollPane(jta = new JTextPane());
		add(pane);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jta.setContentType("text/html");
		StringBuilder sb = new StringBuilder("<html><tr>").append(testResult.getTestName()).append("</tr>");
		List<TestResultModel.ValidatedTestAnswerModel> validatedAnswers = testResult.getValidatedTestAnswers();

		for (TestResultModel.ValidatedTestAnswerModel vta : validatedAnswers){
			TestQuestionModel tqm = vta.getQuestionModel();
			sb.append("<tr>").append(tqm.getQuestion()).append("</tr>");
			for (TestAnswerModel tam : tqm.getAnswers()){
				String selectedAnswer = vta.getUserAnswerIds().contains(tam.getId()) ? "*" : "";
				sb.append(vta.getCorrectAnswerIds().contains(tam.getId()) ?
				          "<tr bgcolor=\"green\">" + selectedAnswer + tam.getAnswer() + "</tr>" :
				          "<tr bgcolor=\"white\">" + selectedAnswer + tam.getAnswer() + "</tr>");
			}
			sb.append("<br>");
		}
		sb.append("</html>");

		jta.setText(sb.toString());
	}
}
