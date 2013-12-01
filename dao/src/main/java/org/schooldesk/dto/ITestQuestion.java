package org.schooldesk.dto;

import java.util.Set;


public interface ITestQuestion extends IResource {
	String getQuestion();
	void setQuestion(String question);

	TestQuestionType getType();
	void setType(TestQuestionType type);

	Set<ITestAnswer> getAnswers();
	void setAnswers(Set<ITestAnswer> answers);

	Set<ITestAnswer> getCorrectAnswers();
	void setCorrectAnswers(Set<ITestAnswer> answers);
}
