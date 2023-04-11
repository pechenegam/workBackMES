package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Team extends BaseEntity {
    private String unitName;

    @ManyToOne
    @JoinColumn(name = "unit_head_id")
    private User unitHead;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
