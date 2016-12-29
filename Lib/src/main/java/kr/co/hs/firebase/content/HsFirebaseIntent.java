package kr.co.hs.firebase.content;


import kr.co.hs.content.HsIntent;

/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseIntent extends HsIntent {
    public static final String ACTION_TOKEN_REFRESH = "ACTION_TOKEN_REFRESH";
    public static final String EXTRA_TOKEN = "Token";

    public static final String ACTION_RECEIVED_MESSAGE = "ACTION_RECEIVED_MESSAGE";
    public static final String EXTRA_REMOTE_MESSAGE = "EXTRA_REMOTE_MESSAGE";
    public static final String ACTION_SENT_MESSAGE = "ACTION_SENT_MESSAGE";
    public static final String EXTRA_SENT = "EXTRA_SENT";
    public static final String ACTION_DELETED_MESSAGE = "ACTION_DELETED_MESSAGE";
    public static final String ACTION_SENT_ERROR = "ACTION_SENT_ERROR";
    public static final String EXTRA_SENT_ERROR = "EXTRA_SENT_ERROR";
}
