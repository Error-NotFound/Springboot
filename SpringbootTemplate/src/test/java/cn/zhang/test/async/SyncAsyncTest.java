package cn.zhang.test.async;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.zhang.controller.SyncController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringApplicationConfiguration(classes=cn.zhang.Application.class)
public class SyncAsyncTest
{
	@Autowired
	private SyncController controller;

	@Test(timeout = 2000)
	public void testHasChannel()
	{
		boolean result = controller.watchChannel("tester", "permission1", "ABC");
		assertNotNull(result);
		assertTrue(result);
	}
}
