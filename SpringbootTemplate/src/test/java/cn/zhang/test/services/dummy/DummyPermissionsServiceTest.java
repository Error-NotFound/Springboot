package cn.zhang.test.services.dummy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import cn.zhang.model.Permissions;
import cn.zhang.service.PermissionsService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes=cn.zhang.Application.class)
public class DummyPermissionsServiceTest
{
	private static final long USER_ID = 0;

	@Autowired
	@Qualifier("dummy-permission-service")
	private PermissionsService permissionService;

	@Test
	public void testLookupPermissionsSync()
	{
		Permissions permissions = permissionService.lookupPermissionsSync(USER_ID);
		verifyPermissions(permissions);
	}

	@Test
	public void testLookupPermissionsAsync() throws InterruptedException, ExecutionException
	{
		Future<Permissions> future = permissionService.lookupPermissionsAsync(USER_ID);
		Permissions permissions = future.get();
		verifyPermissions(permissions);
	}

	@Test
	public void testLookupPermissionsListenable() throws InterruptedException, ExecutionException
	{
		ListenableFuture<Permissions> listenable = permissionService.lookupPermissionsListenable(USER_ID);
		Permissions permissions = listenable.get();
		verifyPermissions(permissions);
	}

	@Test
	public void testLookupPermissionsCompletable() throws InterruptedException, ExecutionException
	{
		CompletableFuture<Permissions> completable = permissionService.lookupPermissionsCompletable(USER_ID);
		Permissions permissions = completable.get();
		verifyPermissions(permissions);
	}

	private void verifyPermissions(Permissions permissions)
	{
		assertNotNull(permissions);
		assertEquals(0, permissions.getId());
		assertTrue("permission1", permissions.hasPermission("permission1"));
		assertFalse("permission2", permissions.hasPermission("permission2"));
	}
}
