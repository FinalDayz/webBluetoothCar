package httpserver;

public class HTTPResponse {
    private final int responseCode;
    private String responseHTML = "";

    public HTTPResponse(int code) {
        this.responseCode = code;
    }

    public HTTPResponse(int code, String responseHTML) {
        this.responseCode = code;
        this.responseHTML = responseHTML;
    }

    public HTTPResponse setResponseHTML(String responseHTML) {
        this.responseHTML = responseHTML;
        return this;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseHTML() {
        return responseHTML;
    }

    public static HTTPResponse notFound() {
        return new HTTPResponse(404);
    }

    public static HTTPResponse ok() {
        return new HTTPResponse(200);
    }

    public static HTTPResponse internalServerError() {
        return new HTTPResponse(500);
    }
}
