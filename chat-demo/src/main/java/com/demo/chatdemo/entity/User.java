package com.demo.chatdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "users")
public class User extends BaseEntity{
private static final int LAST_ACTIVE_INTERVAL = 5;
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public LocalDateTime lastSeen;

    @OneToMany(mappedBy = "sender")
    private List<Chat> chatsAsSender;
    @OneToMany(mappedBy = "receiver")
    private List<Chat> chatsAsReceiver;

    @Transient
    public boolean isOnline(){
        return lastSeen != null&& LocalDateTime.now().isAfter(lastSeen.plusMinutes(LAST_ACTIVE_INTERVAL));
    }


}
