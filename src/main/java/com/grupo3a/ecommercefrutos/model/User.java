package com.grupo3a.ecommercefrutos.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer userId;
    private String userName;
    private String email;
}
