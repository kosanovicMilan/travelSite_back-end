package travelHelper.entities;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.ls.LSOutput;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class Article {

    private Integer article_id;

    @NotEmpty(message = "Article title is required!")
    @NotNull(message = "Article title is required!")
    private String title;

    @NotEmpty(message = "Article text is required!")
    @NotNull(message = "Article text is required!")
    private String text;

    @NotEmpty(message = "Article date of creation is required!")
    @NotNull(message = "Article date of creation  is required!")
    private Date created_on;

    //Foreign keys
    private Integer activity_id;
    private Integer author_id;
    private Integer destination_id;

    @NotEmpty(message = "activityName is required!")
    @NotNull(message = "activityName  is required!")
    private String activityName;

    @NotEmpty(message = "authorName is required!")
    @NotNull(message = "authorName  is required!")
    private String authorName;

    @NotEmpty(message = "destinationName is required!")
    @NotNull(message = "destinationName  is required!")
    private String destinationName;

    public Article() {
    }

    public Article(Integer article_id, String title, String text, Date created_on, int activity_id, int author_id, int destination_id) {
        this.article_id = article_id;
        this.title = title;
        this.text = text;
        this.created_on = created_on;
        this.activity_id = activity_id;
        this.author_id = author_id;
        this.destination_id = destination_id;
    }

}
