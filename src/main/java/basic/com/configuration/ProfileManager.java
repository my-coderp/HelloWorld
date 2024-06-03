package basic.com.configuration;


import basic.com.api.v1.model.CategoryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileManager {

    @Value("${environment.name}")
    private String environmentName;

    public String getEnvironmentName() {

        return environmentName;
    }


}
