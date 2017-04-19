package cn.zhang.test.async;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.zhang.model.Channel;
import cn.zhang.model.Permissions;
import cn.zhang.model.User;
import cn.zhang.service.ChannelService;
import cn.zhang.service.PermissionsService;
import cn.zhang.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringApplicationConfiguration(classes=cn.zhang.Application.class)
public class FutureAsyncTest
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
		Future<Channel> fChannel = channelService.lookupChannelAsync("ABC");

		Future<User> fUser = userService.lookupUserAsync("test");
		User user = fUser.get();

		Future<Permissions> fPermissions = permissionService.lookupPermissionsAsync(user.getId());
		Permissions userPermissions = fPermissions.get();

		Channel channel = fChannel.get();

		assertNotNull(channel);
		assertTrue(userPermissions.hasPermission("permission1"));
		assertNotNull(user);
	}
}
