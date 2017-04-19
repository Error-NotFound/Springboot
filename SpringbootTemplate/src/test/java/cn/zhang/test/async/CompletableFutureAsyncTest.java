package cn.zhang.test.async;

import static org.junit.Assert.*;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.zhang.model.Channel;
import cn.zhang.model.Permissions;
import cn.zhang.model.Result;
import cn.zhang.model.User;
import cn.zhang.service.ChannelService;
import cn.zhang.service.PermissionsService;
import cn.zhang.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringApplicationConfiguration(classes=cn.zhang.Application.class)
public class CompletableFutureAsyncTest
{
	@Autowired
	private UserService userService;

	@Autowired
	private PermissionsService permissionService;

	@Autowired
	private ChannelService channelService;

	@Test(timeout = 1500)
	public void testHasChannel() throws Exception
	{
		CompletableFuture<Channel> cChannel = channelService.lookupChannelCompletable("ABC");
		CompletableFuture<User> cUser = userService.lookupUserCompletable("test");
		CompletableFuture<Permissions> cPermissions = cUser.thenCompose(u -> permissionService.lookupPermissionsCompletable(u.getId()));
		CompletableFuture<Result> cResult = cPermissions.thenCombine(cChannel, (permissions, channel) -> new Result(channel, permissions));

		Result result = cResult.get();
		assertNotNull(result);
		assertNotNull(result.getChannel());
		assertNotNull(result.getPermissions());
		assertTrue(result.getPermissions().hasPermission("permission1"));
	}
}
