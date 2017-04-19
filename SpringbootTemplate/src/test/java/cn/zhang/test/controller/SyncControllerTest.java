package cn.zhang.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringApplicationConfiguration(classes=cn.zhang.Application.class)
public class SyncControllerTest extends BaseControllerTest
{
	@Test
	public void testGetUser() throws Exception
	{
		super.testGetUser();
	}

	@Test
	public void testHasPermissionSuccess() throws Exception
	{
		super.testHasPermission(SUCCESS_PERMISSION, "true");
	}

	@Test
	public void testHasPermissionFailure() throws Exception
	{
		super.testHasPermission(FAIL_PERMISSION, "false");
	}

	@Test
	public void testChannelAvailable() throws Exception
	{
		super.testChannelAvailable();
	}

	@Override
	protected String getBasePath()
	{
		return "/springsync";
	}
}
