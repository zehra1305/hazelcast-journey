package com.example.banka_eft_kontrolu.controller;

import com.example.banka_eft_kontrolu.dto.EftDto;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/eft")
public class EftController {
    private HazelcastInstance hazelcastInstance;
    private final BigDecimal DAILY_LIMIT = new BigDecimal("100000.00");
    public EftController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }
    @PostMapping
    public ResponseEntity<String> post(@RequestBody EftDto eftDto) {
        // 1. Çekmecenin tipini String ve BigDecimal olarak düzelttik
        IMap<String, BigDecimal> limitCache = hazelcastInstance.getMap("Eft");

        // 2. RAM'den ilgili IBAN'ın mevcut harcamasını (BigDecimal olarak) çekiyoruz
        BigDecimal currentSpent = limitCache.get(eftDto.getIban());

        // Eğer bu hesap RAM'de henüz yoksa harcamasını 0.00 olarak başlatıyoruz
        if (currentSpent == null) {
            currentSpent = BigDecimal.ZERO;
        }

        // RAM'deki mevcut harcanan tutar ile arayüzden gelen yeni transfer tutarını topluyoruz
        BigDecimal totalSpentAfterTransfer = currentSpent.add(eftDto.getAmount());

        // Yeni toplam, 100.000 TL limitini aşıyor mu kontrol ediyoruz
        if (totalSpentAfterTransfer.compareTo(DAILY_LIMIT) > 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("REJECTED: Gunluk EFT limitiniz (100.000 TL) asilmistir!");
        }

        // Limit aşılmadıysa transferi onaylayıp güncel tutarı RAM'e geri yazıyoruz
        limitCache.put(eftDto.getIban(), totalSpentAfterTransfer);

        return ResponseEntity.ok("APPROVED: EFT isleminiz basariyla gerceklesmistir.");
    }


    }



