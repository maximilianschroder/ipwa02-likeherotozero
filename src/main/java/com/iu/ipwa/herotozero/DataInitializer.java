package com.iu.ipwa.herotozero;

import com.iu.ipwa.herotozero.model.EmissionData;
import com.iu.ipwa.herotozero.repository.EmissionDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // Sagt Spring: "Lade mich beim Start"
public class DataInitializer implements CommandLineRunner {

    private final EmissionDataRepository repository;

    // Konstruktor-Injektion (Best Practice statt @Autowired)
    public DataInitializer(EmissionDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Wir prüfen, ob schon Daten da sind, um Dopplungen bei Neustart (auf echten DBs) zu vermeiden
        if (repository.count() == 0) {
            System.out.println("... Erstelle Dummy-Daten ...");

            EmissionData d1 = new EmissionData();
            d1.setCountry("Deutschland");
            d1.setYear(2023);
            d1.setValue(670.5);
            repository.save(d1);

            EmissionData d2 = new EmissionData();
            d2.setCountry("USA");
            d2.setYear(2023);
            d2.setValue(4500.0);
            repository.save(d2);

            EmissionData d3 = new EmissionData();
            d3.setCountry("China");
            d3.setYear(2023);
            d3.setValue(11000.2);
            repository.save(d3);

            System.out.println("... Dummy-Daten gespeichert! ...");
        }
    }
}