package com.richard.myactuator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
 
@Component
public class GetUserHealthIndicator implements HealthIndicator {
 
	@Autowired
	RestTemplate restTemplate;
	
    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down().withDetail("error msg", errorCode).build();
        }else{
        	return Health.up().build();
        }
    }

	private int check() {
		String url = "http://WEB-PROVIDER/user/get/2";
		Object object = this.restTemplate.getForObject(url, Object.class);
		if(object==null){
			return 500;
		}else{
			return 0;
		}
	}
 
}