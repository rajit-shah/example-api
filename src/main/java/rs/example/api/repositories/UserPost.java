package rs.example.api.repositories;

import rs.example.api.model.Post;
import rs.example.api.model.User;

import java.util.List;

/**
 * Composite class composing {@link User} and {@link Post} entities. To be sent as a response object to the consumer
 * of User and the related posts
 */
public class UserPost {
    private List<Post> posts;

    private User user;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(final List<Post> post) {
        this.posts = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
