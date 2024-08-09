package travelHelper.repos.article;

import travelHelper.entities.Article;
import travelHelper.entities.User;

import java.util.List;

public interface ArticleRepository {
    public Article getOneArticle(int article_id);
    public List<Article> getAllArticles();
    public void deleteAriticle(int article_id);
    public Article addArticle(Article article);
}
