package io.mosip.curp.helper;


import io.mosip.kernel.core.logger.spi.Logger;
import io.mosip.kernel.logger.logback.factory.Logfactory;

public final class CurpManagerLogger {
    private CurpManagerLogger(){
    }

    public static Logger getLogger(Class<?> clazz) {
        return Logfactory.getSlf4jLogger(clazz);
    }
}
