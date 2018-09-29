package com.hu.base.http.util.iqianjin;

import com.hu.base.http.util.exception.EncoderException;

public interface IEncoder {

	Object encode(Object source) throws EncoderException;

}