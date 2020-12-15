package controllers;

import data.MenuItem;
import java.util.ArrayList;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@NoneScoped
public class NewJSFManagedBeanNone {

    java.util.ArrayList<MenuItem> menuItemList = new java.util.ArrayList<>();

    public NewJSFManagedBeanNone() {
        Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        String language = currentLocale.getLanguage();

        if (language.equals("pl")) {
            menuItemList.add(new MenuItem("Karuzela", "/views/carouselView"));
            menuItemList.add(new MenuItem("Tabela", "/views/tableView"));
            menuItemList.add(new MenuItem("TylkoStudents", "/views/students/studentsPage"));
            menuItemList.add(new MenuItem("Wylogowanie", "/logoutWindow"));
        } else {
            if (language.equals("en")) {
                menuItemList.add(new MenuItem("Carousel", "/views/carouselView"));
                menuItemList.add(new MenuItem("Table", "/views/tableView"));
                menuItemList.add(new MenuItem("OnlyStudents", "/views/students/studentsPage"));
                menuItemList.add(new MenuItem("Logout", "/logoutWindow"));
            }
        }
    }

    public ArrayList<MenuItem> getMenuItemList() {
        return menuItemList;
    }
}
