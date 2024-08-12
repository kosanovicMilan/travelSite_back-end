package travelHelper.repos.article;

import travelHelper.entities.Article;
import travelHelper.repos.SQL_AbstractRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlArticleRepository extends SQL_AbstractRepo implements ArticleRepository {
    @Override
    public Article getOneArticle(int article_id) {
        Article article = null;


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from Article Ar where Ar.article_id = ?");
            preparedStatement.setInt(1, article_id);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getResultSet();

           if(resultSet.next()){
               int articleId = resultSet.getInt(1);
               String title = resultSet.getString(2);
               String text = resultSet.getString(3);
               Date created_On = resultSet.getDate(4);
               int actitivyId = resultSet.getInt(5);
               int authorId = resultSet.getInt(6);
               int destinationId = resultSet.getInt(7);

               article = new Article(article_id,title,text,created_On,actitivyId,authorId,destinationId);
           }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return article;
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<Article>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Article");

            if(resultSet.next()){
                articles.add(new Article(
                        resultSet.getInt("article_id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("created_on"),
                        resultSet.getInt("activity_id"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("destination_id")
                        ));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
        }

        return articles;
    }

    @Override
    public void deleteAriticle(int article_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("delete from Article where article_id = ?");
            preparedStatement.setInt(1,article_id);
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
    public Article addArticle(Article article) {
        List<Article> articles = new ArrayList<Article>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            String[] generatedColumn = {"article_id"};
            preparedStatement = connection.prepareStatement("Insert Into Article(title,text,created_on,activity_id,author_id,destination_id) values(? ,? , ?, ?, ?, ?)",generatedColumn);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getText());
            preparedStatement.setDate(3, (java.sql.Date) article.getCreated_on());
            preparedStatement.setInt(4, article.getActivity_id());
            preparedStatement.setInt(5, article.getAuthor_id());
            preparedStatement.setInt(6, article.getDestination_id());
            preparedStatement.executeUpdate();

            if(resultSet.next()){
               article.setArticle_id(resultSet.getInt(1));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return article;
    }
}
