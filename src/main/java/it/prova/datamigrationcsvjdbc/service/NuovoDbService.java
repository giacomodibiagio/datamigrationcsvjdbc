package it.prova.datamigrationcsvjdbc.service;

import it.prova.datamigrationcsvjdbc.connection.MyConnection;
import it.prova.datamigrationcsvjdbc.dao.Constants;
import it.prova.datamigrationcsvjdbc.dao.NuovoDbDAO;
import it.prova.datamigrationcsvjdbc.model.Assicurato;
import it.prova.datamigrationcsvjdbc.model.NotProcessed;

import java.sql.Connection;

public class NuovoDbService {

    private NuovoDbDAO nuovoDbDAO;


    public void setNuovoDAO(NuovoDbDAO nuovoDbDAO) {
        this.nuovoDbDAO = nuovoDbDAO;

    }


    public int inserisciAssicurato(Assicurato input) throws Exception {
        if (input == null)
            throw new Exception("Valore di input non ammesso.");


        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NUOVO)) {

            // inietto la connection nel dao
            nuovoDbDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = nuovoDbDAO.insertAssicurato(input);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }


    public int inserisciNotProcessed(NotProcessed input) throws Exception {
        if (input == null)
            throw new Exception("Valore di input non ammesso.");


        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NUOVO)) {

            // inietto la connection nel dao
            nuovoDbDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = nuovoDbDAO.insertNotProcessed(input);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}
