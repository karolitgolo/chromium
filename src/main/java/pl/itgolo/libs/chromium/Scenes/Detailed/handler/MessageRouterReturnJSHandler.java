// Copyright (c) 2014 The Chromium Embedded Framework Authors. All rights
// reserved. Use of this source code is governed by a BSD-style license that
// can be found in the LICENSE file.

package pl.itgolo.libs.chromium.Scenes.Detailed.handler;

import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.callback.CefQueryCallback;
import org.cef.handler.CefMessageRouterHandlerAdapter;
import org.codehaus.plexus.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Message router return js handler.
 */
public class MessageRouterReturnJSHandler extends CefMessageRouterHandlerAdapter {

    private final CefClient cefClient;

    /**
     * Instantiates a new Message router return js handler.
     *
     * @param client the client
     */
    public MessageRouterReturnJSHandler(final CefClient client) {
        cefClient = client;
    }

    /**
     * The constant returnsJS.
     */
    public static Map<Integer, String> returnsJS = new ConcurrentHashMap<>();

    @Override
    public boolean onQuery(CefBrowser browser, long query_id, String request, boolean persistent, CefQueryCallback callback) {
        if (request.startsWith("jsReturn:")) {
            String domEncode = request.replace("jsReturn:", "");
            byte[] valueDecoded = Base64.decodeBase64(domEncode.getBytes());
            try {
                String returnJS = new String(valueDecoded, "UTF-8");
                returnsJS.put(browser.getIdentifier(), returnJS);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
