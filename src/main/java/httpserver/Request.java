package httpserver;

import java.io.UnsupportedEncodingException;

public class Request {
    private final ParametersSet URLParameters;
    private final ParametersSet bodyParameters;
    private final RequestType requestType;

    public Request(String URLContent, String bodyContent, RequestType requestType) {
        this.requestType = requestType;
        URLParameters = new ParametersSet();
        bodyParameters = new ParametersSet();

        try {
            bodyParameters.parseParameters(bodyContent);
            URLParameters.parseParameters(URLContent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public ParametersSet getURLParameters() {
        return URLParameters;
    }

    public ParametersSet getBodyParameters() {
        return bodyParameters;
    }
}
