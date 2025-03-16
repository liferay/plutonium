package ${package};

import jakarta.inject.Inject;

import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletConfig;

import jakarta.portlet.annotations.HeaderMethod;
import jakarta.portlet.annotations.InitParameter;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.RenderMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PortletConfiguration(
	portletName = "hello_world",
	title = @LocaleString(value = "${artifactId}"),
	initParams = @InitParameter(name = "foo", value = "1234"))
public class HelloWorldPortlet {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldPortlet.class);

	private static final String JSPX = "/WEB-INF/views/portletViewMode.jspx";

	@Inject HeaderRequest headerRequest;
	@Inject HeaderResponse headerResponse;
	@Inject PortletConfig portletConfig;

	@HeaderMethod(portletNames = {"hello_world"})
	public void renderHeaders() {

		String contextPath = headerRequest.getContextPath();
		String linkMarkup = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" +
			contextPath + "/resources/css/main.css\"></link>";
		headerResponse.addDependency("main.css", "${package}", null, linkMarkup);
		logger.debug("[HEADER_PHASE] Added resource: " + linkMarkup);
	}

	@RenderMethod(include = JSPX, portletNames = {"hello_world"})
	public void renderView() {
		String foo = portletConfig.getInitParameter("foo");
		logger.debug("[RENDER_PHASE] init-param foo: " + foo);
		logger.debug("[RENDER_PHASE] Rendering view: " + JSPX);
	}
}