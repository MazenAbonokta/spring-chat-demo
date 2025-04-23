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
/**
 * Entity representing a chat between two users.
 * This class manages chat details including participants and messages.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableWebSecurity
@Table(name = "Chat")
@Entity
@Builder

public  class Chat extends BaseEntity {
    /**
     * Unique identifier for the chat
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER)
    @OrderBy("createdAt DESC")
    private List<Message>messages;
    /**
     * Gets the name of the chat participant based on the provided sender ID.
     * If the recipient's ID matches the sender ID, returns the sender's full name.
     * Otherwise, returns the recipient's full name.
     *
     * @param senderId The ID of the sender to determine which name to display
     * @return A string containing the full name (firstName + lastName) of the chat participant
     */

    @Transient
    public String getChatName(final String senderId){
        if (receiver.getId().equals(senderId))
        {
            return sender.getFirstName()+" "+sender.getLastName();
        }
        return receiver.getFirstName()+" "+receiver.getLastName();
    }
    /**
     * Counts the number of unread messages for a specific user in this chat.
     * A message is considered unread if:
     * - The user is the receiver of the message
     * - The message state is SENT (not yet read)
     *
     * @param senderId The ID of the user to check unread messages for
     * @return The count of unread messages
     */

    @Transient
    public long getUnreadMessage(final String senderId){
        return messages.stream()
                .filter(message -> message.getReceiverId().equals(senderId))
                .filter(m->m.getState()==MessageState.SENT)
                .count();

    }
    /**
     * Retrieves the content of the last message in the chat.
     * If the last message is not of type TEXT, returns "Attachment".
     * If there are no messages, returns null.
     *
     * @return String representation of the last message or null if no messages exist
     */

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
    /**
     * Gets the timestamp of the last message in the chat.
     * If there are no messages, returns null.
     *
     * @return LocalDateTime of the last message or null if no messages exist
     */

    @Transient
    public LocalDateTime getLastMessageTime(){
        if(messages!=null &&!messages.isEmpty())
        {
            return messages.get(0).getCreatedAt();
        }
        return null;
    }
}
