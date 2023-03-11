package com.socmed.socmed.role;

import com.socmed.socmed.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @SequenceGenerator(sequenceName = "role_sequence", name = "role_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    private Long id;
    @NotEmpty
    private String name;


}
