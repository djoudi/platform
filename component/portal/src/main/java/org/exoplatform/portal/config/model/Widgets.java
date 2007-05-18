/***************************************************************************
 * Copyright 2001-2007 The eXo Platform SARL         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package org.exoplatform.portal.config.model;

import java.util.ArrayList;

/**
 * Created by The eXo Platform SARL
 * Author : Nhu Dinh Thuan
 *          nhudinhthuan@exoplatform.com
 * May 18, 2007  
 */
public class Widgets {
  
  private String id;
  
  private String      ownerType;
  private String      ownerId;
  
  private String accessPermission;
  private transient String[]    accessPermissions ;
  
  private String editPermission;
  
  private ArrayList<Container> children = new ArrayList<Container>();
  
  public String getOwnerId() { return ownerId; }
  public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

  public String getOwnerType() { return ownerType; }
  public void setOwnerType(String ownerType) { this.ownerType = ownerType; }

  public String[] getAccessPermissions(){  return accessPermissions; }
  public void     setAccessPermissions(String[] s) { accessPermissions = s ; }
  
  public String getEditPermission() { return editPermission; }
  public void setEditPermission(String editPermission) { this.editPermission = editPermission; }
  
  public ArrayList<Container> getChildren(){ return children; }
  public void setChildren(ArrayList<Container> values) { children = values; }
  
  public String getId() {
    if(id == null) id = ownerType +"::"+ownerId;
    return id; 
  }
  
  public String getAccessPermission(){
    if(accessPermissions == null)  return "";
    StringBuilder builder = new StringBuilder();
    for(int i = 0; i < accessPermissions.length; i++) {
      builder.append(accessPermissions[i]) ;
      if (i < accessPermissions.length - 1) builder.append(',');
    }
    return builder.toString();
  }
  
  public void setAccessPermission(String s){ 
    this.accessPermission = s;
    if(accessPermission == null) return ;
    accessPermissions = accessPermission.split(",");
    for(int i = 0; i < accessPermissions.length; i++) {
      accessPermissions[i] = accessPermissions[i].trim(); 
    }
  }
  
}
