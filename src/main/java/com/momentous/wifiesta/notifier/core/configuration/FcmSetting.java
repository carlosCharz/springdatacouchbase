package com.momentous.wifiesta.notifier.core.configuration;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLSocketFactory;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * FCM settings loaded from a property file
 * 
 * @author Charz++
 */

@Component
@ConfigurationProperties(prefix = "fcm")
public class FcmSetting {

	protected static final Logger logger = LoggerFactory.getLogger(FcmSetting.class);

	private Long senderId;

	private String serverKey;

	private String serverUrl;

	private Integer serverPort;

	private Boolean debuggable;

	private Integer timeToLive;

	private Integer pingInterval;

	private String priority;

	private String badge;

	private String clickAction;

	private String sound;

	public FcmSetting() {
		logger.info("Loading FCM properties");
	}

	@PostConstruct
	public void postConstruct() {
		logger.info(
				"FCM properties -> senderId: '{}', serverKey: '{}', serverUrl: '{}', serverPort: '{}', debuggable: '{}', timeToLive: '{}', pingInterval: '{}', priority: '{}', badge: '{}', clickAction: '{}', sound: '{}'",
				senderId, serverKey, serverUrl, serverPort, debuggable, timeToLive, pingInterval, priority, badge,
				clickAction, sound);
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getServerKey() {
		return serverKey;
	}

	public void setServerKey(String serverKey) {
		this.serverKey = serverKey;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public Boolean getDebuggable() {
		return debuggable;
	}

	public void setDebuggable(Boolean debuggable) {
		this.debuggable = debuggable;
	}

	public Integer getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}

	public Integer getPingInterval() {
		return pingInterval;
	}

	public void setPingInterval(Integer pingInterval) {
		this.pingInterval = pingInterval;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getClickAction() {
		return clickAction;
	}

	public void setClickAction(String clickAction) {
		this.clickAction = clickAction;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	@Bean
	private XMPPTCPConnectionConfiguration createConnectionConfiguration() throws XmppStringprepException {
		XMPPTCPConnection.setUseStreamManagementResumptionDefault(true);
		XMPPTCPConnection.setUseStreamManagementDefault(true);

		XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
		logger.info("Connecting to the server '{}:{}'", serverUrl, serverPort);
		config.setXmppDomain("Wifiesta FCM XMPP Client Connection Server");
		config.setHost(serverUrl);
		config.setPort(serverPort);
		config.setSendPresence(false);
		config.setSocketFactory(SSLSocketFactory.getDefault());
		config.setSecurityMode(SecurityMode.ifpossible);
		config.setDebuggerEnabled(debuggable);
		return config.build();
	}

}
