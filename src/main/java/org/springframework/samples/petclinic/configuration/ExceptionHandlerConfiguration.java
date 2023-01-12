package org.springframework.samples.petclinic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * This advice is necessary because MockMvc is not a real servlet environment, therefore it does not redirect error
 * responses to [ErrorController], which produces validation response. So we need to fake it in tests.
 * It's not ideal, but at least we can use classic MockMvc tests for testing error response + document it.
 */
@ControllerAdvice
public class ExceptionHandlerConfiguration 
{
	@Autowired
	private BasicErrorController errorController;
    // add any exceptions/validations/binding problems

   @ExceptionHandler(Exception.class)
   public String defaultErrorHandler(HttpServletRequest request,  Exception ex)  {
        request.setAttribute("javax.servlet.error.request_uri", request.getPathInfo());
        request.setAttribute("javax.servlet.error.status_code", 400);
        request.setAttribute("exception", ex);
        return "exception";
   }
   

    @ExceptionHandler(ResourceNotFoundException.class)
    public String ResourceNotFoundExceptionHandler(HttpServletRequest request,  ResourceNotFoundException ex){
        request.setAttribute("javax.servlet.error.request_uri", request.getPathInfo());
        request.setAttribute("javax.servlet.error.status_code", 400);
        request.setAttribute("exception", ex);
        return "exception";
    }


    // @ExceptionHandler(NoHandlerFoundException.class)
    // public String NoHandlerFoundExceptionHandler(HttpServletRequest request,  ResourceNotFoundException ex){
    //     // Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    //     // if (status != null) {
    //     //     Integer statusCode = Integer.valueOf(status.toString());
        
    //     //     if(statusCode == HttpStatus.NOT_FOUND.value()) {
    //     //         return "error-404";
    //     //     }
    //     //     else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
    //     //         return "error-500";
    //     //     }
    //     // }
    //     return "error-404";
    // }
}