package it.prova.datamigrationcsvjdbc.service;

import it.prova.datamigrationcsvjdbc.model.Assicurato;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CsvReaderService {

    public static List<Assicurato> readCsv(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        List<String[]> assicuratiDaVecchioDbString = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            assicuratiDaVecchioDbString.add(data);
        }
        csvReader.close();

        List<Assicurato> assicuratiDaVecchioDb = new ArrayList<>();
        for (int i = 0; i < assicuratiDaVecchioDbString.size(); i++){
            String[] assicuratoDaVecchioDbString = assicuratiDaVecchioDbString.get(i);
            Assicurato assicurato = new Assicurato();
            assicurato.setId(Long.parseLong(assicuratoDaVecchioDbString[0]));
            assicurato.setNome(assicuratoDaVecchioDbString[1]);
            assicurato.setCognome(assicuratoDaVecchioDbString[2]);
            assicurato.setCodiceFiscale(assicuratoDaVecchioDbString[3]);
            assicurato.setData(Date.valueOf(assicuratoDaVecchioDbString[4]));
            assicurato.setNumeroSinistri(Integer.valueOf(assicuratoDaVecchioDbString[5]));
            assicuratiDaVecchioDb.add(assicurato);
        }
        return assicuratiDaVecchioDb;
    }

}
