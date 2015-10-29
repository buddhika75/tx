package lk.gov.health.tx.controllers;

import lk.gov.health.tx.entities.Doctors;
import lk.gov.health.tx.controllers.util.JsfUtil;
import lk.gov.health.tx.controllers.util.JsfUtil.PersistAction;
import lk.gov.health.tx.facelets.DoctorsFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import lk.gov.health.tx.entities.Units;

@Named("doctorsController")
@SessionScoped
public class DoctorsController implements Serializable {

    @EJB
    private lk.gov.health.tx.facelets.DoctorsFacade ejbFacade;
    private List<Doctors> items = null;
    private List<Doctors> searchedItems = null;
    Units unit;
    private Doctors selected;

    public List<Doctors> getSearchedItems() {
        return searchedItems;
    }

    public void setSearchedItems(List<Doctors> searchedItems) {
        this.searchedItems = searchedItems;
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    public void searchByUnit(){
        searchedItems = getFacade().getDoctorsByUnit(unit);
    }
    
    public DoctorsController() {
    }

    public Doctors getSelected() {
        return selected;
    }

    public void setSelected(Doctors selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DoctorsFacade getFacade() {
        return ejbFacade;
    }

    public Doctors prepareCreate() {
        selected = new Doctors();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DoctorsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DoctorsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DoctorsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Doctors> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Doctors getDoctors(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Doctors> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Doctors> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Doctors.class)
    public static class DoctorsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DoctorsController controller = (DoctorsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "doctorsController");
            return controller.getDoctors(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Doctors) {
                Doctors o = (Doctors) object;
                return getStringKey(o.getNic());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Doctors.class.getName()});
                return null;
            }
        }

    }

}
