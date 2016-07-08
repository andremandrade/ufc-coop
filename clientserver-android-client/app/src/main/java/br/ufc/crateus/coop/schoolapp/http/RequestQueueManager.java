package br.ufc.crateus.coop.schoolapp.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import br.ufc.crateus.coop.schoolapp.R;

/**
 * Created by andremeireles on 29/06/16.
 */
public class RequestQueueManager {

    private static RequestQueueManager instance;
    private static String clientCertPassword = "virtus";
    private RequestQueue requestQueue;
    private static Context appContext;
    private static SSLContext sslContext;

    private RequestQueueManager(Context context) {
        appContext = context;
        requestQueue = getRequestQueue();
        //configSSLUsingClientCertificate();
    }

    public static synchronized RequestQueueManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueManager(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(appContext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }


    protected static final String TAG = "NukeSSLCerts";

    public static void configSSLUsingClientCertificate() {

        try {
            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = null;

            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(getKeyStoreWithServerCert());

            KeyManager[] keyManagers = getKeyManagersWithClientCert();

            sslContext = SSLContext.getInstance("SSL");

            sslContext.init(keyManagers, tmf.getTrustManagers(), null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            // Required if you are using self-signed certificate
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception e) {
            Log.e("SSL Config", e.getMessage(), e);
        }

    }

    private static KeyStore getKeyStoreWithServerCert() throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        InputStream crtInput = null;
        try {
            // Load CAs from an InputStream
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            crtInput = appContext.getResources().openRawResource(R.raw.server);
            Certificate cert;

            cert = cf.generateCertificate(crtInput);
            Log.i("Server Cert DN", ((X509Certificate) cert).getSubjectDN().toString());

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", cert);

            return keyStore;
        } finally {
            try {
                if (crtInput != null)
                    crtInput.close();
            } catch (IOException e) {
                Log.e("Close Cert Input", e.getMessage(), e);
            }
        }
    }

    private static KeyManager[] getKeyManagersWithClientCert() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        KeyStore clientKeyStore = KeyStore.getInstance("PKCS12");
        InputStream cert = appContext.getResources().openRawResource(R.raw.client);
        clientKeyStore.load(cert, clientCertPassword.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("X509");
        kmf.init(clientKeyStore, clientCertPassword.toCharArray());
        return kmf.getKeyManagers();
    }
}
