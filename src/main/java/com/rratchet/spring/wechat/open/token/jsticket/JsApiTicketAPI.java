package com.rratchet.spring.wechat.open.token.jsticket;

import org.springframework.web.client.RestOperations;

import com.rratchet.spring.wechat.open.WechatAPI;
import com.rratchet.spring.wechat.open.token.Token;
import com.rratchet.spring.wechat.open.token.TokenAPI;
import com.rratchet.spring.wechat.open.token.accesstoken.AccessTokenManager;

public class JsApiTicketAPI extends WechatAPI implements TokenAPI {

	public final static String JS_API_TICKET_API_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={ACCESS_TOKEN}&type=jsapi";
	
	private AccessTokenManager accessTokenManager;
	
	private RestOperations restOperations;

	public Token acquireToken() {
		String token = accessTokenManager.token();
		JsApiTicketAPIResponse response = restOperations.getForObject(JS_API_TICKET_API_TEMPLATE, JsApiTicketAPIResponse.class, token);
		apiResponseAssert.assertOK(response);
		Token ticket = new Token(response.getTicket(), response.getExpires_in());
		return ticket;
	}

	public void setAccessTokenManager(AccessTokenManager accessTokenManager) {
		this.accessTokenManager = accessTokenManager;
	}

	public void setRestOperations(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

}
