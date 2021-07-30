package com.test.mark.zhang.test.other.project.cache;

import com.test.mark.zhang.test.other.project.security.SecurityZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by mark
 * @Classname SecurityZoneController
 * @Description TODO
 * @Date 2021/7/20 4:09 下午
 */
@RestController
@RequestMapping("/securityZone/v1")
public class SecurityZoneController {
    @Autowired
    private SecurityZoneService securityService;

    private static List<SecurityZone> data = new ArrayList<>();
    private SecurityZoneCache cache = SecurityZoneCache.getInstance();

    @PostConstruct
    public void init() {
        generateData();
        putCache();
    }

    @GetMapping("/getAll")
    public String getAll() {
        //putCache();
//        System.out.println(cache);
//        System.out.println(data.size());
        System.out.println(securityService.getAll());
        securityService.count();
        //securityService.getAll();
        return "success";
    }

    @PostMapping("put")
    public String put() {
        SecurityZone zone1 = new SecurityZone("zoneput","zoneput",null,100001L,"tag1","1001x","");
        data.add(zone1);

        //get
        System.out.println(cache);
        cache.clear();
        putCache();
        System.out.println(cache);
        return "success";
    }

    @PostMapping("putNew")
    @CacheUpdate("securityZone")
    public String putNew() {
        SecurityZone zone1 = new SecurityZone("zoneNew","zoneNew",null,100001L,"tag1","1001x","");
        data.add(zone1);

        return "success";
    }

    public void putCache() {
        List<SecurityZone> securityZones = data;
        for (SecurityZone securityZone : securityZones) {
            cache.putSecurityZone(securityZone.getId(), securityZone);
        }
    }

    public List<SecurityZone> generateData() {

        SecurityZone zone1 = new SecurityZone("zone1","zone1",null,100001L,"tag1","1001x","");
        SecurityZone zone2 = new SecurityZone("zone2","zone2",null,100002L,"tag2","2002x","");
        SecurityZone zone3 = new SecurityZone("zone3","zone3",null,100003L,"tag3","3003x","");
        SecurityZone zone4 = new SecurityZone("zone4","zone4",null,100004L,"tag4","4004x","");
        SecurityZone zone5 = new SecurityZone("zone5","zone5",null,100005L,"tag5","5005x","");
        SecurityZone zone6 = new SecurityZone("zone6","zone6",null,100006L,"tag6","6006x","");
        SecurityZone zone7 = new SecurityZone("zone7","zone7",null,100007L,"tag7","7007x","");
        data.add(zone1);
        data.add(zone2);
        data.add(zone3);
        data.add(zone4);
        data.add(zone5);
        data.add(zone6);
        data.add(zone7);
        return data;
    }

}
