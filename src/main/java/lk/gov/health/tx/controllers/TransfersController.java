package lk.gov.health.tx.controllers;

import lk.gov.health.tx.entities.Transfers;
import lk.gov.health.tx.controllers.util.JsfUtil;
import lk.gov.health.tx.controllers.util.JsfUtil.PersistAction;
import lk.gov.health.tx.facelets.TransfersFacade;

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
import lk.gov.health.tx.entities.OrderNames;
import lk.gov.health.tx.entities.Units;

@Named("transfersController")
@SessionScoped
public class TransfersController implements Serializable {

    @EJB
    private lk.gov.health.tx.facelets.TransfersFacade ejbFacade;
    private List<Transfers> items = null;
    private List<Transfers> searchedItems = null;
    private Transfers selected;
    Units unit;
    String searchText;
    OrderNames orderName;

    public OrderNames getOrderName() {
        return orderName;
    }

    public void setOrderName(OrderNames orderName) {
        this.orderName = orderName;
    }
    
    

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void searchByFromUnit() {
        searchedItems = getFacade().getTransfersByFromUnit(unit);
    }

    public void searchByToUnit() {
        searchedItems = getFacade().getTransfersByToUnit(unit);
    }
    
    public void searchByNic(){
        searchedItems = getFacade().getTransfersByNic("%" + searchText + "%");
    }

    public void searchByOrder(){
        searchedItems = getFacade().getTransfersByOrderName(orderName);
    }
    
    public List<Transfers> getSearchedItems() {
        return searchedItems;
    }

    public void setSearchedItems(List<Transfers> searchedItems) {
        this.searchedItems = searchedItems;
    }

    public TransfersController() {
    }

    public Transfers getSelected() {
        return selected;
    }

    public void setSelected(Transfers selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TransfersFacade getFacade() {
        return ejbFacade;
    }

    public Transfers prepareCreate() {
        selected = new Transfers();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TransfersCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TransfersUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TransfersDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Transfers> getItems() {
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

    public Transfers getTransfers(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Transfers> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Transfers> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Transfers.class)
    public static class TransfersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TransfersController controller = (TransfersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "transfersController");
            return controller.getTransfers(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Transfers) {
                Transfers o = (Transfers) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Transfers.class.getName()});
                return null;
            }
        }

    }

}
