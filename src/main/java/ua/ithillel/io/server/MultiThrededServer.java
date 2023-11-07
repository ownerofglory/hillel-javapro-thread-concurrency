package ua.ithillel.io.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class MultiThrededServer implements AutoCloseable {
    private final ServerSocket serverSocket;
    private boolean isClosed;

    public MultiThrededServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() {

        while (!isClosed) {
            try {
                final Socket connection = this.serverSocket.accept();

                Runnable r = () -> {
                    try (
                            connection;
//                            final InputStream inputStream = connection.getInputStream();
                            final OutputStream outputStream = connection.getOutputStream();

                            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));


//                            final InputStream fileIn = getClass().getClassLoader().getResourceAsStream("index.html");
//                            Reader fileReader = new InputStreamReader(fileIn);
                    ) {

//                        final String siteText = readTextFile(fileReader);

                        // success response
                        final StringBuffer buffer = new StringBuffer();
                        buffer.append("HTTP/1.1 200 OK\r\n");
                        buffer.append("Content-Type: text/html\r\n");
                        buffer.append("\r\n");
//                        buffer.append(siteText);
                        buffer.append("""
                                <!doctype html>
                                <html lang="en">
                                <head>
                                    <meta charset="UTF-8">
                                    <meta name="viewport"
                                          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                                    <meta http-equiv="X-UA-Compatible" content="ie=edge">
                                    <title>Document</title>
                                </head>
                                <body>
                                    <h1>Hello from Java Server</h1>
                                    <p>Non-blocking multhithreaded server</p>
                                </body>
                                </html>
                                """);

                        writer.println(buffer.toString());
                        writer.flush();


                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                };

                new Thread(r).start();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }
    }

    private String readTextFile(Reader fileReader) {
        final BufferedReader bufferedReader = new BufferedReader(fileReader);

        final Optional<String> reduced = bufferedReader.lines()
                .reduce((acc, cur) -> acc + cur + "\n");

        return reduced.orElse("");
    }

    @Override
    public void close() throws Exception {
        if (serverSocket != null) {
            serverSocket.close();
        }
        isClosed = true;
    }
}
