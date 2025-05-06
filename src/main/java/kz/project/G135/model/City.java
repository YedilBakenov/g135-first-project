package kz.project.G135.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class City {

    private long id;
    private String cityName;
    private String code;
    private String ticker;

}
