package httpserver;

import java.io.*;

public class FileEntry extends HTTPEntry {
    protected final File outputFile;

    public FileEntry(File entryFile) throws FileNotFoundException {
        this.outputFile = entryFile;
        if(!entryFile.exists()) {
            throw new FileNotFoundException();
        }
    }

    public HTTPResponse handleRequest(Request request) {
        HTTPResponse response = HTTPResponse.ok();

        StringBuilder outputBuilder = new StringBuilder();
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile));
            while ((line = bufferedReader.readLine()) != null) {
                outputBuilder.append(line);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        response.setResponseHTML(
                outputBuilder.toString()
        );

        return response;
    }
}
