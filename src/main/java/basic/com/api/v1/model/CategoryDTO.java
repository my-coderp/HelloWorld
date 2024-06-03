package basic.com.api.v1.model;

import lombok.Data;

/**
 * API model - going to add swagger later
 * Using MapStruct to map between the API body Data Transfer Object (DTO), and the POJO
 * MapStruct uses @Annotations within interfaces - look at website
 */

@Data
public class CategoryDTO {

    private Long id;
    private String name;
    private String url;


}
