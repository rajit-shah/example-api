package rs.example.api.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import rs.example.api.model.User;

import java.util.concurrent.CompletableFuture;

/**
 * Repository class to fetch user data from supplied uri asynchronously
 */

@Component
public class UserRepository {
    private static final Logger logger = Logger.getLogger(UserRepository.class);

    @Value("${api.repository.userUri}")
    private String userUri;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    public CompletableFuture<User> getUserAsync(final int id) {
        CompletableFuture<User> result = new CompletableFuture<>();
        ListenableFuture<ResponseEntity<User>> user = this.asyncRestTemplate.getForEntity(
                userUri + "/" + id, User.class);

        user.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
            @Override
            public void onFailure(final Throwable ex) {
                logger.error(ex);
                logger.info("Failure! - " + Thread.currentThread().getName());
                result.complete(new User());
            }

            @Override
            public void onSuccess(final ResponseEntity<User> entity) {
                logger.info("Success! - " + Thread.currentThread().getName());
                result.complete(entity.getBody());
            }
        });
        return result;
    }
}
