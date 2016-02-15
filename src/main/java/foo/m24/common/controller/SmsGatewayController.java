package foo.m24.common.controller;

import foo.m24.common.dao.MoLogsDAO;
import foo.m24.common.manager.SmsGatewayManager;
import foo.m24.common.model.MoLogs;
import foo.m24.common.model.MtLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


@Controller
@RequestMapping("/smsg")
public class SmsGatewayController {

    @Autowired
    SmsGatewayManager smsGatewayManager;
    @Autowired
    private MoLogsDAO moLogsDAO;

    @RequestMapping(value="/{moData}", method = RequestMethod.GET)
    public @ResponseBody
    SmsGatewayResponse getReply(@PathVariable String moData) {

        String moId = UUID.randomUUID().toString();
        String phoneNumber = "081111111111";
        String sms = moData;

        SmsGatewayRequest smsGatewayRequest = new SmsGatewayRequest();
        smsGatewayRequest.setMoId( moId );
        smsGatewayRequest.setMobileNo(phoneNumber);
        smsGatewayRequest.setSms(sms);
        smsGatewayRequest.setMoData(moId + " " + phoneNumber + " " + sms);

        SmsParser smsParser = new SmsParser();
        SmsComponent smsComponent = smsParser.parse( sms );


        MoLogs moLogs = new MoLogs();
        moLogs.setMoId(moId);
        moLogs.setMobileNo(phoneNumber);
        if( smsParser.isValid() ) {
            moLogs.setKeyword(smsComponent.getKeyword());
            moLogs.setName(smsComponent.getName());
            moLogs.setNric(smsComponent.getNric());
            moLogs.setAmount(smsComponent.getAmount());
        }
        moLogs.setDateTimeStamp(new Date());
        moLogs.setMoData(smsGatewayRequest.getMoData());

        MtLogs mtLogs = new MtLogs();
        mtLogs.setMtId(UUID.randomUUID().toString());
        mtLogs.setMoId(moLogs.getMoId());

        String replyStatus = "INVALID";
        if( smsParser.isValid() ) {
            replyStatus = "VALID";
            replyStatus = "DUPLICATE";
            boolean isDuplicate = true;

            Long n = moLogsDAO.count( smsComponent.getNric() );
            if( n==0 )
                isDuplicate = false;

            if( !isDuplicate ) {
                replyStatus = "";

                if( smsComponent.getAmount().compareTo(new BigDecimal("50")) <= 0 ) {
                    replyStatus = "SMALL";
                } else if (smsComponent.getAmount().compareTo(new BigDecimal("100")) <= 0) {
                    replyStatus = "MEDIUM";
                } else {
                    replyStatus = "LARGE";
                }
            }
        }
        mtLogs.setReply(replyStatus);

        smsGatewayManager.insertSms( moLogs, mtLogs );

        SmsGatewayResponse r = new SmsGatewayResponse();
        r.setReply(replyStatus);
        return r;

    }
}
