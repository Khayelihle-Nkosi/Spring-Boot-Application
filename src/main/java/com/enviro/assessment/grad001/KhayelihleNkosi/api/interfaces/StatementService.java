package com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public interface StatementService {
    void downloadStatement(Long productId, Date startDate, Date endDate, Writer writer) throws IOException;
}
