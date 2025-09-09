/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor;


/**
 * Identifies the annotated method type.
 * <p>
 * The dispatchId is the action name for action methods, resource ID for resource
 * methods, the portlet mode as string for header & render methods, and the processing
 * event reference QName for event methods. 
 * 
 * @author Scott
 *
 */
public class MethodIdentifier {
   
   private final  String portletName;
   private        Object dispatchId;
   private final  MethodType type;

   /**
    * Constructor
    */
   public MethodIdentifier(String name, Object id, MethodType type) {;
      this.portletName = name;
      this.dispatchId = id;
      this.type = type;
   }
   
   /**
    * Copy constructor
    */
   public MethodIdentifier(MethodIdentifier mi) {
      this.portletName = mi.portletName;
      this.dispatchId = mi.dispatchId;
      this.type = mi.type;
   }
   
   public String getName() {
      return portletName;
   }
   
   public Object getId() {
      return dispatchId;
   }
   
   public void setId(Object id) {
      this.dispatchId = id;
   }
   
   public MethodType getType() {
      return type;
   }

   @Override
   public boolean equals(Object o) {
      boolean eq = false;
      if (o instanceof MethodIdentifier) {
         if (o == this) {
            eq = true; 
         } else {
            MethodIdentifier mi = (MethodIdentifier) o;
            if (equals(portletName, mi.portletName)) {
               if (equals(type, mi.type)) {
                  if (equals(dispatchId, mi.dispatchId)) {
                     eq = true;
                  }
               }
            }
         }
      }
      return eq;
   }

   @Override
   public int hashCode() {
      int hc = 17;
      if (portletName != null) {
         hc += 37*hc + portletName.hashCode();
      }
      if (dispatchId != null) {
         hc += 37*hc + dispatchId.hashCode();
      }
      if (type != null) {
         hc += 37*hc + type.hashCode();
      }
      return hc;
   }

   /**
    * helper function for equality comparison. 
    */
   private boolean equals(Object o1, Object o2) {
      boolean eq = false;
      if (o1 == o2) {
         eq = true;
      } else if (o1 != null && o2 != null) {
         eq = o1.equals(o2);
      }
      return eq;
   }
   
   public String toString() {
      StringBuilder txt = new StringBuilder(256);
      txt.append("Portlet name='").append(portletName).append("'");
      txt.append(", Method type='").append(type).append("'");
      txt.append(", Dispatch ID='").append(dispatchId).append("'");
      return txt.toString();
   }

}
