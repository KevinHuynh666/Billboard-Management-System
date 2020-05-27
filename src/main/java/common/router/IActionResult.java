package common.router;

import java.io.Serializable;

/**
 * This class is the IActionResult object.
 *
 * @author Perdana Bailey
 * @author Jamie Martin
 */
public class IActionResult implements Serializable {
    /**
     * The variables of the object billboard.
     */
    public Status status;
    public String message;
    public Boolean error;
    public Object body;

    /**
     * Constructor for the Response object.
     *
     * @param status: An enum that determines if the request was successful.
     */
    public IActionResult(Status status) {
        this.status = status;
        isError(status);
    }

    /**
     * Constructor for the Response object.
     *
     * @param status:  An enum that determines if the request was successful.
     * @param message: the message given.
     */
    public IActionResult(Status status, String message) {
        this.status = status;
        isError(status);
        this.message = message;
    }

    /**
     * Constructor for the Response object.
     *
     * @param status: An enum that determines if the request was successful.
     * @param body: the body Object of the return.
     */
    public IActionResult(Status status, Object body) {
        this.status = status;
        isError(status);
        this.body = body;
    }

    /**
     * Constructor for the Response object.
     *
     * @param status: An enum that determines if the request was successful.
     * @param message: the message given.
     * @param body:   This acts as the result of the request.
     */
    public IActionResult(Status status, String message, Object body) {
        this.status = status;
        isError(status);
        this.message = message;
        this.body = body;
    }

    public void isError(Status status) {
        switch (status) {
            case SUCCESS:
                this.error = false;
                break;
            default:
                this.error = true;
        }
    }
}
