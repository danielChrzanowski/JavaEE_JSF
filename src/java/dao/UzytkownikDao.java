package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import to.UzytkownikTO;

public class UzytkownikDao {

    protected static String SQL_SELECT_ALL = "select id, imie, nazwisko from t_uzytkownik order by id";
    protected static String SQL_SELECT = "select id, imie, nazwisko from t_uzytkownik where id=?";
    protected static String SQL_UPDATE = "update t_uzytkownik set id=?, imie=?, nazwisko=? where id=?";
    protected static String SQL_INSERT = "insert into t_uzytkownik (id, imie, nazwisko) values (?,?,?)";
    protected static String SQL_DELETE = "delete from t_uzytkownik where id=?";

    Connection connection;

    public UzytkownikDao() {
        try {
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/bazaTestowaMSSQL");
            String name = dataSource.toString();
            connection = dataSource.getConnection();

        } catch (SQLException | NamingException ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            servletContext.log(servletContext.getContextPath() + ":" + ex.toString());
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
        }
    }

    private Long getNextId() {
        try {
            ResultSet wynikZapytania = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("select max(id) from t_uzytkownik");
            wynikZapytania.first();
            long lMaxId = wynikZapytania.getLong(1) + 1;
            return lMaxId;
        } catch (SQLException ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return null;
        }
    }

    public Long create(UzytkownikTO uzytkownikTO) {
        Long uzytkownikToId;

        try {
            uzytkownikToId = getNextId();
            PreparedStatement zapytanie = connection.prepareStatement(SQL_INSERT);
            zapytanie.setLong(1, uzytkownikToId);
            zapytanie.setString(2, uzytkownikTO.getImie());
            zapytanie.setString(3, uzytkownikTO.getNazwisko());

            int iLiczbaZmian = zapytanie.executeUpdate();
            return uzytkownikToId;
        } catch (SQLException ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(ex.toString()));
                }
            }
        }
    }

    public Long delete(Long id) {
        try {
            PreparedStatement zapytanie = connection.prepareStatement(SQL_DELETE);
            zapytanie.setLong(1, id);

            int iLiczbaZmian = zapytanie.executeUpdate();
            return id;
        } catch (SQLException ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return -1l;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(ex.toString()));
                }
            }
        }
    }

    public Long update(UzytkownikTO uzytkownikTO) {
        try {
            PreparedStatement zapytanie = connection.prepareStatement(SQL_UPDATE);
            zapytanie.setLong(1, uzytkownikTO.getId());
            zapytanie.setString(2, uzytkownikTO.getImie());
            zapytanie.setString(3, uzytkownikTO.getNazwisko());
            zapytanie.setLong(4, uzytkownikTO.getId());

            int iLiczbaZmian = zapytanie.executeUpdate();
            return uzytkownikTO.getId();
        } catch (SQLException ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return -1l;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(ex.toString()));
                }
            }
        }
    }

    public List<UzytkownikTO> findAll() {
        List<UzytkownikTO> uzytkownikToList = new ArrayList();

        try {
            ResultSet wynikZapytania = connection.prepareStatement(SQL_SELECT_ALL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery();
            boolean bCzyJestWiersz = wynikZapytania.first();

            while (bCzyJestWiersz) {
                uzytkownikToList.add(new UzytkownikTO(wynikZapytania.getLong("id"), wynikZapytania.getString("imie"), wynikZapytania.getString("nazwisko"), false));
                bCzyJestWiersz = wynikZapytania.next();
            }
            return uzytkownikToList;

        } catch (SQLException ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(ex.toString()));
                }
            }
        }

    }

}
