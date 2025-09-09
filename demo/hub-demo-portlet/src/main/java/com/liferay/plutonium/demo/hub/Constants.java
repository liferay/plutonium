/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.hub;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {

   // maps the names of the images to the paths to the images -
   public final static Map<String, String> IMG_MAP;

   static {
      Map<String, String> imgMap = new HashMap<String, String>();
      imgMap.put("baseball", "/resources/images/baseball-trans.gif");
      imgMap.put("golfball", "/resources/images/golfball-trans.gif");
      imgMap.put("fussball", "/resources/images/fussball-trans.gif");
      IMG_MAP = Collections.unmodifiableMap(imgMap);
   }

   public final static String DEFAULT_IMAGE = "/resources/images/FullMoon.gif";

   public final static String PARAM_SELTYPE = "selType";
   public final static String PARAM_SELTYPE_RADIO = "radio";
   public final static String PARAM_SELTYPE_DROPDOWN = "dropdown";
   
   // privae parameter for color selection portlet
   public final static String PARAM_SUBTYPE = "subType";
   public final static String PARAM_SUBTYPE_URL = "URL";
   public final static String PARAM_SUBTYPE_FORM = "form";
   
   public final static String PARAM_IMGNAME = "imgName";
   public final static String PARAM_COLOR = "color";
   public final static String PARAM_ERRMSG = "errmsg";
   public final static String PARAM_BORDER = "bo";
   public final static String PARAM_BORDER_COLOR = "border";
   public final static String PARAM_CACHE = "ca";
   public final static String PARAM_CACHE_PAGE = "pa";
   public final static String PARAM_CACHE_PORTLET = "po";
   public final static String PARAM_CACHE_FULL = "fu";
   
   public final static String PARAM_FG_COLOR = "fgcolor";
   public final static String PARAM_FG_RED = "red";
   public final static String PARAM_FG_GREEN = "green";
   public final static String PARAM_FG_BLUE = "blue";
   public final static String PARAM_MSG_INPUT = "imsg";
   
   public final static String PARAM_NUM_ACTIONS = "numActions";

   public final static String PARAM_NUM_MSGS = "numMsgs";
   public final static String ATTRIB_MSGS = "msgs";
   
   public final static String DELIM = ";%;";

   public final static String PARAM_NAME = "pName";
   public final static String PARAM_VALUES = "pValues";
   public final static String PARAM_REMTYPE = "remType";
   public final static String PARAM_REMTYPE_REM = "remPRP";
   public final static String PARAM_REMTYPE_SET = "setRP";
   public final static String PARAM_SETTYPE = "setType";
   public final static String PARAM_SETTYPE_VAL = "setVal";
   public final static String PARAM_SETTYPE_VARRAY = "setVals";
}
