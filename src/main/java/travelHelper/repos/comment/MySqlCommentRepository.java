package travelHelper.repos.comment;

import travelHelper.entities.Comment;
import travelHelper.repos.SQL_AbstractRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlCommentRepository extends SQL_AbstractRepo implements CommentRepository {
    @Override
    public Comment getOneComment(int comment_id) {

        Comment comment = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE comment_id=?");
            preparedStatement.setInt(1, comment_id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("comment_id");
                String text = resultSet.getString("text");
                String writer = resultSet.getString("writer");
                Date date = resultSet.getDate("created_on");
                int articleId = resultSet.getInt("article_id");

                comment = new Comment(id, text, writer, date, articleId);

            }



        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return comment;
    }

    @Override
    public List<Comment> getAllComments() {

        List<Comment> comments = new ArrayList<Comment>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from comment");

            while(resultSet.next()){
                comments.add(new Comment(
                        resultSet.getInt("comment_id"),
                        resultSet.getString("text"),
                        resultSet.getString("writer"),
                        resultSet.getDate("created_on"),
                        resultSet.getInt("article_id")
                ));
            }




        }catch (Exception e){
            System.out.println("Puko u mysqlrepo za All()");
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
        }

        return comments;
    }

    @Override
    public void deleteComment(int comment_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("delete from Comment where comment_id = ?");
            preparedStatement.setInt(1,comment_id);
            preparedStatement.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }
    }

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            String[] generatedColumn = {"comment_id"};
            preparedStatement = connection.prepareStatement("INSERT into Comment(text,writer,created_on,article_id) values(?,?,?,?)",generatedColumn);
            preparedStatement.setString(1,comment.getText());
            preparedStatement.setString(2,comment.getWriter());
            preparedStatement.setDate(3, (java.sql.Date) comment.getCreated_on());
            preparedStatement.setInt(4, comment.getArticle_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                comment.setComment_id(resultSet.getInt(1));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return comment;
    }
}
