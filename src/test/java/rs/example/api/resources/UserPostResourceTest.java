package rs.example.api.resources;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rs.example.api.model.Post;
import rs.example.api.model.User;
import rs.example.api.repositories.PostRepository;
import rs.example.api.repositories.UserPost;
import rs.example.api.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class UserPostResourceTest {

    private MockMvc mockMvc;

    @Mock
    private PostRepository postRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.getController())
                .setControllerAdvice(new ResourceExceptionHandler()).build();
    }

    @Test
    public void testGetUserPost() throws Exception {
        CompletableFuture<List<Post>> postAsync = CompletableFuture.completedFuture(Collections.EMPTY_LIST);
        CompletableFuture<User> userAsync = CompletableFuture.completedFuture(new User());

        when(this.postRepositoryMock.getPostAsync(1)).thenReturn(postAsync);
        when(this.userRepositoryMock.getUserAsync(1)).thenReturn(userAsync);

        String uri = "/posts/user/1";
        MvcResult mvcResult = this.mockMvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        UserPost userPost = MapperUtil.mapFromJson(mvcResult.getResponse().getContentAsString(), UserPost.class);
        Assert.assertTrue(userPost.getPosts().isEmpty());
        verify(this.postRepositoryMock, times(1)).getPostAsync(1);
        verifyNoMoreInteractions(postRepositoryMock);
        verify(this.userRepositoryMock, times(1)).getUserAsync(1);
        verifyNoMoreInteractions(this.userRepositoryMock);
    }

    private UserPostResource getController() {
        return new UserPostResource(this.postRepositoryMock, this.userRepositoryMock);
    }
}
