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

import cn.zhang.model.Channel;
import cn.zhang.service.ChannelService;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes=cn.zhang.Application.class)
public class DummyChannelServiceTest
{
	private static final String CHANNEL_NAME = null;

	@Autowired
	@Qualifier("dummy-channel-service")
	private ChannelService channelService;

	@Test
	public void testLookupChannelSync()
	{
		Channel channel = channelService.lookupChannelSync(CHANNEL_NAME);
		verifyChannel(channel);
	}

	@Test
	public void testLookupChannelAsync() throws InterruptedException, ExecutionException
	{
		Future<Channel> future = channelService.lookupChannelAsync(CHANNEL_NAME);
		Channel channel = future.get();
		verifyChannel(channel);
	}

	@Test
	public void testLookupChannelListenable() throws InterruptedException, ExecutionException
	{
		ListenableFuture<Channel> listenable = channelService.lookupChannelListenable(CHANNEL_NAME);
		Channel channel = listenable.get();
		verifyChannel(channel);
	}

	@Test
	public void testLookupChannelCompletable() throws InterruptedException, ExecutionException
	{
		CompletableFuture<Channel> completable = channelService.lookupChannelCompletable(CHANNEL_NAME);
		Channel channel = completable.get();
		verifyChannel(channel);
	}

	private void verifyChannel(Channel channel)
	{
		assertNotNull(channel);
		assertEquals(0, channel.getId());
		assertEquals(CHANNEL_NAME, channel.getName());
	}
}
