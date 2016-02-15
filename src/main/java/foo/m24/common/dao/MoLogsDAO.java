package foo.m24.common.dao;


import foo.m24.common.model.MoLogs;
import org.springframework.dao.DataAccessException;

public interface MoLogsDAO {
    Integer insert(MoLogs moLogs) throws DataAccessException;
    Long count(String nric) throws DataAccessException;
}
