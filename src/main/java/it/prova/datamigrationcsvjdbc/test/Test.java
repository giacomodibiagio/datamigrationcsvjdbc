package it.prova.datamigrationcsvjdbc.test;

import it.prova.datamigrationcsvjdbc.model.Assicurato;
import it.prova.datamigrationcsvjdbc.model.NotProcessed;
import it.prova.datamigrationcsvjdbc.service.CsvReaderService;
import it.prova.datamigrationcsvjdbc.service.MyServiceFactory;
import it.prova.datamigrationcsvjdbc.service.NuovoDbService;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws Exception {
        NuovoDbService service = MyServiceFactory.getNuovoService();

        //csv reader legge il file csv e converte la lista di array di stringhe in una lista di oggetti assicurato
        List<Assicurato> assicuratiDaVecchioDb = CsvReaderService.readCsv("assicurati.csv");

        //ora posso usare gli stream sulla lista di assicurati estratta dal csv
        List<Assicurato> assicurati = assicuratiDaVecchioDb.stream().filter(assicurato -> assicurato.getCodiceFiscale().length() == 16).collect(Collectors.toList());
        List<Assicurato> assicuratiNotProcessed = assicuratiDaVecchioDb.stream().filter(assicurato -> assicurato.getCodiceFiscale().length() != 16).collect(Collectors.toList());

        //inserisco nel db
        assicurati.stream().forEach(assicuratoItem -> {
            try {
                service.inserisciAssicurato(assicuratoItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        assicuratiNotProcessed.stream().forEach(assicuratoNonProcessatoItem -> {
            try {
                NotProcessed notProcessedItem = new NotProcessed();
                notProcessedItem.setCodiceFiscale(assicuratoNonProcessatoItem.getCodiceFiscale());
                notProcessedItem.setOldId(assicuratoNonProcessatoItem.getId());
                service.inserisciNotProcessed(notProcessedItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
