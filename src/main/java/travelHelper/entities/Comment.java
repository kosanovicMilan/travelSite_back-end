package travelHelper.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class Comment {

    private int comment_id;

    @NotNull(message = "Comment text is required!")
    @NotEmpty(message = "Comment text is required!")
    private String text;

    @NotNull(message = "Comment writer is required!")
    @NotEmpty(message = "Comment writer is required!")
    private String writer;

    private int article_id;

    @NotNull(message = "Comment date of creation is required!")
    @NotEmpty(message = "Comment date of creation is required!")
    private Date created_on;

    public Comment() {
    }

    public Comment(int comment_id, String text, String writer,Date created_on,int article_id) {
        this.comment_id = comment_id;
        this.text = text;
        this.writer = writer;
        this.created_on = created_on;
        this.article_id = article_id;
    }
}
