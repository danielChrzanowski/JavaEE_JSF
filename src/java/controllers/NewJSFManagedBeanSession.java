package controllers;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dao.UzytkownikDao;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import to.UzytkownikTO;

@ManagedBean
@SessionScoped
public class NewJSFManagedBeanSession {

    java.util.ArrayList<UzytkownikTO> uzytkownikTOList = new java.util.ArrayList<>();

    @ManagedProperty(value = "#{newJSFManagedBeanApplication}")
    NewJSFManagedBeanApplication applicationController;

    public NewJSFManagedBeanSession() {
        refreshData();
    }

    public ArrayList<UzytkownikTO> getUzytkownikTOList() {
        return uzytkownikTOList;
    }

    public void setUzytkownikTOList(ArrayList<UzytkownikTO> uzytkownikTOList) {
        this.uzytkownikTOList = uzytkownikTOList;
    }

    public void addRow(UzytkownikTO uzytkownikTO) {
        int indexObject = uzytkownikTOList.indexOf(uzytkownikTO);
        UzytkownikTO newRow = new UzytkownikTO(-1l, "", "", true);

        UzytkownikDao daneDao = new UzytkownikDao();
        Long id = daneDao.create(newRow);
        if (id != null) {
            newRow.setId(id);
            uzytkownikTOList.add(indexObject + 1, newRow);
        }
    }

    public void delRow(UzytkownikTO uzytkownikTO) {
        int indexObject = uzytkownikTOList.indexOf(uzytkownikTO);
        UzytkownikDao daneDao = new UzytkownikDao();
        if (daneDao.delete(uzytkownikTO.getId()) != -1) {
            uzytkownikTOList.remove(indexObject);
        }
    }

    public void visibleChange(UzytkownikTO uzytkownikTO) {
        int indexObject = uzytkownikTOList.indexOf(uzytkownikTO);
        UzytkownikDao daneDao = new UzytkownikDao();
        if (daneDao.update(uzytkownikTO) != -1) {
            uzytkownikTOList.set(indexObject, uzytkownikTO);
        }

    }

    public void sort(String dir) {
        Comparator<UzytkownikTO> comparator = (UzytkownikTO a, UzytkownikTO b) -> {
            int compareResult = 0;
            if (dir.equals("asc")) {
                compareResult = a.getNazwisko().compareTo(b.getNazwisko());
            } else {
                compareResult = b.getNazwisko().compareTo(a.getNazwisko());
            }
            return compareResult;
        };
        Collections.sort(uzytkownikTOList, comparator);
    }

    public final void refreshData() {
        UzytkownikDao daneDao = new UzytkownikDao();
        List<UzytkownikTO> uzytkownikToListLokal = daneDao.findAll();

        if (uzytkownikToListLokal != null) {
            uzytkownikTOList.clear();
            uzytkownikTOList = (ArrayList<UzytkownikTO>) uzytkownikToListLokal;
        }
    }

    public void setApplicationController(NewJSFManagedBeanApplication applicationController) {
        this.applicationController = applicationController;
    }

    public String getDataFromReference() {
        return applicationController.appVariable + " - modified in Session controller";
    }

}
