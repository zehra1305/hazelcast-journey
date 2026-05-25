package com.example.gun_1;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class Controller {
    private final HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance();

    @PostMapping("/koy")
    public String kelimeKoy(@RequestBody Dto dto){
        IMap<String, String> map = hazelcast.getMap("zehra");
        map.put("sonkelime", dto.getKelime());
        System.out.println("kelime alındı " + dto.getKelime());
        return dto.getKelime();
    }
   @GetMapping("/getir")
    public String kelimeAl(){
        IMap<String, String> map = hazelcast.getMap("zehra");
       String donen= map.get("sonkelime");
       System.out.println("son kelime " +  donen);
       return donen;
    }


}
