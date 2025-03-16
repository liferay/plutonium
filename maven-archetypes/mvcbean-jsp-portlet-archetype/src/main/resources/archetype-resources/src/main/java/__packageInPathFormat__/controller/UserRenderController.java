package ${package}.controller;

import ${package}.dto.User;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.engine.ViewEngineContext;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionURL;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.MutableActionParameters;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.annotations.HeaderMethod;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.RenderMethod;

import jakarta.validation.executable.ExecutableType;
import jakarta.validation.executable.ValidateOnExecution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Controller
@PortletConfiguration(
	portletName = "portlet1", resourceBundle = "content.portlet1"
)
public class UserRenderController {

	private static final Logger logger = LoggerFactory.getLogger(UserRenderController.class);

	@Inject
	private Models models;

	@Inject
	private ViewEngineContext viewEngineContext;

	@HeaderMethod(portletNames = {"portlet1"})
	public void renderHeaders(HeaderRequest headerRequest, HeaderResponse headerResponse) {

		String contextPath = headerRequest.getContextPath();
		String linkMarkup = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" +
			contextPath + "/resources/css/main.css\"></link>";
		headerResponse.addDependency("main.css", "${package}", null, linkMarkup);
		logger.debug("[HEADER_PHASE] Added resource: " + linkMarkup);
	}

	@RenderMethod(portletNames = {"portlet1"})
	@ValidateOnExecution(type = ExecutableType.NONE)
	public String prepareView(RenderRequest renderRequest, RenderResponse renderResponse) {

		String viewName = viewEngineContext.getView();

		if (viewName == null) {

			viewName = "user.jspx";

			User user = (User) models.get("user");

			if (user == null) {
				user = new User();
				models.put("user", user);
			}

			ActionURL actionURL = renderResponse.createActionURL();
			MutableActionParameters actionParameters = actionURL.getActionParameters();
			actionParameters.setValue(ActionRequest.ACTION_NAME, "submitUser");

			models.put("submitUserActionURL", actionURL.toString());
		}

		logger.debug("[RENDER_PHASE] prepared model for viewName: {}", viewName);

		return viewName;
	}
}