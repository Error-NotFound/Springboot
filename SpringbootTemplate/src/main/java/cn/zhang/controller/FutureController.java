package cn.zhang.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.zhang.model.Channel;
import cn.zhang.model.Permissions;
import cn.zhang.model.User;
import cn.zhang.service.ChannelService;
import cn.zhang.service.PermissionsService;
import cn.zhang.service.UserService;


@RestController("/springfuture")
public class FutureController
{
	@Autowired
	private UserService userService;

	@Autowired
	private PermissionsService permissionService;

	@Autowired
	private ChannelService channelService;

	@RequestMapping(value = "/springfuture/user/{username}", method = RequestMethod.GET)
	public String getUser(@PathVariable("username") String userName) throws InterruptedException, ExecutionException
	{
		Future<User> fUser = userService.lookupUserAsync("test");
		User user = fUser.get();

		return user.getFirstName() + " " + user.getLastName();
	}

	@RequestMapping(value = "/springfuture/user/{username}/{permission}", method = RequestMethod.GET)
	public boolean getPermission(@PathVariable("username") String userName, @PathVariable("permission") String permission) throws InterruptedException, ExecutionException
	{
		Future<User> fUser = userService.lookupUserAsync("test");
		User user = fUser.get();

		Future<Permissions> fPermissions = permissionService.lookupPermissionsAsync(user.getId());
		Permissions userPermissions = fPermissions.get();

		return userPermissions.hasPermission(permission);
	}

	@RequestMapping(value = "/springfuture/watch/{username}/{permission}/{channel}", method = RequestMethod.GET)
	public boolean watchChannel(@PathVariable("username") String userName, @PathVariable("permission") String permission, @PathVariable("channel") String channel) throws InterruptedException, ExecutionException
	{
		Future<Channel> fChannel = channelService.lookupChannelAsync("ABC");

		Future<User> fUser = userService.lookupUserAsync("test");
		User user = fUser.get();

		Future<Permissions> fPermissions = permissionService.lookupPermissionsAsync(user.getId());
		Permissions userPermissions = fPermissions.get();

		Channel resultChannel = fChannel.get();

		return resultChannel != null && userPermissions.hasPermission(permission);
	}
}
