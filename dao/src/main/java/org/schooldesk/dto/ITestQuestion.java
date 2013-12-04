package org.schooldesk.dto;

import java.util.Set;


public interface ITestQuestion extends IResource {
	String getQuestion();
	void setQuestion(String question);

	TestQuestionType getType();
	void setType(TestQuestionType type);

	Set<Long> getAnswerIds();
	void setAnswerIds(Set<Long> answerIds);

	Set<Long> getCorrectAnswerIds();
	void setCorrectAnswerIds(Set<Long> answerIds);
}
