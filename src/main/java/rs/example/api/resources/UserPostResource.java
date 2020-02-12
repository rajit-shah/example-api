package rs.example.api.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.example.api.model.Post;
import rs.example.api.model.User;
import rs.example.api.repositories.PostRepository;
import rs.example.api.repositories.UserPost;
import rs.example.api.repositories.UserRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Rest controller class to fetch user and the comment posts. The Controller relies on respective repository classes to
 * asynchronously fetch records from 3rd party api endpoint. The repositories return an instance of {@link CompletableFuture}
 * whose get() method blocks until the Future is complete(i.e the api call either succeeds or fails)
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/posts")
@Api(tags = {"posts"}, description = "get user posts")
public class UserPostResource {
    private static final Logger logger = Logger.getLogger(UserPostResource.class);

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserPostResource(final PostRepository postRepository, final UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "get all posts by user id", response = UserPost.class)
    public UserPost getUserPost(@PathVariable("id") int id) {

        CompletableFuture<User> userAsync = this.userRepository.getUserAsync(id);
        CompletableFuture<List<Post>> postAsync = this.postRepository.getPostAsync(id);
        try {
            UserPost userPost = new UserPost();
            //get user and the post asynchronously
            userPost.setUser(userAsync.get());
            userPost.setPosts(postAsync.get());
            return userPost;
        } catch (Exception e) {
            logger.error("Error during fetch", e);
            throw new ResourceException("Error during fetch");
        }
    }
}
