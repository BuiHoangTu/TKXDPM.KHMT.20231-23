package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeliveryInfo {
    private String receiver;
    private String phoneNumber;
    private String email;
    private String cityAddress;
    private String detailedAddress;
    private String instruction;
}
