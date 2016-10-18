package com.apress.springrecipes.board.service;

import com.apress.springrecipes.board.domain.Message;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MessageBoardServiceImpl implements MessageBoardService {

    private Map<Long, Message> messages = new LinkedHashMap<Long, Message>();
    private MutableAclService mutableAclService;

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    public List<Message> listMessages() {
        return new ArrayList<Message>(messages.values());
    }

    @Transactional
    @Secured("ROLE_USER")
    public synchronized void postMessage(Message message) {
        message.setId(System.currentTimeMillis());
        messages.put(message.getId(), message);
        ObjectIdentity oid =  new ObjectIdentityImpl(Message.class, message.getId());
        MutableAcl acl = mutableAclService.createAcl(oid);
        acl.insertAce(0, BasePermission.ADMINISTRATION,
                new PrincipalSid(message.getAuthor()), true);
        acl.insertAce(1, BasePermission.DELETE,
                new GrantedAuthoritySid("ROLE_ADMIN"), true);
        acl.insertAce(2, BasePermission.READ,
                new GrantedAuthoritySid("ROLE_USER"), true);
        mutableAclService.updateAcl(acl);
    }

    @Transactional
    //@Secured({"ROLE_ADMIN", "IP_LOCAL_HOST"})
    @Secured("ACL_MESSAGE_DELETE")
    public synchronized void deleteMessage(Message message) {
        messages.remove(message.getId());
        ObjectIdentity oid =
                new ObjectIdentityImpl(Message.class, message.getId());
        mutableAclService.deleteAcl(oid, false);

    }

    public Message findMessageById(Long messageId) {
        return messages.get(messageId);
    }
}

