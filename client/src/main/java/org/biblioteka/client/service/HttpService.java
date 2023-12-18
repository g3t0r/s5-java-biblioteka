package org.biblioteka.client.service;


import com.google.gson.Gson;
import okhttp3.*;
import org.biblioteka.shared.model.ErrorDTO;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class HttpService {

    private final Gson gson = new Gson();
    private final OkHttpClient client = new OkHttpClient();

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public <RSP> void get(
            String url,
            Class<RSP> responseType,
            Consumer<RSP> onSuccess,
            Consumer<ErrorDTO> onError) {


        executorService.submit(() -> {

            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() > 299) {
                    onError.accept(gson.fromJson(response.body().string(), ErrorDTO.class));
                } else {
                    onSuccess.accept(gson.fromJson(response.body().string(), responseType));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public <REQ, RSP> void post(String url,
                                REQ body,
                                Class<RSP> responseType,
                                Consumer<RSP> onSuccess,
                                Consumer<ErrorDTO> onError) {


        executorService.submit(() -> {

            String jsonBody = gson.toJson(body);
            Request request = new Request.Builder()
                    .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() > 299) {
                    onError.accept(gson.fromJson(response.body().string(), ErrorDTO.class));
                } else {
                    onSuccess.accept(gson.fromJson(response.body().string(), responseType));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public <RSP, REQ> Future<RSP> post(String url, REQ body, Class<RSP> responseType) {

        return executorService.submit(() -> {

            String jsonBody = gson.toJson(body);

            Request request = new Request.Builder()
                    .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                    .url(url)
                    .build();


            try (Response response = client.newCall(request).execute()) {
                if (response.code() > 299) {
                    return null;
                }
                return gson.fromJson(response.body().string(), responseType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public <RSP> Future<RSP> get(String url, Class<RSP> responseType) {
        return executorService.submit(() -> {

            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return gson.fromJson(response.body().string(), responseType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static HttpService getInstance() {
        if (instance == null) {
            instance = new HttpService();
        }
        return instance;
    }

    private static HttpService instance;

    private HttpService() {
    }
}
