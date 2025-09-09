<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page session="false" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<portlet:defineObjects />

<div class='bluebox'>
<h4>Chat room:</h4>
<div id='<portlet:namespace/>-chatHistory' class='chatbox'></div>

<div class='parmbox'>
<FORM id='<portlet:namespace/>-sendMsg' METHOD='POST' enctype='application/x-www-form-urlencoded'>
   <table style='width:100%'><tr>
   
   <td align='left'>
   Message:
   </td><td style='width:100%'>
   <input id='<portlet:namespace/>-msg' type='text' name='message' value='' maxlength='100' style='width:100%'>
   </td>
   </tr><tr>
   
   <td>
   <INPUT id='<portlet:namespace/>-send' value='send' type='submit'>
   </td><td align='right'>
   <INPUT id='<portlet:namespace/>-clear' type='button' name='action' value='clear history' />
   </td></tr></table>
   <INPUT type='hidden' name='jakarta.portlet.action' value='addMessage'>
</FORM>
</div>
</div>

<script>
(function () {
   'use strict';
   var pid = '<portlet:namespace/>',
       resdiv = '<portlet:namespace/>-chatHistory',
       msgForm = '<portlet:namespace/>-sendMsg',
       msgField = '<portlet:namespace/>-msg',
       sendButton = '<portlet:namespace/>-send',
       clrButton = '<portlet:namespace/>-clear',
       
       resParams = null,
       actionParams = null,
   
       state,
       hub,
       running = false,
      
   // The function that retrieves the chat history. The URL is created using
   // respource parameters that are set to an initial parameter for the initial fetch
   // but are then reset to null. The resource ID is set to address the server-side
   // method carrying the resource ID 'getChatHistory'. Once started, this function 
   // loops forever. 
   
   getChat = function () {
      hub.createResourceUrl(resParams, null, 'getChatHistory').then(function (url) {
         resParams = null;
         var xhr = new XMLHttpRequest();
         console.log("CHAT: got url: " + url);
         xhr.onreadystatechange = function () {
            var el;
            if (xhr.readyState === 4 && xhr.status === 200) {
               if (xhr.responseText.length > 0) {
                  el = document.getElementById(resdiv);
                  el.innerHTML = xhr.responseText;
                  if (el.scrollHeight > el.clientHeight) {
                     el.scrollTop = el.scrollHeight - el.clientHeight;
                  }
               } else {
                  console.log("empty response. timeout?");
               }
               getChat();
            }
         };
         xhr.open("GET",url,true);
         xhr.send();
      });
   },
   
   // Handler for onStateChange event. Called after initial registration and whenever the 
   // state for this portlet changes. The first time through, we set the resource 
   // parameters to refresh the chat history and start the get chat history loop.
    
   update = function (type, s) {
      state = s;
      if (!running) {
         running = true;
         resParams = {};
         resParams['refresh'] = ['true'];
         getChat();
      }
   }; 
   
   // Handler for "send" button. execute an Ajax action to send the message to the server.
   // The form of the portlet hub action() method used here submits the entire form 
   // through an Ajax call. Any resulting state change is communicated through the 
   // onStateChange event handler. 
   
   document.getElementById(msgForm).onsubmit = function () {
      hub.action(document.getElementById(msgForm));
      return false;
   };
   
   // Handler for "clear" button. This form of the portlet hub action method 
   // doesn't submit a form, but rather just some action parameters. In this case,
   // the reserved parameter with name 'jakarta.portlet.action' is set to the
   // value 'clearHistory', causing the portlet container to execute the 
   // action method carrying this action name.
   
   document.getElementById(clrButton).onclick = function () {
      actionParams = {};
      actionParams['jakarta.portlet.action'] = ['clearHistory'];
      hub.action(actionParams);
      return false;
   };
   
   // Register portlet with Portlet Hub; add onStateChange listener 
   // The portlet hub register() method takes the portlet ID (aka portlet namespace) as
   // a parameter and returns a promise, which it then fulfills with a PortletInit object
   // that provides access to portlet hub functions valid for the registered portelt ID
   // only (see documentation).

   portlet.register(pid).then(function (pi) {
      hub = pi;
      
      // A portlet client must add an 'onStateChange' listener so that the portlet
      // hub can notify the portlet client of its initial state. The portlet hub will
      // also call the listener function whenever the portlet state changes.
      
      hub.addEventListener("portlet.onStateChange", update);
   });
   
}());
</script>


