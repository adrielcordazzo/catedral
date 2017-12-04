package br.com.xlabi.controller.geral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.xlabi.service.geral.UsuarioService;

@Controller
public class AppController extends AbstractController {

	@Autowired
	UsuarioService userService;

	// @Autowired
	// PersistentTokenBasedRememberMeServices
	// persistentTokenBasedRememberMeServices;

	/**
	 * This method will list all existing users.
	 */

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index() {

		if (isCurrentAuthenticationAnonymous()) {
			return "entrar";
		} else {
			return "/index";
		}
	}

	@RequestMapping(value = { "/getLogado" }, method = RequestMethod.GET)
	@ResponseBody
	public String getLogado(ModelMap model) {
		return getMessage("non.unique.ssoId", null);
		// return getPrincipal();
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/entrar", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "entrar";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response,
			// auth);
			// persistentTokenBasedRememberMeServices.logout(request, response,
			// auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/entrar?logout";
	}

}