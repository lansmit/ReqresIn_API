package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserModel {
    private String name;
    private String job;
    private String year;
}
