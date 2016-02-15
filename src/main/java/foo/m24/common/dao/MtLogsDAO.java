package foo.m24.common.dao;


import foo.m24.common.model.MtLogs;
import org.springframework.dao.DataAccessException;

public interface MtLogsDAO {
    Integer insert(MtLogs mtLogs) throws DataAccessException;
}
