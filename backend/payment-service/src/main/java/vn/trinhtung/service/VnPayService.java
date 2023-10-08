package vn.trinhtung.service;

import vn.trinhtung.dto.VnPayIpnResponseDTO;

import java.math.BigDecimal;
import java.util.Map;

public interface VnPayService {
    String getPaymentUrl(String ipAddress, BigDecimal amount, Integer bookingId, String userId);

    Map<String, String> refund(Integer bookingId);

    Map<String, String> refundForCancellingBooking(Integer bookingId);

    VnPayIpnResponseDTO handleIpn(Map<String, String> params);

}
