package com.hu.base.http.util.iqianjin;


public interface IBinaryDecoder extends IDecoder {

	byte[] decode(byte[] source) throws DecoderException;

}