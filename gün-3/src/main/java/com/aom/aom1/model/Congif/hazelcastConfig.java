package com.aom.aom1.model.Congif;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.apache.logging.log4j.status.StatusLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ObjectInputFilter;

@Configuration
public class hazelcastConfig {
    @Bean
    public HazelcastInstance hazelcastInstance() {

        // a) Boş bir ayar nesnesi oluşturuyoruz.
        ClientConfig clientConfig = new ClientConfig();

        // b) Bu ayara, Docker'da çalışan Hazelcast'in ağ adresini veriyoruz.
        clientConfig.getNetworkConfig().addAddress("localhost:5701");

        // c) Bu ayarları kullanarak Hazelcast istemcisini (köprüyü) başlatıyoruz.
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

}
