package travelHelper.services;

import travelHelper.entities.Article;
import travelHelper.repos.article.ArticleRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    public ArticleService() {
        System.out.println(this);
    }

    @Inject
    private ArticleRepository articleRepository;

    public List<Article> allArticles() {
        return this.articleRepository.getAllArticles();
    }

    public Article getArticleById(int id) {
        return this.articleRepository.getOneArticle(id);
    }

    public Article addArticle(Article article) {
        return this.articleRepository.addArticle(article);
    }

    public void deleteArticle(int id) {
        this.articleRepository.deleteAriticle(id);
    }
}
