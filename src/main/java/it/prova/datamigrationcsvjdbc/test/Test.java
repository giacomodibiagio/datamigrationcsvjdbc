package it.prova.datamigrationcsvjdbc.test;

import it.prova.datamigrationcsvjdbc.model.Assicurato;
import it.prova.datamigrationcsvjdbc.model.NotProcessed;
import it.prova.datamigrationcsvjdbc.service.MyServiceFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader("assicurati.csv"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");

            if(data[3].length() != 16){
                NotProcessed notProcessed = new NotProcessed();
                notProcessed.setCodiceFiscale(data[2]);
                notProcessed.setOldId(Long.parseLong(data[0]));
                MyServiceFactory.getNuovoService().inserisciNotProcessed(notProcessed);
            } else {
                Assicurato assicurato = new Assicurato();
                assicurato.setNome(data[1]);
                assicurato.setCognome(data[2]);
                assicurato.setCodiceFiscale(data[3]);
                assicurato.setData(Date.valueOf(data[4]));
                assicurato.setNumeroSinistri(Integer.valueOf(data[5]));
                MyServiceFactory.getNuovoService().inserisciAssicurato(assicurato);
            }

        }
        csvReader.close();
    }
}
