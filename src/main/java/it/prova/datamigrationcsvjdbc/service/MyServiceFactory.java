package it.prova.datamigrationcsvjdbc.service;


import it.prova.datamigrationcsvjdbc.dao.NuovoDbDAO;

public class MyServiceFactory {

    public static NuovoDbService getNuovoService() {
        NuovoDbService nuovoDbService = new NuovoDbService();
        nuovoDbService.setNuovoDAO(new NuovoDbDAO());
        return nuovoDbService;
    }
}
