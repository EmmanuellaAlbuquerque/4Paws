package app.com._paws.exceptions;

import java.net.URI;

public interface ErrorType {
    public String getCode();
    public URI getTypeUri();
}
