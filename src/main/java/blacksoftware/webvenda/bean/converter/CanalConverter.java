/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacksoftware.webvenda.bean.converter;

import blacksoftware.webvenda.model.Canal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Avell G1511
 */
@FacesConverter(value = "canalConverter")
public class CanalConverter implements Converter {

    private final Converter converter = new ModelConverter<>(Canal.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return converter.getAsObject(context, component, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return converter.getAsString(context, component, value);
    }

}
