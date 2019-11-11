package eu.transkribus.client.connection;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.transkribus.client.connection.ATrpServerConn.TrpServer;
import eu.transkribus.client.util.TrpClientErrorException;
import eu.transkribus.client.util.TrpServerErrorException;
import eu.transkribus.core.io.util.TrpProperties;
import eu.transkribus.core.model.beans.job.enums.JobImpl;

public class AdminCallsTest {
	private static final Logger logger = LoggerFactory.getLogger(AdminCallsTest.class);
	
	
	@Test
	public void testGetUserNamesForJobImpl() throws LoginException {
//		String jobImpl = JobImpl.P2PaLATrainJob.toString();
		String jobImpl = JobImpl.CITlabHtrPlusTrainingJob.toString();
//		String jobImpl = JobImpl.CITlabAdvancedLaJob.toString();
		
		logger.info(new Object(){}.getClass().getEnclosingMethod().getName());
		
//		TrpProperties creds = new TrpProperties("adminCreds.properties");
		TrpProperties creds = new TrpProperties("testCreds.properties");
		try (TrpServerConn c = new TrpServerConn(TrpServer.Test, creds.getString("username"), creds.getString("password"))) {
			List<String> usernames = c.getAdminCalls().getUserNamesForJobImpl(jobImpl);
			
			logger.info("responseStr = "+usernames);
		} 
//		catch (TrpClientErrorException e) {
//			logger.error("client error: "+e.getMessageToUser());
//			logger.error("msg = "+e.getMessage());
//		}
//		catch (TrpServerErrorException e) {
//			logger.error("server error: "+e.getMessageToUser());
//			logger.error("msg = "+e.getMessage());
//		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	
	@Test
	public void testAllowUsersForJob() throws LoginException {
		String jobImpl = JobImpl.P2PaLATrainJob.toString();
		List<String> userList = new ArrayList<>();
//		userList.add("whoever@wherever.com");
		userList.add("tobias.hodel@uzh.ch");
		
//		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
//		logger.info("testAllowUsersForJob");
		logger.info(new Object(){}.getClass().getEnclosingMethod().getName());
		
		TrpProperties creds = new TrpProperties("adminCreds.properties");
		try (TrpServerConn c = new TrpServerConn(TrpServer.Test, creds.getString("username"), creds.getString("password"))) {
			String responseStr = c.getAdminCalls().allowUsersForJob(userList, jobImpl);
			
			logger.info("responseStr = "+responseStr);
		} 
		catch (TrpClientErrorException e) {
			logger.error("client error: "+e.getMessageToUser());
			logger.error("msg = "+e.getMessage());
		}
		catch (TrpServerErrorException e) {
			logger.error("server error: "+e.getMessageToUser());
			logger.error("msg = "+e.getMessage());
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
