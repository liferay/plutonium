package ${package}.controller;

import ${package}.dto.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.mvc.security.CsrfProtected;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.PortletRequestScoped;

import jakarta.validation.Valid;

import jakarta.ws.rs.BeanParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@PortletRequestScoped
public class UserActionController {

	private static final Logger logger = LoggerFactory.getLogger(UserActionController.class);

	@Inject
	private BindingResult bindingResult;

	@Inject
	private Models models;

	@Inject
	@BeanParam
	@Valid
	private User user;

	@ActionMethod(portletName = "portlet1", actionName = "submitUser")
	@CsrfProtected
	public String submitForm(ActionRequest actionRequest, ActionResponse actionResponse) {

		models.put("user", user);

		Set<ParamError> bindingErrors = bindingResult.getAllErrors();

		if (bindingErrors.isEmpty()) {

			if (logger.isDebugEnabled()) {
				logger.debug("[ACTION_PHASE] firstName=" + user.getFirstName());
				logger.debug("[ACTION_PHASE] lastName=" + user.getLastName());
			}

			DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy G");

			Calendar todayCalendar = Calendar.getInstance();

			models.put("todaysDate", dateFormat.format(todayCalendar.getTime()));

			return "redirect:greeting.html";
		}

		return null;
	}
}