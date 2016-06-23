package com.netease.common;

import org.springframework.test.context.ContextConfiguration;

import com.netease.edu.dao.JunitTransactionSpringContextTest;

/**
 * "classpath:/applicationContext-component-scan.xml", "classpath:/applicationContext-dao-base.xml",
 * "classpath:/applicationContext-service.xml", "classpath:/applicationContext-aop-base.xml",
 * "classpath:/applicationContext-test-common.xml", "classpath:/applicationContext-common.xml",
 * "classpath:/applicationContext-jms.xml", "classpath:/applicationContext-config.xml",
 * "classpath:/applicationContext-dbconfig.xml", "classpath:/biz/applicationContext-framework-aop.xml",
 * "classpath:/applicationContext-timerTask.xml", "classpath:/applicationContext-remoteService.xml",
 * "classpath:/applicationContext-outer-jms.xml", "classpath:/applicationContext-jms.xml",
 * "classpath:/conf/edu901-servlet.xml""classpath:/applicationContext-dao-base.xml"
 * 
 * @author Administrator
 */
@ContextConfiguration(locations = { "classpath:/applicationContext-framework-dao-base.xml" })
public class BaseTestCase extends JunitTransactionSpringContextTest {
    /*
     * @Autowired private QcMemberService qcMemberService;
     * @Autowired EduWebConfig config; public void setWebUser(Long userId) { QcMember member =
     * qcMemberService.getById(userId); WebUser webUser = new WebUser(); webUser.setState(WebUser.STATE_LOGON);
     * webUser.setId(userId); webUser.setNickName(member.getNickName()); webUser.setRealName(member.getRealName());
     * webUser.setPersonalUrlPrefix(config.getPersonalUrlPrefix()); webUser.setSmallFaceUrl(member.getSmallFaceUrl());
     * webUser.setLargeFaceUrl(member.getLargeFaceUrl()); WebUser.setWebUser(webUser);
     * SecurityContextHolder.getContext().setAuthentication(new AuthenticationAdapter(webUser)); }
     */
}
