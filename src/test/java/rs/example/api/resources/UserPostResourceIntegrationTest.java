package rs.example.api.resources;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rs.example.api.entry.AsyncApiStarter;
import rs.example.api.repositories.UserPost;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AsyncApiStarter.class)
@WebAppConfiguration
public class UserPostResourceIntegrationTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetUserPostStatusOk() throws Exception {
        String uri = "/posts/user/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testGetUserPostEntityReturned() throws Exception {
        String uri = "/posts/user/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        final UserPost userPost = MapperUtil.mapFromJson(mvcResult.getResponse().getContentAsString(), UserPost.class);
        Assert.assertTrue("posts should not be empty!", !userPost.getPosts().isEmpty());
        Assert.assertNotNull("User should not be null!", userPost.getUser());
    }

    @Test
    public void testGetUserPostEmptyEntityReturned() throws Exception {
        String uri = "/posts/user/2000";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        final UserPost userPost = MapperUtil.mapFromJson(mvcResult.getResponse().getContentAsString(), UserPost.class);
        Assert.assertTrue("posts should be empty!", userPost.getPosts().isEmpty());
        Assert.assertNull("User should be null!", userPost.getUser().getName());
    }

    @Test
    public void testGetUserPostStatusNotFound() throws Exception {
        String uri = "/undefined/undefined/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }

}
