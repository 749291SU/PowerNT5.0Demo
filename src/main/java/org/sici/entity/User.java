package org.sici.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @projectName: PowerNT5.0Demo
 * @package: org.sici.entity
 * @className: User
 * @author: 749291
 * @description: TODO
 * @date: 6/7/2024 10:19 AM
 * @version: 1.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private BigDecimal salary;
}
