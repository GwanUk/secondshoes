package com.market.secondshoes.exception;

public class ImageExceededException extends RuntimeException{
    public ImageExceededException() {
        super();
    }

    public ImageExceededException(String message) {
        super(message);
    }

    public ImageExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageExceededException(Throwable cause) {
        super(cause);
    }
}
