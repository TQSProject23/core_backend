package tqs.loadconnect.core_backend.models;

import lombok.NonNull;
import tqs.loadconnect.core_backend.Enums.RoleEnum;

public class UserPP extends User {

    @NonNull    // lombok or springframework?
    private final RoleEnum role = RoleEnum.PICKUP_POINT;
}
