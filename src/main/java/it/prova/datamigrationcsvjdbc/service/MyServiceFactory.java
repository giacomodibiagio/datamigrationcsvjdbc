package it.prova.datamigrationcsvjdbc.service;


import it.prova.datamigrationcsvjdbc.dao.NuovoDAO;

public class MyServiceFactory {

    public static NuovoService getNuovoService() {
        NuovoService nuovoService = new NuovoService();
        nuovoService.setNuovoDAO(new NuovoDAO());
        return nuovoService;
    }


}
