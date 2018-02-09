package com.arthurzera.website.controllers.user.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.website.controllers.BasicController;
import com.arthurzera.website.forms.ChangeInformationForm;
import com.arthurzera.website.models.User;

@Controller
public class UserConfigInformation extends BasicController {
	private static final String IMG_UPLOAD_FOLDER = "C://Users//Secret//eclipse-workspace//WebsiteArthurzera//src//main//resources//public//img//";
	private static final String PARTIAL_UPLOAD_FOLDER = "/img/user_img/";

	@RequestMapping("/users/{username}/config/change-information")
	public ModelAndView changeInformation(@PathVariable("username") String username) {
		ModelAndView mvc = super.mvc();
		User user = userService.findByUsername(username);
		User currentUser = (User) mvc.getModel().get("currentUser");
		if (!user.equals(currentUser)) {
			notifyService.addDangerMessage("You can't modify another user information");
			mvc.setViewName("redirect:/users" + currentUser.getUsername() + "/config");
			return mvc;
		}
		mvc.addObject("changeInformationForm", new ChangeInformationForm(user.getAboutMe(), user.getCellphoneNumber()));
		mvc.setViewName("users/change-information");
		return mvc;
	}

	@RequestMapping(value = "/users/{username}/config/change-information", method = RequestMethod.POST)
	public ModelAndView changeInformation(@PathVariable("username") String username,
			ChangeInformationForm changeInformationForm, BindingResult bindingResult) throws IOException {
		ModelAndView mvc = super.mvc();
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldError().toString());
			notifyService.addDangerMessage("Fill the form correctly!");
			mvc.setViewName("redirect:/users/" + username + "/config/change-information");
			return mvc;
		}
		User user = userService.findByUsername(username);
		user.setAboutMe(changeInformationForm.getAboutMe());
		user.setCellphoneNumber(changeInformationForm.getCellphoneNumber());
		if (!changeInformationForm.getProfilePicture().isEmpty()) {
			try {
				ImageIO.read(changeInformationForm.getProfilePicture().getInputStream()).toString();
				try {
					if (!user.getProfilePictureUrl().equals(User.DEFAULT_PROFILE_PICTURE)) {
						Path oldPicture = Paths.get(IMG_UPLOAD_FOLDER + user.getProfilePictureUrl().substring(5));
						try {
							Files.delete(oldPicture);
						} catch (NoSuchFileException ex) {
							ex.printStackTrace();
						}
					}
					byte[] bytes = changeInformationForm.getProfilePicture().getBytes();
					Path parentDir = Paths
							.get(IMG_UPLOAD_FOLDER + "//user_img//" + user.getId() + '_' + user.getUsername());
					Path path = Paths.get(parentDir.toString() + "//profile_picture_"
							+ changeInformationForm.getProfilePicture().getOriginalFilename());
					user.setProfilePictureUrl(PARTIAL_UPLOAD_FOLDER + user.getId() + '_' + user.getUsername() + "/"
							+ changeInformationForm.getProfilePicture().getOriginalFilename());
					if (!Files.exists(parentDir)) {
						Files.createDirectories(parentDir);
					}
					Files.write(path, bytes);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				notifyService.addWarningMessage("You can only upload images");
				mvc.setViewName("redirect:/users/" + username + "/config/change-information");
				return mvc;
			}
		}
		userService.edit(user);
		mvc.setViewName("redirect:/users/" + username + "/config");
		return mvc;
	}

}
