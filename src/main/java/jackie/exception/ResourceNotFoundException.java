package jackie.exception;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String resource) {
        super(404, resource + "not found");
    }
}

