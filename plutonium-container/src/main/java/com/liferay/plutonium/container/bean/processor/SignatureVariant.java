/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor;

/**
 * Enum to identify the signature variant for annotated methods that allow
 * multiple method signatures.
 * 
 * @author Scott Nicklous
 */
public enum SignatureVariant {

   /**
    * The default signature for the method.
    */
   DEFAULT,

   /**
    * Signature variant for ActionMethod, with return value String and
    * taking a ActionRequest and ActionResponse as arguments.
    */
   STRING_ACTIONREQ_ACTIONRESP,

   /**
    * Signature variant for RenderMethod, with return value String and
    * taking a RenderRequest and RenderResponse as arguments.
    */
   STRING_RENDERREQ_RENDERRESP,

   /**
    * Signature variant for ResourceMethod, with return value String and
    * taking a ResourceRequest and ResourceResponse as arguments.
    */
   STRING_RESOURCEREQ_RESOURCERESP,

   /**
    * Signature variant for render, header, and resource methods, with return
    * value String and taking no arguments.
    */
   STRING_VOID,

   /**
    * Signature variant for HeaderMethod, with return value void and 
    * taking a HeaderRequest and HeaderResponse as arguments.
    */
   VOID_HEADERREQ_HEADERRESP,
   
   /**
    * Signature variant for RenderMethod, with return value void and 
    * taking a RenderRequest and RenderResponse as arguments.
    */
   VOID_RENDERREQ_RENDERRESP,
   
   /**
    * Signature variant for ResourceMethod, with return value void and 
    * taking a ResourceRequest and ResourceResponse as arguments.
    */
   VOID_RESOURCEREQ_RESOURCERESP,

   /**
    * Signature variant for render, header, and resource methods, with return 
    * value void and taking no arguments.
    */
   VOID_VOID,
   
}
