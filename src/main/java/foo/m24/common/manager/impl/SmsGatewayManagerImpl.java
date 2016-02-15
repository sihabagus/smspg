package foo.m24.common.manager.impl;

import foo.m24.common.dao.MoLogsDAO;
import foo.m24.common.dao.MtLogsDAO;
import foo.m24.common.manager.SmsGatewayManager;
import foo.m24.common.model.MoLogs;
import foo.m24.common.model.MtLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SmsGatewayManagerImpl implements SmsGatewayManager {
    @Autowired
    private MoLogsDAO moLogsDAO;
    @Autowired
    private MtLogsDAO mtLogsDAO;

    @Override
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public Integer insertSms(MoLogs moLogs, MtLogs mtLogs) throws DataAccessException {

        moLogsDAO.insert( moLogs );
        mtLogsDAO.insert( mtLogs );

        return 1;
    }
}
