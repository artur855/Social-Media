package com.arthurzera.website.controllers.auth;

import java.net.URI;
import javax.security.auth.login.LoginException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.website.controllers.BasicController;
import com.arthurzera.website.forms.ChangePasswordEmailForm;
import com.arthurzera.website.models.User;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class FacebookConnectController extends BasicController{

	private Facebook facebook;
	
	private ConnectionRepository connectionRepository;
	
	@RequestMapping("/users/auth/facebook")
	public ModelAndView facebook() throws LoginException {
		ModelAndView mvc = super.mvc();
		if(connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			mvc.setViewName("redirect:/connect/facebook");
			return mvc;
		}
		org.springframework.social.facebook.api.User facebookProfile = facebook.userOperations().getUserProfile();
		User user = new User(facebookProfile.getFirstName(), facebookProfile.getName(), facebookProfile.getEmail());
		user.setProfilePictureUrl(getPictureUrl(facebookProfile.getId(), ImageType.NORMAL));
		mvc.setViewName("connect/facebookConnected");
		return mvc;
	}
	
	@RequestMapping(value="/users/auth/facebook", method=RequestMethod.POST)
	public ModelAndView facebook(ChangePasswordEmailForm changePasswordEmailForm) throws LoginException {
		ModelAndView mvc = super.mvc();
		
		mvc.setViewName("redirect:/");
		
		return mvc;
	}
	
	
	public String getPictureUrl(String userId, ImageType imageType) {
		URI uri = URIBuilder.fromUri(facebook.getBaseGraphApiUrl()+userId+"/picture?type="+imageType.toString().toLowerCase()+"&redirect=false").build();
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		FacebookTemplate template = new FacebookTemplate(connection.createData().getAccessToken());
		JsonNode response = template.getRestTemplate().getForObject(uri, JsonNode.class);
		return response.get("data").get("url").textValue();
	}
}
