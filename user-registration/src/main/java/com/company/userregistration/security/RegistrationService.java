package com.company.userregistration.security;

import com.company.userregistration.entity.User;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    // Use UnconstrainedDataManager to bypass data access check (https://docs.jmix.io/jmix/security/authorization.html#data-access-checks)
    // when saving entities in anonymous session.
    private final UnconstrainedDataManager unconstrainedDataManager;

    public RegistrationService(UnconstrainedDataManager unconstrainedDataManager) {
        this.unconstrainedDataManager = unconstrainedDataManager;
    }

    public void register(User user) {
        RoleAssignmentEntity roleAssignment = unconstrainedDataManager.create(RoleAssignmentEntity.class);
        roleAssignment.setUsername(user.getUsername());
        roleAssignment.setRoleCode(UiMinimalRole.CODE);
        roleAssignment.setRoleType(RoleAssignmentRoleType.RESOURCE);

        unconstrainedDataManager.save(user, roleAssignment);
    }
}