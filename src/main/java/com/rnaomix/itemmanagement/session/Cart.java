package com.rnaomix.itemmanagement.session;

import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
import lombok.Data;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

// Ref: https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/SessionManagement.html#session-management-how-to-use-sessionscope
@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {

    private Integer itemId;

    private long amount;

}
