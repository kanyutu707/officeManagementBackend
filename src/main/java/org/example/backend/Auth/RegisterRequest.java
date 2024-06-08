package org.example.backend.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.Entity.Company;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String employeeNumber;
    private String image;
    private String position;
    private String status;
    private Company company;
    private Integer company_id;

    public Integer getCompanyId() {
        return company_id;
    }
}
