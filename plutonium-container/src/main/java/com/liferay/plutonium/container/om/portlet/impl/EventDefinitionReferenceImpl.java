/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import javax.xml.namespace.QName;

import com.liferay.plutonium.container.om.portlet.EventDefinitionReference;

/**
 * Reference to an event definition
 * 
 * @author Scott Nicklous
 *
 */
public class EventDefinitionReferenceImpl implements EventDefinitionReference {

   private QName qname;

   
   /**
    * Copy Constructor
    * @param edri
    */
   public EventDefinitionReferenceImpl(EventDefinitionReference edri) {
      QName qn = edri.getQualifiedName();
      this.qname = new QName(qn.getNamespaceURI(), qn.getLocalPart());
   }
   
   /**
    * Constructor
    * @param qname
    */
   public EventDefinitionReferenceImpl(QName qname) {
      this.qname = qname;
   }
   
   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinitionReference#getQualifiedName()
    */
   @Override
   public QName getQualifiedName() {
      return qname;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinitionReference#setQualifiedName(javax.xml.namespace.QName)
    */
   @Override
   public void setQualifiedName(QName qn) {
      this.qname = qn;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((qname == null) ? 0 : qname.hashCode());
      return result;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      EventDefinitionReferenceImpl other = (EventDefinitionReferenceImpl) obj;
      if (qname == null) {
         if (other.qname != null) {
            return false;
         }
      } else if (!qname.equals(other.qname)) {
         return false;
      }
      return true;
   }

}
