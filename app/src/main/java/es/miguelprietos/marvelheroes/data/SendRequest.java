package es.miguelprietos.marvelheroes.data;

/**
 * Created by miguelprieto on 7/2/17.
 */

import java.util.Calendar;
import java.util.TimeZone;

import es.miguelprietos.marvelheroes.utils.Constants;
import es.miguelprietos.marvelheroes.utils.Utils;

public class SendRequest {

    private static final String apiKey = Constants.API_KEY;
    private static final String privateKey= Constants.PRIVATE_KEY;
    private static final long rTime = 1000L;

    private long timeStamp;
    private static final String publicKey = apiKey;
    private String hashSignature;
    private static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));


    public String getHashSignature() {
        return hashSignature;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    private SendRequest(){
        this.timeStamp = calendar.getTimeInMillis();
        this.hashSignature = Utils.md5(String.valueOf(this.timeStamp) + privateKey + publicKey);
    }

    /**
     * Returnes a new instance of a send request.
     * @return
     */
    public static SendRequest create(){
        return  new SendRequest();
    }
}
