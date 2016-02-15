package foo.m24.common.dao.impl;

import foo.m24.common.dao.MtLogsDAO;
import foo.m24.common.model.MtLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MtLogsDAOImpl extends JdbcDaoSupport implements MtLogsDAO {
    @Autowired
    public MtLogsDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public Integer insert(MtLogs mtLogs) throws DataAccessException {
        String query = "INSERT INTO `mtlogs`" +
                " (MtId,MoId,Reply)" +
                " VALUES" +
                " (?,?,?)";
        return getJdbcTemplate().update(
                query,
                new Object[]{
                        mtLogs.getMtId(),
                        mtLogs.getMoId(),
                        mtLogs.getReply()
                }
        );
    }
}
