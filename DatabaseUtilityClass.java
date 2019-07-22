/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DepressionDetection.DatabaseConnection;

import DepressionDetection.*;

/**
 *
 * @author PETER-PC
 */
public class DatabaseUtilityClass {
    
    //add database utility methods as required

    
    public static List<User> getUserList(){
        String sql = "SELECT * FROM User ORDER BY USERID";
        List<User> userList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql));
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                int USERID = resultSet.getInt();
                String USERNAME = resultSet.getString();
                String USERTYPE = resultSet.getString();

                User user = new User(USERID, USERNAME, USERTYPE);
                userList.add(user);
            }
            return userList;
        }catch(SQLException exception) {
            throw new Exception(exception.getMessage());
        }
    }
    
    public static void getFriendList(String username){
        String sql = "SELECT * FROM User WHERE USERNAME = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String [] friendList = resultSet.getString();
                resultSet.close();

                SocialMediaUser mediaUser = new SocialMediaUser(null, null, friendList, null, null, null);
                return mediaUser;

            } else {
                resultSet.close();
                return null;
            }
        } catch(SQLException exception) {
            throw new Exception(exception.getMessage());
        }

    }
    
    public static void getUserActivity(String username){}
    
//    public static void getUserMentalHealthStatus(String username){
//        String  sql = "SELECT * FROM UserMentalHealthStatus WHERE USERNAME = ?";
//        Connection connection = DBUtil.getConnection();
//        try (PreparedStatement preparedStatement =
//                connection.prepareStatement(sql)){
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                int User_ID = resultSet.getInt();
//                byte[] Variability = resultSet.getBytes();
//                byte[] Instability = resultSet.getBytes();
//                byte[] DiagnosisHistory = resultSet.getBytes();
//                String CurrDeprTypeID = resultSet.getString();
//
//                resultSet.close();
//
//                UserMentalHealthStatus mentalHealthStatus =
//                        new UserMentalHealthStatus(User_ID, Variability, Instability, DiagnosisHistory, null, null);
//
//
//                return mentalHealthStatus;
//            } else {
//                resultSet.close();
//                return null;
//            } catch (SQLException e) {
//                throw new Exception(e.getMessage());
//            }
//        }
//    }
    
    public static List<Answer> getQuestionnaireResult(){
        String sql = "SELECT * FROM QuestionnaireA ORDER BY questionID";
        List<Answer> questionnaireAnswers = new ArrayList<>();
        Connection connection = DBUtil.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql));
        ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                int answerID = resultSet.getInt();
                String answerText = resultSet.getString();
                int questionID = resultSet.getInt();


                Answer answer = new Answer(answerID, answerText, questionID);
                questionnaireAnswers.add(answer);
            }
            return answer;
        }catch(SQLException exception) {
            throw new Exception(exception.getMessage());
        }
    }
    
    public static List<Post> getPostsClassification(){
        String sql = "SELECT * FROM Post ORDER BY postID";
        List<Post> postList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql));
        ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                int postID = resultSet.getInt();
                String posterUsername = resultSet.getString();
                String PostTime = resultSet.getString();
                String PostType = resultSet.getString();
                String PostCaption = resultSet.getString();
                String PostImage = resultSet.getString();
                String PostText = resultSet.getString();


                Post post = new Post(postID, posterUsername, PostTime, PostType, PostCaption, PostImage, PostText);
                postList.add(post);
            }
            return postList;
        }catch(SQLException exception) {
            throw new Exception(exception.getMessage());
        }
    }
    
    public static List<Comment> getPost_Comments(String username){
        String sql = "SELECT * FROM Comments ORDER BY commenterUsername";
        List<Comment> postComments = new ArrayList<>();
        Connection connection = DBUtil.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql));
        ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                int postID = resultSet.getInt();
                String commenterUsername = resultSet.getString();
                String commentTime = resultSet.getString();
                String CommentText = resultSet.getString();

                Comment comment = new Comment(postID, commenterUsername, commentTime, CommentText);
                postComments.add(comment);
            }
            return postComments;
        }catch(SQLException exception) {
            throw new Exception(exception.getMessage());
        }
    }
    
    //use switch statement to alternate results
    public static UserMentalHealthStatus getQuestionnaire(String depressionType){
        String  sql = "SELECT * FROM QuestionnaireQ WHERE DepressionTypeID = ?";
        Connection connection = DBUtil.getConnection();
        switch (depressionType) {
            case "General":
                try (PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            case "Seasonal Affective Disorder":
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            case "Persistent Depressive Disorder":
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            case "Premenstrual Dysphoric Disorder":
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            case "Situational Depression":
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            case "Atypical Depression":
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            case "Major Depressive Disorder":
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            case "Psychotic Depression":
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            case "Bipolar Disorder":
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;
            default:
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(sql)) {
                    ResultSet rs =preparedStatement.executeQuery();
                    if (rs.next()) {
                        int questionId = rs.getInt();
                        String questionText = rs.getString();

                        rs.close();

                        UserMentalHealthStatus mentalHealthStatus =
                                new UserMentalHealthStatus(questionId, questionText);
                        return mentalHealthStatus;
                    } else{
                        rs.close();
                        return null;
                    }
                } catch (SQLException exception) {
                    throw new Exception(exception.getMessage());
                }
                break;


        }
    }

    public static void getSocialMediaUserProfile(){}
}
