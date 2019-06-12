/* created by chenshi at 2019-02-02 */
package com.dytt.common.exception;

public class ControllerException extends ServiceException {

    private String message;

    /**
     * @param message
     */
    public ControllerException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
