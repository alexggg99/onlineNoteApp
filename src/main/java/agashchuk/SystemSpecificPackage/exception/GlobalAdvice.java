package agashchuk.SystemSpecificPackage.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalAdvice {

    private static final Logger logger = Logger.getLogger(GlobalAdvice.class.getName());

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleNFException(){
        logger.log(Level.WARNING,"IOException handler executed. User not found");
        //returning 404 error code
        return new ResponseEntity<Object>("User not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserBlockedFound.class)
    public ResponseEntity<Object> handleBException(){
        logger.log(Level.WARNING,"IOException handler executed. User blocked");
        //returning 423 error code
        return new ResponseEntity<Object>("User is blocked", new HttpHeaders(), HttpStatus.LOCKED);
    }

    @ExceptionHandler(UserNotmatchFound.class)
    public ResponseEntity<Object> handleMException(){
        logger.log(Level.WARNING,"IOException handler executed. User not match");
        //returning 401 error code
        return new ResponseEntity<Object>("User is blocked", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
