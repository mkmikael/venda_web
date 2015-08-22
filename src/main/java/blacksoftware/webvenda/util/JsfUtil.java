/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacksoftware.webvenda.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Avell G1511
 */
public class JsfUtil {
    
    public static void message(FacesMessage.Severity severity, String summary, String detail) {
        FacesMessage facesMessage = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    public static void info(String summary, String detail) {
        message(FacesMessage.SEVERITY_INFO, summary, detail);
    }
    
    public static void error(String summary, String detail) {
        message(FacesMessage.SEVERITY_ERROR, summary, detail);
    }
    
    public static void warn(String summary, String detail) {
        message(FacesMessage.SEVERITY_WARN, summary, detail);
    }
    
    public static void fatal(String summary, String detail) {
        message(FacesMessage.SEVERITY_FATAL, summary, detail);
    }
}
