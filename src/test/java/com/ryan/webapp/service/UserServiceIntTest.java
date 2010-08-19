package com.ryan.webapp.service;

import static com.ryan.webapp.model.factory.UserFactory.createUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.ryan.webapp.dao.UserDao;
import com.ryan.webapp.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-placeholders.xml" , 
						"classpath:applicationContext-services.xml",
						"classpath:applicationContext-mocks.xml"})
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class})
public class UserServiceIntTest {
	private static final long ONE_SECOND = 1000l;
	private final static int SIZE = 10; 

	@Autowired
	private Mockery mockery;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	private ExecutorService executor;
	
	@Before
	public void setUp() {
        executor = Executors.newFixedThreadPool(SIZE);
	}
	
	@After
	public void tearDown() {
		mockery.assertIsSatisfied();
	}
	
	@Timed(millis=5000)
	@Test
	public void multipleCallsToAddUserCallsExpectedMethods() throws InterruptedException, ExecutionException, TimeoutException {
		Collection<Future<Integer>> tasks = getFutureCallsToUserService();
		runTasks(tasks);
		executor.awaitTermination(2, TimeUnit.SECONDS);
	}

	private Collection<Future<Integer>> getFutureCallsToUserService() {
		Collection<Future<Integer>> tasks = new ArrayList<Future<Integer>>(SIZE);
		
		for (int i=0; i< SIZE; i++) {
			final User user = createUser("Name", String.valueOf(i));
			addUserDaoExpectation(user);
			tasks.add(submitAddUserForTheFuture(i, user));
		}
		return tasks;
	}

	private void addUserDaoExpectation(final User user) {
		mockery.checking(new Expectations() {{
			one(userDao).addUser(user);
		}});
	}

	private void runTasks(Collection<Future<Integer>> tasks)
			throws InterruptedException, ExecutionException, TimeoutException {
		for(Future<Integer> task: tasks) {
			task.get(2, TimeUnit.SECONDS);
		}
	}
	
	private Future<Integer> submitAddUserForTheFuture(final Integer i, final User user) {
        Callable<Integer> serviceMethod = new Callable<Integer>() {
            public Integer call() throws Exception {
            	userService.addUser(user);
				Thread.sleep(ONE_SECOND);
            	return i;
            }
        };

        return executor.submit(serviceMethod);
    }

	
}
