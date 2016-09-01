package com.rmuhamed.demoapp.api;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import app.lanacion.activity.api.deserializadores.DeserializadorDeAcumuladoTema;
import app.lanacion.activity.log.CustomLog;
import app.lanacion.activity.model.Nota;
import app.lanacion.activity.util.NetworkResponse.StreamToStringConverter;

/**
 * Created by rmuhamed on 13/05/2015.
 */
public class UltimasNoticiasRequest extends JsonRequest<List<Nota>> {
    public static final String LOG_TAG = UltimasNoticiasRequest.class.getSimpleName();
    private Context contexto;
    private Response.Listener<List<Nota>> listener;

    public UltimasNoticiasRequest(Context contexto, String url, String requestBody, Response.Listener<List<Nota>> listener, Response.ErrorListener errorListener) {
        super(url, requestBody, listener, errorListener);

        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        this.contexto = contexto;
        this.listener = listener;
    }

    @Override
    protected Response<List<Nota>> parseNetworkResponse(NetworkResponse networkResponse) {
        CustomLog.i(LOG_TAG, "Request de Ultimas noticias completo, parseando...");

        Response<List<Nota>> result = null;

        try {
            String UTF8String = StreamToStringConverter.convert(networkResponse.data);
            try {
                //La parte de persona será ignorada
                DeserializadorDeAcumuladoTema deserializador = new DeserializadorDeAcumuladoTema();
                deserializador.setContext(this.contexto);
                List<Nota> notaList = deserializador.parseAcumulado(UTF8String);

                CustomLog.i(LOG_TAG, "Terminó el pareso, enviando el callback para renderizar...");
                result = Response.success(notaList, HttpHeaderParser.parseCacheHeaders(networkResponse));
                //this.deliverResponse(notaList);

            } catch (Exception e) {
                CustomLog.e(LOG_TAG, e.getMessage());
                result = Response.error(new VolleyError());
            }

        } catch (UnsupportedEncodingException e) {
            CustomLog.e(LOG_TAG, e.getMessage());
            result = Response.error(new VolleyError());
        } catch (IOException e) {
            CustomLog.e(LOG_TAG, e.getMessage());
            result = Response.error(new VolleyError());
        } catch (Exception e) {
            CustomLog.e(LOG_TAG, e.getMessage());
            result = Response.error(new VolleyError());
        }

        return result;
    }

    @Override
    protected void deliverResponse(List<Nota> response) {
        if (this.listener != null) {
            this.listener.onResponse(response);
        }
    }

}