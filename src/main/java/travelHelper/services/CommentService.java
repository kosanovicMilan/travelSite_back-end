package travelHelper.services;

import travelHelper.entities.Comment;
import travelHelper.repos.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;


public class CommentService {

    public CommentService() {
        System.out.println(this);
    }

    @Inject
    private CommentRepository commentRepository;

    public List<Comment> allComments() {
        System.out.println("usao u service");
        return this.commentRepository.getAllComments();
    }

    public Comment oneComment(int id) {
        return this.commentRepository.getOneComment(id);
    }

    public Comment addComment(Comment comment) {
        return this.commentRepository.addComment(comment);
    }

    public void deleteComment(int id) {
        this.commentRepository.deleteComment(id);
    }
}
