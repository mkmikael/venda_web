/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacksoftware.webvenda.bean.converter;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.util.ReflectionUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Avell G1511
 * @param <T>
 */
public class ModelConverter<T> implements Converter {
    private static final Logger LOG = Logger.getLogger(ModelConverter.class.getName());

    private final DAO dao;
    private final Class<T> clazz;

    public ModelConverter(Class<T> clazz) {
        dao = new DAO();
        this.clazz = clazz;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.log(Level.INFO, "VALUE: {0}", value);
        if (value == null || value.isEmpty()) {
            return null;
        }
        T get = dao.get(clazz, new Long(value));
        LOG.log(Level.INFO, "VALUEGET: {0}", get);
        return get;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LOG.log(Level.INFO, "VALUE: {0}", value);
        if (value == null) {
            return null;
        }
        Object id = ReflectionUtil.getId(value);
        LOG.log(Level.INFO, "VALUEID: {0}", id);
        if (id == null) {
            return null;
        } else {
            return id.toString();
        }
    }

}
