/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package com.google.code.camel.smtp;

import static org.jboss.netty.buffer.ChannelBuffers.copiedBuffer;

import java.util.ArrayList;
import java.util.List;

import org.apache.james.protocols.smtp.SMTPResponse;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * {@link OneToOneEncoder} which encodes {@link SMTPResponse} 
 *
 */
public class SMTPResponseEncoder extends OneToOneEncoder {

    private String charset = "US_ASCII";

    @Override
    protected Object encode(ChannelHandlerContext arg0, Channel arg1, Object obj) throws Exception {
        StringBuilder builder = new StringBuilder();
        SMTPResponse response = (SMTPResponse) obj;
        List<String> lines = getResponse(response);
        for (int i = 0; i < lines.size(); i++) {
            builder.append(lines.get(i));
            if (i < lines.size()) {
                builder.append("\r\n");
            }
        }
        return copiedBuffer(builder.toString(), charset);
    }

    private List<String> getResponse(SMTPResponse response) {
        List<String> responseList = new ArrayList<String>();

        for (int k = 0; k < response.getLines().size(); k++) {
            StringBuffer respBuff = new StringBuffer(256);
            respBuff.append(response.getRetCode());
            if (k == response.getLines().size() - 1) {
                respBuff.append(" ");
                respBuff.append(response.getLines().get(k));

            } else {
                respBuff.append("-");
                respBuff.append(response.getLines().get(k));

            }
            responseList.add(respBuff.toString());
        }

        return responseList;
    }

}
