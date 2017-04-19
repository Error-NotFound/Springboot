package cn.zhang.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import cn.zhang.model.Channel;
import cn.zhang.model.Permissions;
import cn.zhang.model.Result;
import cn.zhang.service.ChannelService;
import cn.zhang.service.PermissionsService;
import cn.zhang.service.UserService;


@RestController("/springcompletablefuture")
public class CompletableFutureController
{
	@Autowired
	private UserService userService;

	@Autowired
	private PermissionsService permissionService;

	@Autowired
	private ChannelService channelService;

	@RequestMapping(value = "/springcompletablefuture/user/{username}", method = RequestMethod.GET)
	public DeferredResult<String> getUser(@PathVariable("username") String userName)
	{
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		userService.lookupUserCompletable(userName).thenAccept(user -> deferredResult.setResult(user.getFirstName() + " " + user.getLastName()));
		return deferredResult;
	}

	@RequestMapping(value = "/springcompletablefuture/user/{username}/{permission}", method = RequestMethod.GET)
	public DeferredResult<Boolean> getPermission(@PathVariable("username") String userName, @PathVariable("permission") String permission)
	{
		final DeferredResult<Boolean> deferredResult = new DeferredResult<>();

		userService.lookupUserCompletable(userName)
				.thenCompose(user -> permissionService.lookupPermissionsCompletable(user.getId()))
				.thenAccept(p -> deferredResult.setResult(p.hasPermission(permission)));

		return deferredResult;
	}

	@RequestMapping(value = "/springcompletablefuture/watch/{username}/{permission}/{channel}", method = RequestMethod.GET)
	public DeferredResult<Boolean> watchChannel(@PathVariable("username") String userName, @PathVariable("permission") String permission, @PathVariable("channel") String channel)
	{
		final DeferredResult<Boolean> deferredResult = new DeferredResult<>();

		CompletableFuture<Permissions> cPermission = userService.lookupUserCompletable(userName).thenCompose(user -> permissionService.lookupPermissionsCompletable(user.getId()));
		CompletableFuture<Channel> cChannel = channelService.lookupChannelCompletable(channel);
		CompletableFuture<Result> cResult = cPermission.thenCombine(cChannel, (p, c) -> new Result(c, p));
		cResult.thenAccept(result -> deferredResult.setResult(result.getChannel() != null && result.getPermissions().hasPermission(permission)));

		return deferredResult;
	}
}
