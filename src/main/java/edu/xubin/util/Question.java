package edu.xubin.util;

public class Question {

    private String questioName;
    private String questionDescribe;

    public String getQuestionName() {
        return questioName;
    }

    public void setQuestionName(String question) {
        this.questioName = question;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }

    public Question(String questioName, String questionDescribe) {
        this.questioName = questioName;
        this.questionDescribe = questionDescribe;
    }
}
