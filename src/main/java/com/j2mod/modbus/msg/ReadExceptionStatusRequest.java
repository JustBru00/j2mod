/*
 * Copyright 2002-2016 jamod & j2mod development teams
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.j2mod.modbus.msg;

import com.j2mod.modbus.Modbus;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Class implementing a <tt>Read Exception Status</tt> request.
 *
 * @author Julie Haugh (jfh@ghgande.com)
 * @version jamod-1.2rc1-ghpc
 *
 * @author jfhaugh (jfh@ghgande.com)
 * @version @version@ (@date@)
 */
public final class ReadExceptionStatusRequest extends ModbusRequest {

    /**
     * Constructs a new <tt>Read Exception Status</tt> request
     * instance.
     */
    public ReadExceptionStatusRequest() {
        super();

        setFunctionCode(Modbus.READ_EXCEPTION_STATUS);

		/*
		 * There is no additional data in this request.
		 */
        setDataLength(0);
    }

    /**
     * createResponse -- create an empty response for this request.
     */
    public ModbusResponse getResponse() {
        ReadExceptionStatusResponse response;

        response = new ReadExceptionStatusResponse();

		/*
         * Copy any header data from the request.
		 */
        response.setHeadless(isHeadless());
        if (!isHeadless()) {
            response.setTransactionID(getTransactionID());
            response.setProtocolID(getProtocolID());
        }

		/*
         * Copy the unit ID and function code.
		 */
        response.setUnitID(getUnitID());
        response.setFunctionCode(getFunctionCode());

        return response;
    }

    /**
     * The ModbusCoupler doesn't have a means of reporting the serial
     * device exception status.
     */
    public ModbusResponse createResponse() {
        return createExceptionResponse(Modbus.ILLEGAL_FUNCTION_EXCEPTION);
    }

    /**
     * writeData -- output this Modbus message to dout.
     */
    public void writeData(DataOutput dout) throws IOException {
        dout.write(getMessage());
    }

    /**
     * readData -- dummy function.  There is no data with the request.
     */
    public void readData(DataInput din) throws IOException {
    }

    /**
     * getMessage -- return an empty array as there is no data for
     * 		this request.
     */
    public byte[] getMessage() {

        return new byte[0];
    }
}