/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2;

import SQL.SQLHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author erino
 */
public class Question {

    private final String QuestionID;
    private final String CategoryID;
    private final String question;
    private final String answer;
    private final String wrongAns1;
    private final String wrongAns2;
    private final String wrongAns3;

    public Question(String quest, String catID, String correct, String wrong1, String wrong2, String wrong3) throws SQLException {

        SQLHandler sql = new SQLHandler();
        int questionCount = sql.getAllQuestions().size();

        QuestionId = String.valueOf(questionCount++);
        CategoryId = catID;
        userQuestion = quest;
        correctAns = correct;
        wrongAns1 = wrong1;
        wrongAns2 = wrong2;
        wrongAns3 = wrong3;
    
    }
    
    public Question(String question) throws SQLException {
        SQLHandler sql = new SQLHandler();
        ArrayList<String> questioninfo = sql.searchUsersTable(question);

        
        QuestionId = questioninfo.get(0);
        CategoryId = questioninfo.get(1);
        userQuestion = questioninfo.get(2);
        correctAns = questioninfo.get(3);
        wrongAns1 = questioninfo.get(4);
        wrongAns2 = questioninfo.get(5);
        wrongAns3 = questioninfo.get(6);
       
    }
    
     public void createQuestion(Question question) throws SQLException {
        SQLHandler sql = new SQLHandler();
        sql.createQuestion(question.getQuestionId(), question.getCategoryId(), question.getUserQuestion(), question.getCorrectAnswer(), question.getWrongAnswer1(), question.getWrongAnswer2(), question.getWrongAnswer3());
    }
     
      public String getQuestionId() {
        return this.QuestionId;
    }

    public String getCategoryId() {
        return this.CategoryId;
    }

    public String getUserQuestion() {
        return this.userQuestion;
    }

    public String getCorrectAnswer() {
        return this.correctAns;
    }

    public String getWrongAnswer1() {
        return this.wrongAns1;
    }

    public String getWrongAnswer2() {
        return this.wrongAns2;
    }

    public String getWrongAnswer3() {
        return this.wrongAns3;
    }

   
}
