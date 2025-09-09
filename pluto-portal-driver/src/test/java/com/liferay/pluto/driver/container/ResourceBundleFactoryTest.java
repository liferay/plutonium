package com.liferay.pluto.driver.container;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

import com.liferay.pluto.container.om.portlet.PortletDefinition;
import com.liferay.pluto.container.om.portlet.PortletInfo;
import com.liferay.pluto.container.om.portlet.impl.PortletDefinitionImpl;
import com.liferay.pluto.container.om.portlet.impl.PortletInfoImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceBundleFactoryTest {

    private PortletDefinition validDD;

    @Before
    public void setUp() {
        String title = "Info Title";
        String shortTitle = "Info Short Title";
        String keywords = "Info Keywords";
        PortletInfo info = new PortletInfoImpl(title, keywords, shortTitle);
        validDD = new PortletDefinitionImpl("RBTestPortlet", null);
        validDD.setPortletInfo(info);
        validDD.setResourceBundle(TestResourceBundle.class.getName());
    }

    @Test
    public void testGetBundleAllSpecified() {
        ResourceBundleFactory factory = new ResourceBundleFactory(validDD, validDD.getPortletInfo());
        ResourceBundle bundle = factory.getResourceBundle(Locale.getDefault());
        assertEquals("Bundle Title", bundle.getString("jakarta.portlet.title"));
        assertEquals("Bundle Short Title", bundle.getString("jakarta.portlet.short-title"));
        assertEquals("Bundle Keywords", bundle.getString("jakarta.portlet.keywords"));
    }

    @Test
    public void testGetResourceBundleNoBundle() {
        validDD.setResourceBundle(null);
        ResourceBundleFactory factory = new ResourceBundleFactory(validDD, validDD.getPortletInfo());
        ResourceBundle bundle = factory.getResourceBundle(Locale.getDefault());
        assertEquals("Info Title", bundle.getString("jakarta.portlet.title"));
        assertEquals("Info Short Title", bundle.getString("jakarta.portlet.short-title"));
        assertEquals("Info Keywords", bundle.getString("jakarta.portlet.keywords"));
    }

    @Test
    public void testGetResourceBundleNoInfo() {
        ResourceBundleFactory factory = new ResourceBundleFactory(validDD, validDD.getPortletInfo());
        ResourceBundle bundle = factory.getResourceBundle(Locale.getDefault());
        assertEquals("Bundle Title", bundle.getString("jakarta.portlet.title"));
        assertEquals("Bundle Short Title", bundle.getString("jakarta.portlet.short-title"));
        assertEquals("Bundle Keywords", bundle.getString("jakarta.portlet.keywords"));
    }

    public static class TestResourceBundle extends ListResourceBundle {
        private final Object[][] contents = {
            {"jakarta.portlet.title", "Bundle Title"},
            {"jakarta.portlet.short-title", "Bundle Short Title"},
            {"jakarta.portlet.keywords", "Bundle Keywords"}
        };

        @Override
        protected Object[][] getContents() {
            return contents;
        }
    }
}
