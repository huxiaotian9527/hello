package com.hu.base.http.util.wolaidian;


import java.util.Map;

public class TestRSA {
	public static void main(String[] args) throws Exception {
//		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcCjaVAi/iNOvSX7c70nQmEvv9Hnr6JA43GD/1SbUA+4c6kFzMyLBy9EeAMUsbndnS/Pyut9M4rBEIbO4AgK9Wvk0T5Pz5Ultfs0uUVKSpgCaIQRNYz2+g1WDXFX1myx0Y2gGdWk2uwEZsC21WKuis1S+8wXVDy5QMpTgHcDBfWwIDAQAB";
//		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJwKNpUCL+I069JftzvSdCYS+/0eevokDjcYP/VJtQD7hzqQXMzIsHL0R4AxSxud2dL8/K630zisEQhs7gCAr1a+TRPk/PlSW1+zS5RUpKmAJohBE1jPb6DVYNcVfWbLHRjaAZ1aTa7ARmwLbVYq6KzVL7zBdUPLlAylOAdwMF9bAgMBAAECgYAUk3wzFgC+cYAPU7PT3aKXSrWT4SsSRSLivP3iGIAXyxM/871o+6XY4sFy9A0VbTFGEzo2x+LAfGAanlKcGlwEfEyE47HEq42NSgv8K0Z/G7AVHFDBvmzqtPpuyn4pJsNnh5rsNSTVoXOnqGDIcJ/YKXcc4xBaayA/W2iSWCdMUQJBAPglJ8DhhS/N20+vwztkUddS/rU/eQVM5I70Vfmn73YTqD0+TIETB1wIbbB2o1m6IE7AMkhMspEBCJKl9mN5ijcCQQCg+q7Qydya37mafdUH32fE0G7Qjm/Hzje+9GuiaVqC74p5BkTR2g+JaQg/lER10RhsoEogm9/c8lPi8HO7M/H9AkEAyq3GZ5OMLnyMqeycoPESq7YdVrsuRPcGJAFMC+tv+liZ7NPqtu5s723u8dglGg+dfdUb/NOwciGO+ADYW22VRwJAC1fevaObweYF5Bb9zeI2I640QMnoooGrd+8tLnKSCCvs6/4/FzJwadRW2nQOJucm+3CICKgzLBR/SLx8yzIr2QJALreuWjN+UxNgjd9qCWpmFE8nPwicuvFkO9KX3g0Bl76iaL8lDXACTNco2XUCJ1orDhO7h0YFO/WJo3unHWtvVQ==";

		 Map<String, String> params = RSAUtils.generateKeyPair();
		 String publicKey = params.get("publicKey");
		 String privateKey = params.get("privateKey");
		 String modulus = params.get("modulus");
		 System.out.println("公钥publicKey:" + publicKey);
		 System.out.println("私钥privateKey:" + privateKey);
		 System.out.println("modulus:" + modulus);

		String content = "guanlichang12";
		String encryptCon = RSAUtils.encrypt(content, publicKey);
		System.out.println("加密后内容：" + encryptCon);
		String dencryptCon = RSAUtils.decrypt(encryptCon, privateKey);
		System.out.println(dencryptCon);
	}
}
