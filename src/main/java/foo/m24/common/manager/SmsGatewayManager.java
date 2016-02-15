package foo.m24.common.manager;

import foo.m24.common.model.MoLogs;
import foo.m24.common.model.MtLogs;
import org.springframework.dao.DataAccessException;


public interface SmsGatewayManager {
    Integer insertSms(MoLogs moLogs, MtLogs mtLogs) throws DataAccessException;
}
