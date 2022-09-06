package com.mynt.shippingcalculator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

@RestController
public class ParcelController {

    @GetMapping("cost")
    public ShippingCost calculateCost(final Parcel parcel) {

        final Rule appliedRule = Stream.of(Rule.values()).filter(rule -> rule.test(parcel)).findFirst().get();
        return new ShippingCost(appliedRule, parcel);
    }

    @GetMapping("cost/{voucherId}")
    public ShippingCost calculateCost(final Parcel parcel, @PathVariable final String voucherId) {
        final String uri = "http://mynt-exam.mocklab.io/voucher/" + voucherId + "?key=apikey";
        final RestTemplate restTemplate = new RestTemplate();
        final Voucher voucher = restTemplate.getForObject(uri, Voucher.class);

        final LocalDate voucherExpiry = voucher.getExpiry().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final boolean expired = LocalDate.now().isAfter(voucherExpiry);
        if (expired) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Voucher expired");
        }

        final ShippingCost shippingCost = calculateCost(parcel);
        shippingCost.setVoucher(voucher);

        return shippingCost;
    }
}
