package exceptions;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Custom runtime exception for framework errors with automatic logging
 */
public class FrameworkException extends RuntimeException {
    private static final Logger logger = LogManager.getLogger(FrameworkException.class);
    
    public FrameworkException(String message) {
        super(message);
        logger.error(message);
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, cause);
    }
}