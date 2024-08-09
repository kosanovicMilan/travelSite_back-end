package travelHelper.repos.comment;

import travelHelper.entities.Comment;

import java.util.List;

public interface CommentRepository {

    public Comment getOneComment(int comment_id);
    public List<Comment> getAllComments();
    public void deleteComment(int comment_id);
    public Comment addComment(Comment comment);
}
