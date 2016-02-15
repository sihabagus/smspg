package foo.m24.common.dao.impl;

import foo.m24.common.dao.MoLogsDAO;
import foo.m24.common.model.MoLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MoLogsDAOImpl extends JdbcDaoSupport implements MoLogsDAO {
    @Autowired
    public MoLogsDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public Integer insert(MoLogs moLogs) throws DataAccessException {
        String query = "INSERT INTO `mologs`" +
                " (MoId,MobileNo,Keyword,Name,NRIC,Amount,DateTimeStamp,MoData)" +
                " VALUES" +
                " (?,?,?,?,?,?,?,?)";
        return getJdbcTemplate().update(
                query,
                new Object[]{
                        moLogs.getMoId(),
                        moLogs.getMobileNo(),
                        moLogs.getKeyword(),
                        moLogs.getName(),
                        moLogs.getNric(),
                        moLogs.getAmount(),
                        moLogs.getDateTimeStamp(),
                        moLogs.getMoData()
                }
        );
    }

    @Override
    public Long count(String nric) throws DataAccessException {
        nric = nric.substring(1, nric.length()-1);

        String query = "SELECT COUNT(*) FROM `mologs` where nric like '%"+ nric +"%'";
        Long result = getJdbcTemplate().queryForLong(query);
        return result;
    }
}
