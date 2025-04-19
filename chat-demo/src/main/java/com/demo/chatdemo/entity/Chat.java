package com.demo.chatdemo.entity;

import com.demo.chatdemo.constant.MessageState;
import com.demo.chatdemo.constant.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableWebSecurity
@Table(name = "Chat")
@Entity
@Builder

public class Chat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER)
    @OrderBy("createdAt DESC")
    private List<Message>messages;

    @Transient
    public String getChatName(final String senderId){
        if (recipient.getId().equals(senderId))
        {
            return sender.getFirstName()+" "+sender.getLastName();
        }
        return recipient.getFirstName()+" "+recipient.getLastName();
    }
    @Transient
    public long getUnreadMessage(final String senderId){
        return messages.stream()
                .filter(message -> message.getReceiverId().equals(senderId))
                .filter(m->m.getState()==MessageState.SENT)
                .count();

    }

    @Transient
    public String getLastMessage(){
        if(messages!=null &&!messages.isEmpty())
        {
            if (messages.get(0).getType()!=MessageType.TEXT)
            {
                return "Attachment";
            }

        }
        return null;
    }

    @Transient
    public LocalDateTime getLastMessageTime(){
        if(messages!=null &&!messages.isEmpty())
        {
            return messages.get(0).getCreatedAt();
        }
        return null;
    }
}
