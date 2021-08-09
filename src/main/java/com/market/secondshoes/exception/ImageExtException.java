package com.market.secondshoes.exception;

public class ImageExtException extends RuntimeException{
    public ImageExtException() {
        super();
    }

    public ImageExtException(String message) {
        super(message);
    }

    public ImageExtException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageExtException(Throwable cause) {
        super(cause);
    }
}
