package cn.zhang.test.services.dummy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.util.concurrent.ListenableFuture;

import cn.zhang.model.User;
import cn.zhang.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration
@SpringApplicationConfiguration(classes=cn.zhang.Application.class)
public class DummyUserServiceTest
{
	private static final String USERNAME = "test";

	@Autowired
	@Qualifier("dummy-user-service")
	private UserService userService;

	@Test
	public void testLookupUserSync()
	{
		User user = userService.lookupUserSync(USERNAME);
		verifyUser(user);
	}

	@Test
	public void testLookupUserAsync() throws InterruptedException, ExecutionException
	{
		Future<User> future = userService.lookupUserAsync(USERNAME);
		User user = future.get();
		verifyUser(user);
	}

	@Test
	public void testLookupUserListenable() throws InterruptedException, ExecutionException
	{
		ListenableFuture<User> listenableFuture = userService.lookupUserListenable(USERNAME);
		User user = listenableFuture.get();
		verifyUser(user);
	}

	@Test
	public void testLookupUserCompletable() throws InterruptedException, ExecutionException
	{
		CompletableFuture<User> completableFuture = userService.lookupUserCompletable(USERNAME);
		User user = completableFuture.get();
		verifyUser(user);
	}

	private void verifyUser(User user)
	{
		assertNotNull(user);
		assertEquals(0, user.getId());
		assertEquals("tester", user.getFirstName());
		assertEquals("test", user.getLastName());
		assertEquals(USERNAME, user.getUserName());
	}
}
