/***************************************************************************
 * Copyright 2001-2003 The eXo Platform SARL         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package org.exoplatform.webui.component;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.component.validator.Validator;
import org.exoplatform.webui.event.Event;
/**
 * Created by The eXo Platform SARL
 * Author : Tuan Nguyen
 *          tuan08@users.sourceforge.net
 * Jun 6, 2006
 */
abstract public class UIFormInputBase<T> extends UIContainer implements UIFormInput<T> {
  
  final static public boolean  ENABLE = true, DISABLE = false;
  
  protected String name ;
  protected String bindingField ;
  
  protected List<Validator>  validators ;
  
  protected T defaultValue_; 
  protected T value_;
  
  protected Class<T> typeValue_ ;
  
  protected boolean enable_ = true;  
  protected boolean readonly_ = false;
  
  public UIFormInputBase(String name, String bindingField,Class<T> typeValue) {
    this.name = name ;
    this.bindingField =  bindingField ;
    this.typeValue_ = typeValue;
    setId(name);
  }  
  
  public String getName()  { return name ; }
  public void   setName(String name) { this.name = name ; }
  
  public String getBindingField() { return bindingField ; }
  public void   setBindingField(String s) {  this.bindingField = s ; }
  
  public T getDefaultValue() { return defaultValue_; }
  public void setDefaultValue(T defaultValue) { defaultValue_ = defaultValue; }
  
  public T getValue(){ return value_; }    
  public UIFormInput setValue(T value){
    this.value_ = value;
    return this;
  }  
  
  public Class<T> getTypeValue() { return typeValue_; }
  
  public void reset(){ value_ = defaultValue_; }
    
  public boolean isEnable() { return enable_; }  
  public UIFormInputBase<T> setEnable(boolean enable) {
    enable_ = enable;
    return this;
  }
  
  public boolean isEditable() { return !readonly_; }
  public UIFormInputBase<T> setEditable(boolean editable) { 
    readonly_ = !editable;
    return this;
  }
  
  public UIFormInputBase<T> addValidator(Class clazz) throws Exception {
    if(validators == null)  validators = new ArrayList<Validator>(3) ;
    validators.add((Validator)clazz.newInstance()) ;
    return this ;
  } 
  
  public List<Validator>  getValidators() { return validators ; }
  
  final public void processDecode(WebuiRequestContext context) throws Exception {
    UIForm uiForm  = getAncestorOfType(UIForm.class);
    String action =  uiForm.getSubmitAction(); //context.getRequestParameter(UIForm.ACTION) ;
    Event<UIComponent> event = createEvent(action, Event.Phase.DECODE, context) ;
    if(event != null) event.broadcast() ;   
  }
  
  abstract public  void decode(Object input,  WebuiRequestContext context) throws Exception ;
  
  @SuppressWarnings("unused")
  public void decodeFromMultipartFields(WebuiRequestContext context, UIComponent component, List items) {}
 
}