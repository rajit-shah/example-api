package rs.example.api.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import rs.example.api.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository class to fetch post data from supplied uri asynchronously
 */
@Component
public class PostRepository {
    private static final Logger logger = Logger.getLogger(PostRepository.class);

    @Value("${api.repository.postUri}")
    private String postUri;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    public CompletableFuture<List<Post>> getPostAsync(final int id) {
        CompletableFuture<List<Post>> result = new CompletableFuture<>();
        ListenableFuture<ResponseEntity<List<Post>>> post = this.asyncRestTemplate.exchange(
                postUri + "?userId=" + id, HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
                });

        post.addCallback(new ListenableFutureCallback<ResponseEntity<List<Post>>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error(ex);
                logger.info("Failure! - " + Thread.currentThread().getName());
                result.complete(Collections.EMPTY_LIST);
            }

            @Override
            public void onSuccess(ResponseEntity<List<Post>> entity) {
                logger.info("Success! - " + Thread.currentThread().getName());
                result.complete(entity.getBody());
            }
        });
        return result;
    }
}
