package com.example.day_2;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin("*")
@RestController
public class Controller {
private final HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance();
@PostMapping("/koy")
    public String  verikoy(@RequestBody  Dto dto ) {
    IMap<String, Dto> map = hazelcast.getMap("zehra-depo");
    map.put(dto.getEmail(),dto);
    System.out.println("başarıyla kaydedildi "  + dto.getAdi());
    return dto.getAdi();
}

@GetMapping("/getir")
    public Collection<Dto> getir() {
    IMap<String, Dto> map = hazelcast.getMap("zehra-depo");
    Collection<Dto> dtos = map.values();
    return dtos;
}


}
