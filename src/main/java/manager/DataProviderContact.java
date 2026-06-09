package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {
    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Alex")
                .lastName("Levin")
                .phone("05475677412")
                .email("alex@lev.com")
                .address("Roboad av. 7/278")
                .description("friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Dvoretskiy")
                .phone("0547124567")
                .email("tony@dvo.com")
                .address("Dali av. 79/147")
                .description(null)
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Alex")
                .lastName("Levin")
                .phone("0547")
                .email("alex@lev.com")
                .address("Roboad av. 7/278")
                .description("friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Lev")
                .phone("0547145216847521")
                .email("john@lev.com")
                .address("Haifa")
                .description("friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Andry")
                .lastName("Liner")
                .phone("nnnnnnnnnnnn")
                .email("andry@lev.com")
                .address("Roboad av. 7/278")
                .description("friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Andry")
                .lastName("Liner")
                .phone("")
                .email("andry@lev.com")
                .address("Roboad av. 7/278")
                .description("friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Lev")
                .phone("05-4714-5216-15")
                .email("john@lev.com")
                .address("Haifa")
                .description("friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Tony001")
                .lastName("Brook")
                .phone("04568")
                .email("tony@dvo.com")
                .address("Dali av. 79/147")
                .description("WrongPhone")
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
