package travelHelper.resources;

import travelHelper.entities.Comment;
import travelHelper.services.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> allComments() {
        System.out.println("usao u resors!");
        return this.commentService.allComments();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment oneComment(@PathParam("id") int id) {
        return this.commentService.oneComment(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment addComment(Comment Comment) {
        return this.commentService.addComment(Comment);
    }

    @DELETE
    @Path("/{id}")
    public void deletComment(@PathParam("id") int id) {
        this.commentService.deleteComment(id);
    }
}
