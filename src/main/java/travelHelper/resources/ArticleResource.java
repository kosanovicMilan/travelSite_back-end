package travelHelper.resources;

import travelHelper.entities.Article;
import travelHelper.services.ArticleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/articles")
public class ArticleResource {

    @Inject
    private ArticleService articleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> allArticles() {
        return this.articleService.allArticles();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article oneArticle(@PathParam("id") int id) {
        return this.articleService.getArticleById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Article addArticle(Article article) {
        return this.articleService.addArticle(article);
    }

    @DELETE
    @Path("/{id}")
    public void deletArticle(@PathParam("id") int id) {
        this.articleService.deleteArticle(id);
    }
}
