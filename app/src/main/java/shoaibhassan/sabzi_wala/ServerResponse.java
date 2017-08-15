package shoaibhassan.sabzi_wala;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SHOAIB HASSAN on 8/13/2017.
 */

public class ServerResponse {

    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    String getMessage()
    {
        return message;
    }

    boolean getSuccess()
    {
        return success;
    }

}
