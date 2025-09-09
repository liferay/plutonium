<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>
<%@ page import="jakarta.portlet.*"%>
<%@ page import="java.util.*"%>
<portlet:defineObjects/>
<%
String baseNS = renderResponse.getNamespace();
PortletSession ps = renderRequest.getPortletSession();
%>

<I>This portlet is testing basic functions...</I>
<P>
<FONT SIZE="-1">
<B>Testing Portlet Actions...</B><BR>
<%
PortletURL url = renderResponse.createActionURL();
url.setParameter("checkAction","action1");
url.setSecure(renderRequest.isSecure());
%>
click <A HREF="<%=url.toString()%>">here</A> to invoke the first portlet action.<BR>
<%
if ("action1".equals(ps.getAttribute("checkAction", PortletSession.PORTLET_SCOPE)))
{
    out.print("Result: ");
    out.print("<b>passed</b>");
}
%>
<P>
<B>Testing RenderParameters with Portlet Actions...</B><BR>
<%
PortletURL url1 = renderResponse.createActionURL();
url1.setParameter("checkActionRender","step1");
url1.setParameter("jspNameTransfer","test4.jsp");
url.setSecure(renderRequest.isSecure());
%>
click <A HREF="<%=url1.toString()%>">here</A> for step 1.<BR>
<%
if ("step2".equals(renderRequest.getParameter("checkActionRender2")))
{
    PortletURL url2 = renderResponse.createRenderURL();
    url2.setParameter("checkActionRender2","step2");
    url2.setParameter("checkActionRender3","step3");
    url2.setParameter("jspName","test4.jsp");
    url2.setSecure(renderRequest.isSecure());
%>
click <A HREF="<%=url2.toString()%>">here</A> for step 2.<BR>
<%
}
if (("step3".equals(renderRequest.getParameter("checkActionRender3"))) &&
    ("step2".equals(renderRequest.getParameter("checkActionRender2"))))
{
    out.print("Result: ");
    out.print("<b>passed</b>");
}
%>

<%
url = renderResponse.createRenderURL();
url.setParameter("jspName","test5.jsp");
url.setSecure(renderRequest.isSecure());
%>
<FORM METHOD="POST" ACTION="<%=url.toString()%>">
<INPUT value="Next >>" TYPE="submit">
</FORM>
</FONT>

