package com.hu.base.http.util.iqianjin;

import com.hu.base.http.util.exception.EncoderException;

public interface IBinaryEncoder extends IEncoder {

	byte[] encode(byte[] source) throws EncoderException;

}