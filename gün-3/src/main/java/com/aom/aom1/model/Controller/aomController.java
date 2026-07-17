package com.aom.aom1.model.Controller;

import com.aom.aom1.model.Model.usermodel;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class aomController {
    private final HazelcastInstance hazelcastInstance;
    public aomController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostConstruct
    public void init() {
        IMap<String, usermodel> userCache = hazelcastInstance.getMap("abone-listesi");
        userCache.put("5551234567", new usermodel("5551234567", true));
    }
    @GetMapping("/status")
    public ResponseEntity<String> checkStatus(@RequestParam String msisdn) {
        IMap<String, usermodel> userCache = hazelcastInstance.getMap("abone-listesi");

        // RAM'den numarayı sorguluyoruz
        usermodel user = userCache.get(msisdn);

        if (user != null && user.isAvailable()) {
            return ResponseEntity.ok("AUTHORIZED"); // Numara var ve aktifse 200 OK dön
        } else {
            return ResponseEntity.status(403).body("REJECTED"); // Yoksa veya pasifse 403 dön
        }
    }


}
